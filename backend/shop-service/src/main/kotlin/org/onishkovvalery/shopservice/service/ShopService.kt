package org.onishkovvalery.shopservice.service

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.Optional
import java.util.logging.Filter
import java.util.logging.LogRecord

@Service
class ShopService(private val vehicleClient: RestTemplate) {

    fun getVehiclesByFuelType(fuelType: String): ResponseEntity<String?> {
        val uri = UriComponentsBuilder.fromUriString("/vehicles")
            .queryParamIfPresent("fuelType", Optional.ofNullable(fuelType))
            .build().toString()
        return vehicleClient.getForEntity(uri, String::class.java)
    }

    fun resetVehicleDistance(vehicleId: Long): ResponseEntity<String?> {
        val url = "/vehicles/{vehicleId}"
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