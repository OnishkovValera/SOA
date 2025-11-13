package org.onishkovvalery.vehicleservice.service

import org.onishkovvalery.vehicleservice.model.dto.vehicle.VehicleDto
import org.onishkovvalery.vehicleservice.model.dto.vehicle.VehicleFilterDto
import org.onishkovvalery.vehicleservice.model.dto.vehicle.VehicleUpdateDto
import org.onishkovvalery.vehicleservice.model.entity.Vehicle
import org.onishkovvalery.vehicleservice.repository.VehicleRepository
import org.onishkovvalery.vehicleservice.utils.SpecificationBuilder
import org.onishkovvalery.vehicleservice.utils.VehicleMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service


@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository,
    private val vehicleMapper: VehicleMapper,
) {
    fun getVehicles(vehicle: VehicleFilterDto?, page: Pageable): Page<VehicleDto?> {
        if (vehicle == null) return vehicleRepository.findAll(page).map { vehicleMapper.vehicleToDto(it) }
        return vehicleRepository.findAll(createSpecification(vehicle), page).map { vehicleMapper.vehicleToDto(it) }
    }

    fun create(vehicle: VehicleDto): VehicleDto {
        val createdVehicle = vehicleRepository.save(vehicleMapper.dtoToVehicle(vehicle))
        return vehicleMapper.vehicleToDto(createdVehicle)
    }

    fun updateVehicle(id: Long, vehicleUpdateDto: VehicleUpdateDto): VehicleDto {
        val vehicle = vehicleRepository.findById(id).orElseThrow()
        vehicle.update(vehicleUpdateDto)
        val newVehicle = vehicleRepository.save(vehicle)
        return vehicleMapper.vehicleToDto(newVehicle)
    }

    fun deleteVehicle(id: Long) {
        vehicleRepository.findById(id).orElseThrow()
        vehicleRepository.deleteById(id)
    }

    fun getVehicleById(id: Long): VehicleDto {
        return vehicleMapper.vehicleToDto(vehicleRepository.findById(id).orElseThrow())
    }

    private fun createSpecification(vehicle: VehicleFilterDto): Specification<Vehicle> {
        return SpecificationBuilder<Vehicle>()
            .like(vehicle.name, "name")
            .eq(vehicle.coordinatesX, "x")
            .eq(vehicle.coordinatesY, "y")
            .eq(vehicle.fuelType, "fuelType")
            .eq(vehicle.distanceTravelled, "distanceTravelled")
            .eq(vehicle.numberOfWheels, "numberOfWheels")
            .eq(vehicle.enginePower, "enginePower")
            .build()
    }

    private fun Vehicle.update(fieldsOnUpdate: VehicleUpdateDto) {
        fieldsOnUpdate.name?.let { name = it }
        fieldsOnUpdate.enginePower?.let { enginePower = it }
        fieldsOnUpdate.numberOfWheels?.let { numberOfWheels = it }
        fieldsOnUpdate.distanceTravelled?.let { distanceTravelled = it }
        fieldsOnUpdate.fuelType?.let { fuelType = it }
        fieldsOnUpdate.coordinate?.x?.let { coordinate.x = it }
        fieldsOnUpdate.coordinate?.y?.let { coordinate.y = it }
    }

}