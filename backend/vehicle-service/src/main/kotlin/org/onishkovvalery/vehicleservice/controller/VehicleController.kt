package org.onishkovvalery.vehicleservice.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.onishkovvalery.vehicleservice.model.dto.VehicleDto
import org.onishkovvalery.vehicleservice.model.dto.VehicleFilterDto
import org.onishkovvalery.vehicleservice.model.entity.Vehicle
import org.onishkovvalery.vehicleservice.service.VehicleService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/vehicles")
class VehicleController(private val vehicleService: VehicleService) {

    @PostMapping
    @Operation(summary = "Create vehicle", method = "POST", tags = ["Vehicle API"])
    fun create(@Valid @RequestBody vehicle: VehicleDto): ResponseEntity<VehicleDto> {
        val createdVehicle = vehicleService.create(vehicle)
        return ResponseEntity.ok(createdVehicle)
    }

    @GetMapping
    @Operation(summary = "Get vehicles", method = "GET", tags = ["Vehicle API"])
    fun get(@ModelAttribute vehicleFilter: VehicleFilterDto, page: Pageable): ResponseEntity<Page<Vehicle?>> {
        println(vehicleFilter)
        println(page)
        return ResponseEntity.ok(vehicleService.getVehicles(vehicleFilter, page))
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle", method = "PUT", tags = ["Vehicle API"])
    fun update(@PathVariable id: Long, @Valid @RequestBody vehicle: VehicleDto): ResponseEntity<VehicleDto> {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicle))
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle", method = "DELETE", tags = ["Vehicle API"])
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        vehicleService.deleteVehicle(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by id", method = "GET", tags = ["Vehicle API"])
    fun find(@PathVariable id: Long): ResponseEntity<VehicleDto> {
        return ResponseEntity.ok(vehicleService.getVehicleById(id))
    }
}