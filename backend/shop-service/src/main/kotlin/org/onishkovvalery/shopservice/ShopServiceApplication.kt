package org.onishkovvalery.shopservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableConfigurationProperties
@ConfigurationPropertiesScan
class ShopServiceApplication

fun main(args: Array<String>) {
	runApplication<ShopServiceApplication>(*args)
}
