package org.onishkovvalery.vehicleservice.model.entity

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull

@Embeddable
class Coordinates(
    @field:Max(61)
    var x: Float,
    var y: Double?
)