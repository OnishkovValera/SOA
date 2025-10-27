package org.onishkovvalery.vehicleservice.service

import org.onishkovvalery.vehicleservice.model.dto.VehicleDto
import org.onishkovvalery.vehicleservice.model.dto.VehicleFilterDto
import org.onishkovvalery.vehicleservice.model.entity.Vehicle
import org.onishkovvalery.vehicleservice.repository.VehicleRepository
import org.onishkovvalery.vehicleservice.utils.VehicleMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service


@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository,
    private val vehicleMapper: VehicleMapper
) {
    fun create(vehicle: VehicleDto): VehicleDto {
        val createdVehicle = vehicleRepository.save(vehicleMapper.dtoToVehicle(vehicle))
        return vehicleMapper.vehicleToDto(createdVehicle)
    }

    fun getVehicles(vehicle: VehicleFilterDto, page: Pageable): Page<Vehicle?> {
        val spec = Specification.allOf<Vehicle>({ root, query, builder ->
            builder.like(root.get("name"), "%${vehicle.name}%")
            builder.and(builder.like(root.get("enginePower"), "%${vehicle.enginePower}%"))
        })

        val result = vehicleRepository.findAll(spec, page)
        println(" ============ $result")
        return result
    }

    fun updateVehicle(id: Long, vehicle: VehicleDto): VehicleDto {
        TODO("Not yet implemented")
    }

    fun deleteVehicle(id: Long) {
        TODO("Not yet implemented")
    }

    fun getVehicleById(id: Long): VehicleDto {
        TODO("Not yet implemented")
    }

}