package org.onishkovvalery.shopservice.controller

import org.onishkovvalery.shopservice.service.ShopService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType

@Controller
@RequestMapping("/api/v1/shop")
class ShopController(private val shopService: ShopService) {

    @GetMapping("/search/by-type/{type}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun searchByType(@PathVariable type: String, pageable: Pageable) = shopService.getVehiclesByFuelType(type, pageable)

    @GetMapping("fix-distance/{vehicleId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fixDistance(@PathVariable vehicleId: Long) = shopService.resetVehicleDistance(vehicleId)
}