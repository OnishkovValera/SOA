package org.onishkovvalery.shopservice.ejb

import jakarta.ejb.Stateless
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.slf4j.LoggerFactory

@Stateless(name = "ShopEJB")
open class ShopEJB : ShopRemote {
    private val logger = LoggerFactory.getLogger(ShopEJB::class.java)
    private val client = ClientBuilder.newClient()
    
    private val host = System.getProperty("vehicle.service.host") ?: "localhost"
    private val port = System.getProperty("vehicle.service.port") ?: "80"
    private val method = System.getProperty("vehicle.service.method") ?: "http"
    private val rootUri = "$method://haproxy:$port/api/v1"
    private val URI_FOR_VEHICLES = "vehicles"

    override fun getVehiclesByFuelType(fuelType: String?, pageNumber: Int, pageSize: Int): String? {
        logger.info("Executing getVehiclesByFuelType: fuelType={}, page={}, size={}", fuelType, pageNumber, pageSize)
        var target = client.target(rootUri).path(URI_FOR_VEHICLES)
        
        if (fuelType != null) {
            target = target.queryParam("fuelType", fuelType)
        }
        
        target = target.queryParam("page", pageNumber)
            .queryParam("size", pageSize)

        return try {
            val result = target.request(MediaType.APPLICATION_JSON).get(String::class.java)
            logger.debug("Successfully fetched vehicles from {}", target.uri)
            result
        } catch (e: Exception) {
            logger.error("Error while fetching vehicles from {}: {}", target.uri, e.message, e)
            "Error: ${e.message}"
        }
    }

    override fun resetVehicleDistance(vehicleId: Long): String? {
        logger.info("Executing resetVehicleDistance for vehicleId={}", vehicleId)
        val target = client.target(rootUri).path(URI_FOR_VEHICLES).path(vehicleId.toString())
        val body = mapOf("distanceTravelled" to 1L)
        
        return try {
            val response: Response = target.request(MediaType.APPLICATION_JSON)
                .put(Entity.json(body))
            
            if (response.statusInfo.family == Response.Status.Family.SUCCESSFUL) {
                logger.info("Successfully reset distance for vehicleId={}", vehicleId)
                response.readEntity(String::class.java)
            } else {
                logger.warn("Failed to reset distance for vehicleId={}: Status={} Reason={}", 
                    vehicleId, response.status, response.statusInfo.reasonPhrase)
                "Error: ${response.status} ${response.statusInfo.reasonPhrase}"
            }
        } catch (e: Exception) {
            logger.error("Error while resetting distance for vehicleId={}: {}", vehicleId, e.message, e)
            "Error: ${e.message}"
        }
    }
}
