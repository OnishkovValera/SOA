package org.onishkovvalery.shopservice.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.time.temporal.ChronoUnit

@Configuration
class ClientConfig {
    @Bean
    fun vehicleRestTemplate(): RestTemplate {
        return RestTemplateBuilder()
            .connectTimeout(
                Duration
                    .of(5, ChronoUnit.SECONDS)
            )
            .readTimeout(
                Duration
                    .of(5, ChronoUnit.SECONDS)
            )
            .rootUri("http://localhost:8080/api/v1")
            .build()
    }
}