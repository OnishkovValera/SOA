package org.onishkovvalery.shopservice.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "vehicle-service")
data class VehicleServiceConfig(
    var host: String,
    var port: Int,
    var method: String,
)