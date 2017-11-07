package net.daester.david.flywayjooqexample.domain

data class Airport(val id: Int, val ident: String, val type: String, val name: String, val scheduledService: Boolean?, val position: AirportPosition)
data class AirportPosition(val longitudeDeg: Double?, val latitudeDeg: Double?)

interface AirportRepository {
    fun getById(id: Int): Airport
    fun getByIdent(ident: String): Airport
    fun getAllAirports(): List<Airport>
    fun getAllAirportsByType(type: String): List<Airport>
    fun getAllAirportsByCountry(isoCountry: String): List<Airport>
    fun getAllAirportsReachableFrom(currentAirportIdent: String, distanceInMeter: Long, requiresService: Boolean): List<Airport>
}