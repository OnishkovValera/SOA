package org.onishkovvalery.vehicleservice.model.dto.vehicle

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.onishkovvalery.vehicleservice.model.dto.coordinates.CoordinateUpdateDto
import org.onishkovvalery.vehicleservice.model.entity.Coordinates
import org.onishkovvalery.vehicleservice.model.entity.enums.FuelType
import java.time.LocalDate

data class VehicleUpdateDto(
    @field:NotBlank
    var name: String? = null,
    var coordinate: CoordinateUpdateDto? = null,
    @field:Positive
    var enginePower: Float? = null,
    @field:Positive
    var numberOfWheels: Int? = null,
    @field:Positive
    var distanceTravelled: Int? = null,
    var fuelType: FuelType? = null,
)