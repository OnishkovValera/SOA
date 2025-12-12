package org.onishkovvalery.vehicleservice.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "shop-service")
data class ShopServiceConfiguration(
    var host: String,
    var port: Int,
    var method: String,
)
