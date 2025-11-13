package org.onishkovvalery.vehicleservice.model.dto.vehicle

import org.onishkovvalery.vehicleservice.model.entity.enums.FuelType

data class VehicleFilterDto(
    val name: String? = null,
    val enginePower: Float? = null,
    val numberOfWheels: Int? = null,
    val distanceTravelled: Int? = null,
    val fuelType: FuelType? = null,
    val coordinatesX: Float? = null,
    val coordinatesY: Double? = null,
)