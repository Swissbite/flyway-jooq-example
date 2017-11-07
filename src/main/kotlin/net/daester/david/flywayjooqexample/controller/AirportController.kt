package net.daester.david.flywayjooqexample.controller

import net.daester.david.flywayjooqexample.domain.AirportRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/airports")
class AirportController(private val airportRepository: AirportRepository) {

    @GetMapping
    fun getAllAirports() = airportRepository.getAllAirports()

    @GetMapping("{ident}")
    fun getByIdent(@PathVariable ident: String) = airportRepository.getByIdent(ident)

    @GetMapping("/by_type/{type}")
    fun getAllAirportsByType(@PathVariable type: String) = airportRepository.getAllAirportsByType(type)

    @GetMapping("/by_country/{isoCountry}")
    fun getAllAirportsByCountry(@PathVariable isoCountry: String) = airportRepository.getAllAirportsByCountry(isoCountry)

    @GetMapping("{currentAirportIdent}/distance/{distanceInMeter}")
    fun getReachableAirportsWithinRadius(@PathVariable currentAirportIdent: String,
                                         @PathVariable distanceInMeter: Long,
                                         @RequestParam(required = false, defaultValue = "false", name = "requireService") requiresService: Boolean)
            = airportRepository.getAllAirportsReachableFrom(currentAirportIdent, distanceInMeter, requiresService)
}