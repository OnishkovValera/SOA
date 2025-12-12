package org.onishkovvalery.vehicleservice.utils

import org.mapstruct.Mapper
import org.onishkovvalery.sharedLibs.common.dto.coordinates.CoordinateDto
import org.onishkovvalery.vehicleservice.model.entity.Coordinates

@Mapper(componentModel = "spring")
interface CoordinateMapper {
    fun coordinateToDto(source: Coordinates): CoordinateDto
    fun dtoToCoordinates(source: CoordinateDto): Coordinates
}