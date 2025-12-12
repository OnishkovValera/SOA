package org.onishkovvalery.shopservice.service

import org.onishkovvalery.sharedLibs.common.dto.vehicle.VehicleDto
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

private const val URI_FOR_VEHICLES = "/vehicles"

@Service
class ShopService(private val vehicleClient: RestTemplate) {
    fun getVehiclesByFuelType(fuelType: String, pageable: Pageable): ResponseEntity<String?> {
        println("$fuelType ${pageable.pageNumber} ${pageable.pageSize}")
        val uri = UriComponentsBuilder.fromUriString(URI_FOR_VEHICLES)
            .queryParamIfPresent("fuelType", Optional.ofNullable(fuelType))
            .queryParamIfPresent("page", Optional.ofNullable(pageable.pageNumber))
            .queryParamIfPresent("size", Optional.ofNullable(pageable.pageSize))
            .build().toString()

        return vehicleClient.getForEntity(uri, object : ParameterizedTypeReference<Page<VehicleDto>>() {})
    }

    fun resetVehicleDistance(vehicleId: Long): ResponseEntity<String?> {
        val url = "$URI_FOR_VEHICLES/$vehicleId"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val body = object {
            val distanceTravelled = 1L
        }
        val entity = HttpEntity(body, headers)
        return vehicleClient.exchange(
            url,
            HttpMethod.PUT,
            entity,
            String::class.java,
            vehicleId
        )
    }
}