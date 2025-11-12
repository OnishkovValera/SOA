package org.onishkovvalery.vehicleservice.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.onishkovvalery.vehicleservice.repository.VehicleRepository
import org.onishkovvalery.vehicleservice.utils.VehicleMapper

@ExtendWith(MockitoExtension::class)
class VehicleServiceTest {

    @Mock
    private lateinit var vehicleRepository: VehicleRepository

    @Mock
    private lateinit var vehicleMapper: VehicleMapper

    @InjectMocks
    private lateinit var vehicleService: VehicleService

    @BeforeEach
    fun setUp() {
        Mockito.reset(vehicleRepository)
        Mockito.reset(vehicleMapper)
    }

    @Test
    fun `Check vehicle service create method`() {

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