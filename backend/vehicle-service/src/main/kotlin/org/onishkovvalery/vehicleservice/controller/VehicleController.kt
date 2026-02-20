package org.onishkovvalery.vehicleservice.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.onishkovvalery.sharedLibs.common.dto.vehicle.VehicleDto
import org.onishkovvalery.sharedLibs.common.dto.vehicle.VehicleFilterDto
import org.onishkovvalery.sharedLibs.common.dto.vehicle.VehicleUpdateDto
import org.onishkovvalery.vehicleservice.service.VehicleService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/vehicles")
@ConditionalOnProperty(name = ["app.rest.enabled"], havingValue = "true", matchIfMissing = true)
class VehicleController(private val vehicleService: VehicleService) {

    @GetMapping
    @Operation(summary = "Get vehicles", method = "GET", tags = ["Vehicle API"])
    fun get(@ModelAttribute vehicleFilter: VehicleFilterDto, page: Pageable) = ResponseEntity.ok(vehicleService.getVehicles(vehicleFilter, page))

    @GetMapping(params = ["!name", "!coordinatesX", "!coordinatesY", "!enginePower", "!numberOfWheels", "!distanceTravelled", "!fuelType"])
    @Operation(summary = "Get vehicles", method = "GET", tags = ["Vehicle API"])
    fun get(page: Pageable) = ResponseEntity.ok(vehicleService.getVehicles(null, page))

    @PostMapping
    @Operation(summary = "Create vehicle", method = "POST", tags = ["Vehicle API"])
    fun create(@Valid @RequestBody vehicle: VehicleDto) = ResponseEntity.ok(vehicleService.create(vehicle))

    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle", method = "PUT", tags = ["Vehicle API"])
    fun update(@PathVariable id: Long, @Valid @RequestBody vehicle: VehicleUpdateDto) =
        ResponseEntity.ok(vehicleService.updateVehicle(id, vehicle))

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle", method = "DELETE", tags = ["Vehicle API"])
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        vehicleService.deleteVehicle(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by id", method = "GET", tags = ["Vehicle API"])
    fun find(@PathVariable id: Long) = ResponseEntity.ok(vehicleService.getVehicleById(id))
}
