package org.onishkovvalery.vehicleservice.model.dto

import org.onishkovvalery.vehicleservice.model.entity.FuelType

data class VehicleFilterDto(
    val name: String?,
    val model: String?,
    val enginePower: Float?,
    val numberOfWheels: Int?,
    val distanceTravelled: Int?,
    val fuelType: FuelType?,
    val coordinatesX: Float?,
    val coordinatesY: Double?,
)
