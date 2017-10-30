package net.daester.david.flywayjooqexample.controller

import net.daester.david.flywayjooqexample.domain.AirportRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/airports")
class AirportController(private val airportRepository: AirportRepository) {

    @GetMapping
    fun getAllAirports() = airportRepository.getAllAirports()
}