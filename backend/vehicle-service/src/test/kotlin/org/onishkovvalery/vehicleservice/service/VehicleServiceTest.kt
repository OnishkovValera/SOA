package org.onishkovvalery.vehicleservice.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.onishkovvalery.vehicleservice.repository.VehicleRepository
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VehicleServiceTest(
    private val vehicleService: VehicleService,
    private val vehicleRepository: VehicleRepository
) {

    @BeforeEach
    fun setUp() {
        Mockito.reset(vehicleRepository)
    }

    @Test
    fun create() {

    }

    @Test
    fun getVehicles() {
    }

    @Test
    fun updateVehicle() {
    }

    @Test
    fun deleteVehicle() {
    }

    @Test
    fun getVehicleById() {
    }

}