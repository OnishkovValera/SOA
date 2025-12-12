package org.onishkovvalery.sharedLibs.common.dto.vehicle

import org.onishkovvalery.sharedLibs.common.dto.enums.FuelType

data class VehicleFilterDto(
    val name: String? = null,
    val enginePower: Float? = null,
    val numberOfWheels: Int? = null,
    val distanceTravelled: Int? = null,
    val fuelType: FuelType? = null,
    val coordinatesX: Float? = null,
    val coordinatesY: Double? = null,
)