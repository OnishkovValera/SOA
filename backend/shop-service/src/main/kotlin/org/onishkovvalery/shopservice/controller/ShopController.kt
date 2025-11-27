package org.onishkovvalery.shopservice.controller

import org.onishkovvalery.shopservice.service.ShopService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v1/shop")
class ShopController(private val shopService: ShopService) {

    @GetMapping("/search/by-type/{type}")
    fun searchByType(@PathVariable type: String) = shopService.getVehiclesByFuelType(type)

    @GetMapping("fix-distance/{vehicleId}")
    fun fixDistance(@PathVariable vehicleId: Long) = shopService.resetVehicleDistance(vehicleId)

}