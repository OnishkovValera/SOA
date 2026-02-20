package org.onishkovvalery.vehicleservice.soap

import com.fasterxml.jackson.databind.ObjectMapper
import org.onishkovvalery.sharedLibs.common.dto.enums.FuelType
import org.onishkovvalery.sharedLibs.common.dto.vehicle.VehicleFilterDto
import org.onishkovvalery.sharedLibs.common.dto.vehicle.VehicleUpdateDto
import org.onishkovvalery.vehicleservice.service.VehicleService
import org.onishkovvalery.vehicleservice.soap.schema.GetVehiclesRequest
import org.onishkovvalery.vehicleservice.soap.schema.GetVehiclesResponse
import org.onishkovvalery.vehicleservice.soap.schema.UpdateVehicleDistanceRequest
import org.onishkovvalery.vehicleservice.soap.schema.UpdateVehicleDistanceResponse
import org.springframework.data.domain.PageRequest
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

@Endpoint
class VehicleSoapEndpoint(
    private val vehicleService: VehicleService,
    private val objectMapper: ObjectMapper,
) {

    companion object {
        const val NAMESPACE_URI = "http://onishkovvalery.org/vehicleservice/soap"
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVehiclesRequest")
    @ResponsePayload
    fun getVehicles(@RequestPayload request: GetVehiclesRequest): GetVehiclesResponse {
        val pageNumber = (request.page ?: 0).coerceAtLeast(0)
        val pageSize = (request.size ?: 20).coerceAtLeast(1)
        val fuelType = request.fuelType
            ?.trim()
            ?.takeIf { it.isNotBlank() }
            ?.let { FuelType.valueOf(it.uppercase()) }
        val filter = VehicleFilterDto(fuelType = fuelType)
        val page = vehicleService.getVehicles(filter, PageRequest.of(pageNumber, pageSize))

        val response = GetVehiclesResponse()
        response.jsonPayload = objectMapper.writeValueAsString(page)
        return response
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateVehicleDistanceRequest")
    @ResponsePayload
    fun updateVehicleDistance(@RequestPayload request: UpdateVehicleDistanceRequest): UpdateVehicleDistanceResponse {
        val distance = (request.distanceTravelled ?: 1).coerceAtLeast(1)
        val updated = vehicleService.updateVehicle(request.id, VehicleUpdateDto(distanceTravelled = distance))

        val response = UpdateVehicleDistanceResponse()
        response.jsonPayload = objectMapper.writeValueAsString(updated)
        return response
    }
}
