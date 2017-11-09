package net.daester.david.flywayjooqexample.infrastructure.jooq
import net.daester.david.flyawayjooqexample.infrastructure.jooq.Tables.*
import net.daester.david.flyawayjooqexample.infrastructure.jooq.enums.AirportsScheduledService
import net.daester.david.flyawayjooqexample.infrastructure.jooq.enums.AirportsType
import net.daester.david.flyawayjooqexample.infrastructure.jooq.enums.VAirportDistancesTargetAirportScheduledService
import net.daester.david.flyawayjooqexample.infrastructure.jooq.tables.interfaces.IAirports
import net.daester.david.flywayjooqexample.domain.Airport
import net.daester.david.flywayjooqexample.domain.AirportPosition
import net.daester.david.flywayjooqexample.domain.AirportRepository
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class JooqAirportRepository(private val jooq: DSLContext) : AirportRepository {
    val LOGGER = LoggerFactory.getLogger(JooqAirportRepository::class.java)

    override fun getById(id: Int): Airport = jooq.selectFrom(AIRPORTS).where(AIRPORTS.ID.eq(id)).fetchOne().asDomain()

    override fun getByIdent(ident: String): Airport = jooq.selectFrom(AIRPORTS).where(AIRPORTS.IDENT.eq(ident)).fetchOne().asDomain()

    override fun getAllAirports(): List<Airport> = jooq.selectFrom(AIRPORTS).map { it.asDomain() }

    override fun getAllAirportsByType(type: String): List<Airport>
            =
            try {
                jooq.selectFrom(AIRPORTS).where(AIRPORTS.TYPE.eq(AirportsType.valueOf(type))).map { it.asDomain() }
            } catch (e: IllegalArgumentException) {
                LOGGER.warn("Illegal argument: {}", e.message)
                emptyList()
            }

    override fun getAllAirportsByCountry(isoCountry: String): List<Airport> =
            jooq.selectFrom(AIRPORTS).where(
                    AIRPORTS.COUNTRY_ID.`in`(
                            jooq.select(COUNTRIES.ID).from(COUNTRIES).where(COUNTRIES.CODE.eq(isoCountry))
                    )
            ).map { it.asDomain() }

    override fun getAllAirportsReachableFrom(currentAirportIdent: String,
                                             distanceInMeter: Long,
                                             requiresService: Boolean): List<Airport> =
            jooq.selectFrom(AIRPORTS)
                    .where(AIRPORTS.ID.`in`(
                            jooq.select(V_AIRPORT_DISTANCES.TARGET_AIRPORT_ID)
                                    .from(V_AIRPORT_DISTANCES)
                                    .where(V_AIRPORT_DISTANCES.START_AIRPORT_IDENT.eq(currentAirportIdent))
                                    .and(V_AIRPORT_DISTANCES.DISTANCE_IN_METER.le(distanceInMeter.toDouble()))
                                    .let {
                                        if (requiresService)
                                            it.and(V_AIRPORT_DISTANCES.TARGET_AIRPORT_SCHEDULED_SERVICE.eq(VAirportDistancesTargetAirportScheduledService.yes))
                                        else
                                            it
                                    })
                    ).map { it.asDomain() }


    private fun IAirports.asDomain() = Airport(id, ident, type.name, name, when (scheduledService) {
        AirportsScheduledService.yes -> true
        AirportsScheduledService.no -> false
        else -> null
    }, AirportPosition(longitudeDeg, latitudeDeg))
}