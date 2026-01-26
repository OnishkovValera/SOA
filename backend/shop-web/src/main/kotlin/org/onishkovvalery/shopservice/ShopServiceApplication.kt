package org.onishkovvalery.shopservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableConfigurationProperties
@ConfigurationPropertiesScan
class ShopServiceApplication : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        System.setProperty("org.springframework.boot.logging.LoggingSystem", "none")
        return builder.sources(ShopServiceApplication::class.java)
    }
}

fun main(args: Array<String>) {
	runApplication<ShopServiceApplication>(*args)
}
