package org.onishkovvalery.vehicleservice.controller

import org.onishkovvalery.vehicleservice.model.entity.enums.FuelType
import org.onishkovvalery.vehicleservice.service.SearchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/vehicles/search")
class SearchController(private val searchService: SearchService) {

    @GetMapping("name-contains")
    fun getByNameContains(@RequestParam name: String) = ResponseEntity.ok(searchService.getByNameContains(name))

    @GetMapping("name-starts-with")
    fun getByNameStartsWith(@RequestParam name: String) = ResponseEntity.ok(searchService.getByNameStartsWith(name))

    @GetMapping("fuel-type-greater-than")
    fun getByFuelTypeGreaterThan(@RequestParam fuelType: FuelType) = ResponseEntity.ok(searchService.getByFuelTypeGreaterThan(fuelType))
}