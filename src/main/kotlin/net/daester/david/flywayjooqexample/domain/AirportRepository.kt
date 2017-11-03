package net.daester.david.flywayjooqexample.domain

class Airport(val id: Int, val name: String)

interface AirportRepository {
    fun getAllAirports(): List<Airport>

    fun getAllAirportsByType(type: String): List<Airport>
}