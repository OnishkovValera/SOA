package org.onishkovvalery.shopservice.service

import org.onishkovvalery.shopservice.ejb.ShopRemote
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.http.*
import org.springframework.stereotype.Service

@Service
class ShopService(private val shopEJB: ShopRemote) {

    private val logger = LoggerFactory.getLogger(ShopService::class.java)

    fun getVehiclesByFuelType(fuelType: String, pageable: Pageable): ResponseEntity<String?> {
        logger.info("Web request to getVehiclesByFuelType: fuelType={}, pageable={}", fuelType, pageable)
        val result = shopEJB.getVehiclesByFuelType(fuelType, pageable.pageNumber, pageable.pageSize)
        return ResponseEntity(result, HttpStatus.OK)
    }

    fun resetVehicleDistance(vehicleId: Long): ResponseEntity<String?> {
        logger.info("Web request to resetVehicleDistance: vehicleId={}", vehicleId)
        val result = shopEJB.resetVehicleDistance(vehicleId)
        return ResponseEntity(result, HttpStatus.OK)
    }
}