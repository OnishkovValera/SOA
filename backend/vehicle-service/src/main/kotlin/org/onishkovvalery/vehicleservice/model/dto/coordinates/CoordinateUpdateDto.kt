package org.onishkovvalery.vehicleservice.model.dto.coordinates

import jakarta.validation.constraints.Max

data class CoordinateUpdateDto (
    @field:Max(61)
    var x: Float? = null,
    var y: Double? = null
)