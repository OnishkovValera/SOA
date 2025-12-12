package org.onishkovvalery.shopservice.config

import org.springframework.boot.ssl.SslBundles
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.time.temporal.ChronoUnit


@Configuration
class ClientConfig {
    @Bean
    fun vehicleRestTemplate(ssl: SslBundles, vehicleServiceConfig: VehicleServiceConfig): RestTemplate {
        return RestTemplateBuilder()
            .sslBundle(ssl.getBundle("client"))
            .connectTimeout(
                Duration
                    .of(5, ChronoUnit.SECONDS)
            )
            .readTimeout(
                Duration
                    .of(5, ChronoUnit.SECONDS)
            )
            .rootUri("${vehicleServiceConfig.method}://${vehicleServiceConfig.host}:${vehicleServiceConfig.port}/api/v1")
            .build()

    }

    @Bean
    fun jacksonConverter(): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter()
    }



}