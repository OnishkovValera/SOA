package org.onishkovvalery.vehicleservice.model.entity

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.Max

@Embeddable
class Coordinates(
    @Max(61)
    private var x: Float,
    private var y: Double?
)