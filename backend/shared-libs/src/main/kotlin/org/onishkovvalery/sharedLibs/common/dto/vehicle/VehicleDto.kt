package org.onishkovvalery.sharedLibs.common.dto.vehicle

import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.onishkovvalery.sharedLibs.common.dto.coordinates.CoordinateDto
import org.onishkovvalery.sharedLibs.common.dto.enums.FuelType
import java.time.LocalDate

data class VehicleDto(
    val id: Long? = null,
    @field:Pattern(regexp = ".*\\S.*")
    val name: String,
    @field:Valid
    val coordinate: CoordinateDto,
    val creationDate: LocalDate = LocalDate.now(),
    @field:Positive
    val enginePower: Float,
    @field:Positive
    val numberOfWheels: Int,
    @field:Positive
    val distanceTravelled: Int,
    val fuelType: FuelType
)