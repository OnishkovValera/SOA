package org.onishkovvalery.vehicleservice.config

import org.springframework.boot.autoconfigure.web.client.RestClientSsl
import org.springframework.boot.ssl.SslBundle
import org.springframework.boot.ssl.SslBundles
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.time.temporal.ChronoUnit

@Configuration
class GlobalConfig {
    @Bean
    fun restTemplate(ssl: SslBundles, ): RestTemplate {
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
            .rootUri("https://localhost:8080/api/v1")
            .build()
    }
}