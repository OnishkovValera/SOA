package org.onishkovvalery.shopservice.service

import org.onishkovvalery.shopservice.ejb.ShopRemote
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ShopService(private val shopEJB: ShopRemote) {

    private val logger = LoggerFactory.getLogger(ShopService::class.java)

    fun getVehiclesByFuelType(fuelType: String, pageable: Pageable): String? {
        logger.info("Web request to getVehiclesByFuelType: fuelType={}, pageable={}", fuelType, pageable)
        return shopEJB.getVehiclesByFuelType(fuelType, pageable.pageNumber, pageable.pageSize)
    }

    fun resetVehicleDistance(vehicleId: Long): String? {
        logger.info("Web request to resetVehicleDistance: vehicleId={}", vehicleId)
        return shopEJB.resetVehicleDistance(vehicleId)
    }
}
