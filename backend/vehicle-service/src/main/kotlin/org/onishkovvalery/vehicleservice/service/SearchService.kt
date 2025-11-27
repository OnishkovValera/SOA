package org.onishkovvalery.vehicleservice.service

import org.onishkovvalery.vehicleservice.model.entity.Vehicle
import org.onishkovvalery.vehicleservice.model.entity.enums.FuelType
import org.onishkovvalery.vehicleservice.repository.VehicleRepository
import org.onishkovvalery.vehicleservice.utils.SpecificationBuilder
import org.onishkovvalery.vehicleservice.utils.VehicleMapper
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val vehicleMapper: VehicleMapper,
    private val vehicleRepository: VehicleRepository,
) {
    fun getByNameContains(name: String) = vehicleRepository.findAll(
        SpecificationBuilder<Vehicle>()
            .like(name, "name")
            .build())
        .map { vehicle -> vehicleMapper.vehicleToDto(vehicle) }

    fun getByNameStartsWith(name: String) = vehicleRepository.findAll(
        SpecificationBuilder<Vehicle>()
            .startsWith(name, "name")
            .build())
        .map { vehicle -> vehicleMapper.vehicleToDto(vehicle) }

    fun getByFuelTypeGreaterThan(fuelType: FuelType) = vehicleRepository.findAll(
        SpecificationBuilder<Vehicle>()
            .fuelTypeGreaterThan(fuelType)
            .build())
        .map { vehicle -> vehicleMapper.vehicleToDto(vehicle) }
}