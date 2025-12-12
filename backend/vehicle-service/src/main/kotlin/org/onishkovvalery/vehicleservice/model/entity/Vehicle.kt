package org.onishkovvalery.vehicleservice.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.onishkovvalery.sharedLibs.common.dto.enums.FuelType
import java.time.LocalDate

@Entity
class Vehicle(
    @field: [Id GeneratedValue(strategy = GenerationType.IDENTITY)]
    val id: Long? = null,

    @field:NotBlank
    var name: String,

    var coordinate: Coordinates,

    var creationDate: LocalDate = LocalDate.now(),

    @field:Positive
    var enginePower: Float,

    @field:Positive
    var numberOfWheels: Int,

    @field:Positive
    var distanceTravelled: Int,

    @Enumerated(EnumType.ORDINAL)
    var fuelType: FuelType
)