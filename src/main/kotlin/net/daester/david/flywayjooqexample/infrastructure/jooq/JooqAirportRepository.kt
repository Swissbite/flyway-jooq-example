package net.daester.david.flywayjooqexample.infrastructure.jooq

import ch.zvv.kds.infrastructure.jooq.Tables
import ch.zvv.kds.infrastructure.jooq.tables.Airports
import net.daester.david.flywayjooqexample.domain.Airport
import net.daester.david.flywayjooqexample.domain.AirportRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class JooqAirportRepository(private val jooq: DSLContext): AirportRepository {
    val AIRPORTS: Airports = Tables.AIRPORTS
    override fun getAllAirports(): List<Airport> = jooq.selectFrom(AIRPORTS).map { Airport(it.id, it.name) }
}