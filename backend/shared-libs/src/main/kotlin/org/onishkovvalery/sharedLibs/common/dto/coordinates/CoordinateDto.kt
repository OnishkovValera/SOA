package org.onishkovvalery.sharedLibs.common.dto.coordinates

import jakarta.validation.constraints.Max

data class CoordinateDto(
    @field:Max(61)
    var x: Float,
    var y: Double?
)
