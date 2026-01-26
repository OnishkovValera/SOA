package org.onishkovvalery.shopservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.onishkovvalery.shopservice.service.ShopService
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/shop")
class ShopController(private val shopService: ShopService) {

    @GetMapping("/search/by-type/{type}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun searchByType(@PathVariable type: String, pageable: Pageable): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(shopService.getVehiclesByFuelType(type, pageable))
        } catch (e: Exception) {
            ResponseEntity.status(500).body(mapOf("error" to e.message))
        }
    }

    @PostMapping("/fix-distance/{vehicleId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fixDistance(@PathVariable vehicleId: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(shopService.resetVehicleDistance(vehicleId))
        } catch (e: Exception) {
            ResponseEntity.status(500).body(mapOf("error" to e.message))
        }
    }
}