package org.onishkovvalery.vehicleservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.onishkovvalery.vehicleservice.model.dto.VehicleDto
import org.onishkovvalery.vehicleservice.model.entity.Coordinates
import org.onishkovvalery.vehicleservice.model.entity.FuelType
import org.onishkovvalery.vehicleservice.service.VehicleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@WebMvcTest(VehicleController::class)
@ExtendWith(MockitoExtension::class)
@AutoConfigureMockMvc
class VehicleControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    @MockitoBean
    private lateinit var vehicleService: VehicleService

    @Test
    fun `Check vehicle controller get method with valid request body`() {
        //given
        Mockito.`when`(
            vehicleService.getVehicles(
                VehicleDto(
                    name = "ferrari",
                    coordinate = Coordinates(
                        x = 1F,
                        y = 2.0
                    ),
                    enginePower = 10.1F,
                    numberOfWheels = 4,
                    distanceTravelled = 5,
                    fuelType = FuelType.DIESEL
                ),
                PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"))
            )
        ).thenReturn(
            PageImpl(
                listOf(
                    VehicleDto(
                        1,
                        "ferrari",
                        Coordinates(
                            1F,
                            2.0
                        ),
                        LocalDate.now(),
                        10.1F,
                        4,
                        5,
                        FuelType.DIESEL
                    )
                )
            )
        )

        val requestMap = mapOf(
            "name" to "ferrari",
            "coordinate" to mapOf(
                "x" to 1,
                "y" to 2
            ),
            "enginePower" to "10.1",
            "numberOfWheels" to 4,
            "distanceTravelled" to 5,
            "fuelType" to "DIESEL"
        )
        //when
        val result = mockMvc.perform(
            post("/api/v1/vehicles").content(
                objectMapper.writeValueAsString(requestMap)
            )
        )
        //then
        println(result.andReturn().response.contentAsString)
    }

    @Test
    fun `getVehicle method test @ModelAttribute is working`() {
        //given

        val vehicle = VehicleDto(
            name = "ferrari",
            coordinate = Coordinates(
                x = 1F,
                y = 2.0
            ),
            enginePower = 10.1F,
            numberOfWheels = 4,
            distanceTravelled = 5,
            fuelType = FuelType.DIESEL
        )

        Mockito.`when`(vehicleService.getVehicles(vehicle, PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"))))
            .thenReturn(
                PageImpl(
                    listOf(
                        VehicleDto(
                            id = 1L,
                            name = "ferrari",
                            coordinate = Coordinates(
                                x = 1F,
                                y = 2.0
                            ),
                            enginePower = 10.1F,
                            numberOfWheels = 4,
                            distanceTravelled = 5,
                            fuelType = FuelType.DIESEL
                        )
                    )
                )
            )

        //when
        val result = mockMvc.perform(
            get("/api/v1/vehicles")
                .param("name", "ferrari")
                .param("coordinate.x", "1")
                .param("coordinate.y", "2")
                .param("enginePower", "10.1")
                .param("numberOfWheels", "4")
                .param("distanceTravelled", "5")
                .param("fuelType", "DIESEL")
        )

        //then
        result.andExpect(status().isOk)
            .andExpect { println(it.response.contentAsString) }
    }

}