package org.onishkovvalery.vehicleservice.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.time.LocalDate

@Entity
class Vehicle(
    @field: [Id GeneratedValue(strategy = GenerationType.IDENTITY)]
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

    @Enumerated(EnumType.STRING)
    private var fuelType: FuelType
)