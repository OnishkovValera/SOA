package org.onishkovvalery.vehicleservice.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.kotlin.*
import org.onishkovvalery.vehicleservice.model.dto.vehicle.VehicleDto
import org.onishkovvalery.vehicleservice.model.dto.vehicle.VehicleFilterDto
import org.onishkovvalery.vehicleservice.service.VehicleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.Assert
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

@WebMvcTest(VehicleController::class)
@AutoConfigureMockMvc
class VehicleControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {
    @MockitoBean
    private lateinit var vehicleService: VehicleService

    @BeforeEach
    fun setUp() {
        Mockito.reset(vehicleService)
    }

    @ParameterizedTest
    @MethodSource("org.onishkovvalery.vehicleservice.testData.ControllerTestDataProviderKt#validRequest")
    fun `Check vehicle controller get method validation with valid request`(
        request: Pair<VehicleFilterDto?, Pageable?>,
        response: Page<VehicleDto?>
    ) {
        //given
        Mockito.`when`(
            vehicleService.getVehicles(
                anyOrNull(), any()
            )
        ).thenReturn(response)
        val params = LinkedMultiValueMap<String, String>()
        val map: Map<String, Any> = objectMapper.convertValue(
            request.first,
            object : TypeReference<Map<String, Any>>() {}
        )
        map.forEach { (key, value) -> value?.let { params.add(key, value.toString()) } }
        request.second?.let { pageable ->
            params.add("page", pageable.pageNumber.toString())
            params.add("size", pageable.pageSize.toString())
            if (pageable.sort.isSorted) {
                params.add("sort", pageable.sort.first().property + "," + pageable.sort.first().direction)
            }
        }

        //when
        val result = mockMvc.perform(get("/api/v1/vehicles").params(params))

        //then
        result.andExpect { status().isOk }
            .andExpect { jsonPath("$.size").value(response.size) }
            .andExpect { Assert.notNull(it.response, "Response must not be null") }
    }

    @ParameterizedTest
    @MethodSource("org.onishkovvalery.vehicleservice.testData.ControllerTestDataProviderKt#invalidRequest")
    fun `Check vehicle controller get method validation with invalid request`(
        request: MultiValueMap<String, String>,
        expectedStatus: Int,
    ) {
        //given
        Mockito.`when`(vehicleService.getVehicles(anyOrNull(), any()))
            .thenReturn(Mockito.mock(Page::class.java) as Page<VehicleDto?>)
        //when
        val result = mockMvc.perform(get("/api/v1/vehicles").params(request))
        //then
        result.andExpect { it.response.status == expectedStatus }
    }

    @ParameterizedTest
    @MethodSource("org.onishkovvalery.vehicleservice.testData.ControllerTestDataProviderKt#invalidCreateRequestData")
    fun `Check invalid request body in vehicle controller post method`(body: Any?) {
        //given
        Mockito.`when`(vehicleService.create(Mockito.mock(VehicleDto::class.java)))
            .thenReturn(Mockito.mock(VehicleDto::class.java))

        //when
        val result = mockMvc.perform(
            post("/api/v1/vehicles")
                .content(objectMapper.writeValueAsString(body))
                .contentType("application/json")
        )

        //then
        verify(vehicleService, never()).create(isA<VehicleDto>())
        result.andExpect { it.response.status == 400 }
    }

    @ParameterizedTest
    @MethodSource("org.onishkovvalery.vehicleservice.testData.ControllerTestDataProviderKt#validCreateRequestData")
    fun `Check valid request body in vehicle controller post method`(body: Any?) {
        //given
        Mockito.`when`(vehicleService.create(Mockito.mock(VehicleDto::class.java)))
            .thenReturn(Mockito.mock(VehicleDto::class.java))

        //when
        val result = mockMvc.perform(
            post("/api/v1/vehicles")
                .content(objectMapper.writeValueAsString(body))
                .contentType("application/json")
        )

        //then
        verify(vehicleService, times(1)).create(isA<VehicleDto>())
        result.andExpect { it.response.status == 400 }
    }

    @ParameterizedTest
    @MethodSource("org.onishkovvalery.vehicleservice.testData.ControllerTestDataProviderKt#validUpdateRequestData")
    fun `Check valid request body in vehicle controller put method`(body: Any?) {
        //given
        Mockito.`when`(
            vehicleService.updateVehicle(
                any(),
                any()
            )
        ).thenReturn(mock<VehicleDto>())

        //when
        val result = mockMvc.perform(put("/api/v1/vehicles/1")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(body))
        )

        //then
        verify(vehicleService, times(1)).updateVehicle(any(), any())
        result.andExpect { it.response.status == 200 }
    }

    @ParameterizedTest
    @MethodSource("org.onishkovvalery.vehicleservice.testData.ControllerTestDataProviderKt#invalidUpdateRequestData")
    fun `Check invalid request body in vehicle controller put method`(body: Any?) {
        //given
        Mockito.`when`(
            vehicleService.updateVehicle(
                any(),
                any()
            )
        ).thenReturn(mock<VehicleDto>())

        //when
        val result = mockMvc.perform(
            put("/api/v1/vehicles/1")
                .content(objectMapper.writeValueAsString(body))
                .contentType("application/json")
        )

        //then
        verify(vehicleService, never()).create(isA<VehicleDto>())
        result.andExpect { it.response.status == 400 }
    }

    @Test
    fun `Check valid request body in vehicle controller delete method`() {
        //given

        //when
        val result = mockMvc.perform(delete("/api/v1/vehicles/1"))


        //then
        verify(vehicleService, times(1)).deleteVehicle(any())
        result.andExpect { it.response.status == 200 }
    }

    @Test
    fun `Check invalid request body in vehicle controller delete method`() {
        //given
        Mockito.`when`(
            vehicleService.deleteVehicle(any())
        ).thenThrow(IllegalArgumentException::class.java)

        //when
        val result = mockMvc.perform(delete("/api/v1/vehicles/1"))

        //then
        verify(vehicleService, times(1)).deleteVehicle(any())
        result.andExpect { it.response.status == 400 }
    }

    @Test
    fun `Check valid request body in vehicle controller get by id method`() {
        //given

        //when
        val result = mockMvc.perform(get("/api/v1/vehicles/1"))


        //then
        verify(vehicleService, times(1)).getVehicleById(any())
        result.andExpect { it.response.status == 200 }
    }

    @Test
    fun `Check invalid request to get by id method with non exists id in vehicle controller`() {
        //given
        Mockito.`when`(vehicleService.getVehicleById(any())).thenThrow(IllegalArgumentException::class.java)

        //when
        val result = mockMvc.perform(get("/api/v1/vehicles/1"))

        //then
        verify(vehicleService, times(1)).getVehicleById(any())
        result.andExpect { it.response.status == 400 }
    }
}