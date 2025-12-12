package org.onishkovvalery.sharedLibs.common.dto.vehicle

import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.onishkovvalery.sharedLibs.common.dto.coordinates.CoordinateUpdateDto
import org.onishkovvalery.sharedLibs.common.dto.enums.FuelType

data class VehicleUpdateDto(
    @field:Pattern(regexp = ".*\\S.*")
    var name: String? = null,
    @field:Valid
    var coordinate: CoordinateUpdateDto? = null,
    @field:Positive
    var enginePower: Float? = null,
    @field:Positive
    var numberOfWheels: Int? = null,
    @field:Positive
    var distanceTravelled: Int? = null,
    var fuelType: FuelType? = null,
)