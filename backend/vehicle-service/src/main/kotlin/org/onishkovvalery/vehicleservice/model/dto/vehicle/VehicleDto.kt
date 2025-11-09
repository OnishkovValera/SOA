package org.onishkovvalery.vehicleservice.model.dto.vehicle

import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.onishkovvalery.vehicleservice.model.entity.Coordinates
import org.onishkovvalery.vehicleservice.model.entity.enums.FuelType
import java.time.LocalDate

data class VehicleDto (
    val id: Long? = null,
    @field:Pattern(regexp = ".*\\S.*")
    val name: String,
    val model: String,
    @field:Valid
    val coordinate: Coordinates,
    val creationDate: LocalDate = LocalDate.now(),
    @field:Positive
    val enginePower: Float,
    @field:Positive
    val numberOfWheels: Int,
    @field:Positive
    val distanceTravelled: Int,
    val fuelType: FuelType
)