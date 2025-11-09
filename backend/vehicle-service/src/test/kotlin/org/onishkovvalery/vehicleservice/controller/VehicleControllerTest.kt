package org.onishkovvalery.vehicleservice.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.ArgumentMatchers.nullable
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.notNull
import org.onishkovvalery.vehicleservice.model.dto.vehicle.VehicleDto
import org.onishkovvalery.vehicleservice.model.dto.vehicle.VehicleFilterDto
import org.onishkovvalery.vehicleservice.model.entity.Coordinates
import org.onishkovvalery.vehicleservice.model.entity.enums.FuelType
import org.onishkovvalery.vehicleservice.service.VehicleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.*
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
    @MethodSource("org.onishkovvalery.vehicleservice.testData.TestDataProviderKt#validRequest")
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
    @MethodSource("org.onishkovvalery.vehicleservice.testData.TestDataProviderKt#invalidRequest")
    fun `Check vehicle controller get method validation with not valid request`(
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
            .andExpect { println(it.response.contentAsString) }

    }
}