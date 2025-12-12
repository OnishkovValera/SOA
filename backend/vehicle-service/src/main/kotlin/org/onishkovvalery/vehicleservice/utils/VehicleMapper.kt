package org.onishkovvalery.vehicleservice.utils

import org.mapstruct.Mapper
import org.onishkovvalery.sharedLibs.common.dto.vehicle.VehicleDto
import org.onishkovvalery.vehicleservice.model.entity.Vehicle

@Mapper(componentModel = "spring", uses = [CoordinateMapper::class])
interface VehicleMapper{
    fun vehicleToDto(source: Vehicle): VehicleDto
    fun dtoToVehicle(source: VehicleDto): Vehicle
}
