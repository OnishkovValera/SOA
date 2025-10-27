package org.onishkovvalery.vehicleservice.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.onishkovvalery.vehicleservice.model.entity.Coordinates
import org.onishkovvalery.vehicleservice.model.entity.FuelType
import java.time.LocalDate

data class VehicleDto (
    private val id: Long? = null,
    @NotBlank
    private var name: String,
    private var coordinate: Coordinates,
    private var creationDate: LocalDate = LocalDate.now(),
    @Positive
    private var enginePower: Float,
    @Positive
    private var numberOfWheels: Int,
    @Positive
    private var distanceTravelled: Int,
    private var fuelType: FuelType
)
