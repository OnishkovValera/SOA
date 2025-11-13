package org.onishkovvalery.vehicleservice.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.onishkovvalery.vehicleservice.model.entity.Vehicle
import org.onishkovvalery.vehicleservice.repository.VehicleRepository
import org.onishkovvalery.vehicleservice.utils.VehicleMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

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
        //given
        Mockito.`when`(vehicleMapper.dtoToVehicle(any())).thenReturn(mock())
        Mockito.`when`(vehicleRepository.save(any())).thenReturn(mock())
        Mockito.`when`(vehicleMapper.vehicleToDto(any())).thenReturn(mock())

        //when
        vehicleService.create(mock())

        //then
        verify(vehicleRepository, times(1)).save(any())
        verify(vehicleMapper, times(1)).vehicleToDto(any())
        verify(vehicleMapper, times(1)).dtoToVehicle(any())

    }

    @Test
    fun `Check vehicle getVehicle method with vehicleFilter equals null`() {
        //given
        val page = PageRequest.of(0, 20)
        Mockito.`when`(vehicleRepository.findAll(any<Pageable>())).thenReturn(Page.empty())

        //when
        vehicleService.getVehicles(null, page)
        //then
        verify(vehicleRepository, times(1)).findAll(any<Pageable>())
        verify(vehicleRepository, never()).findAll(any<Specification<Vehicle>>(), any<Pageable>())


    }

    @Test
    fun `Check vehicle getVehicle method with vehicleFilter not equals null`() {
        //given
        Mockito.`when`(
            vehicleRepository.findAll(
                any<Specification<Vehicle>>(),
                any<Pageable>()
            )
        ).thenReturn(Page.empty())

        //when
        vehicleService.getVehicles(mock(), mock())

        //then
        verify(vehicleRepository, never()).findAll(any<Pageable>())
        verify(vehicleRepository, times(1)).findAll(any<Specification<Vehicle>>(), any<Pageable>())


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