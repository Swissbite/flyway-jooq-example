package net.daester.david.flywayjooqexample.infrastructure.jooq

import ch.zvv.kds.infrastructure.jooq.Tables
import ch.zvv.kds.infrastructure.jooq.enums.AirportsType
import ch.zvv.kds.infrastructure.jooq.tables.Airports
import ch.zvv.kds.infrastructure.jooq.tables.records.AirportsRecord
import net.daester.david.flywayjooqexample.domain.Airport
import net.daester.david.flywayjooqexample.domain.AirportRepository
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class JooqAirportRepository(private val jooq: DSLContext) : AirportRepository {
    val LOGGER = LoggerFactory.getLogger(JooqAirportRepository::class.java)
    val AIRPORTS: Airports = Tables.AIRPORTS

    override fun getAllAirports(): List<Airport> = jooq.selectFrom(AIRPORTS).map { it.asDomain() }

    override fun getAllAirportsByType(type: String): List<Airport>
            = try {
        jooq.selectFrom(AIRPORTS).where(AIRPORTS.TYPE.eq(AirportsType.valueOf(type))).map { it.asDomain() }
    } catch (e: IllegalArgumentException) {
        LOGGER.warn("Illegal argument: {}", e.message)
        emptyList()
    }

    private fun AirportsRecord.asDomain() = Airport(id, name)
}