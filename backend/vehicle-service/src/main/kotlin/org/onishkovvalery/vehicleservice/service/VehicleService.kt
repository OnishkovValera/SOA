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

    fun create(vehicle: VehicleDto): VehicleDto {
        val createdVehicle = vehicleRepository.save(vehicleMapper.dtoToVehicle(vehicle))
        return vehicleMapper.vehicleToDto(createdVehicle)
    }

    fun getVehicles(vehicle: VehicleFilterDto?, page: Pageable): Page<VehicleDto?> {
        if (vehicle == null) return vehicleRepository.findAll(page).map { vehicleMapper.vehicleToDto(it) }
        val specification = createSpecification(vehicle)
        return vehicleRepository.findAll(specification, page).map { vehicleMapper.vehicleToDto(it) }
    }

    fun updateVehicle(id: Long, vehicleUpdateDto: VehicleUpdateDto): VehicleDto {
        val oldVehicle = vehicleRepository.findById(id).orElseThrow()
        this.overrideVehicle(oldVehicle, vehicleUpdateDto)
        val newVehicle = vehicleRepository.save(oldVehicle)
        return vehicleMapper.vehicleToDto(newVehicle)

    }

    fun deleteVehicle(id: Long) {
        TODO("Not yet implemented")
    }

    fun getVehicleById(id: Long): VehicleDto {
        TODO("Not yet implemented")
    }

    private fun createSpecification(vehicle: VehicleFilterDto): Specification<Vehicle> {
        return SpecificationBuilder<Vehicle>()
            .like(vehicle.name, "name")
            .eq(vehicle.coordinatesX, "x")
            .eq(vehicle.coordinatesY, "y")
            .like(vehicle.model, "model")
            .eq(vehicle.fuelType, "fuelType")
            .eq(vehicle.distanceTravelled, "distanceTravelled")
            .eq(vehicle.numberOfWheels, "numberOfWheels")
            .eq(vehicle.enginePower, "enginePower")
            .build()
    }

    private fun overrideVehicle(vehicle: Vehicle, fieldsOnUpdate: VehicleUpdateDto) {
        fieldsOnUpdate.name?.let { vehicle.name = it }
        fieldsOnUpdate.enginePower?.let { vehicle.enginePower = it }
        fieldsOnUpdate.numberOfWheels?.let { vehicle.numberOfWheels = it }
        fieldsOnUpdate.distanceTravelled?.let { vehicle.distanceTravelled = it }
        fieldsOnUpdate.fuelType?.let { vehicle.fuelType = it }
        fieldsOnUpdate.coordinate?.x?.let { vehicle.coordinate.x = it }
        fieldsOnUpdate.coordinate?.y?.let { vehicle.coordinate.y = it }
    }

}