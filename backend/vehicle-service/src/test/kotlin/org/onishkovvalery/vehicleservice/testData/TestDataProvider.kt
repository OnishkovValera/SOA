package org.onishkovvalery.vehicleservice.testData

import org.hibernate.internal.util.collections.CollectionHelper.listOf
import org.junit.jupiter.params.provider.Arguments
import org.onishkovvalery.vehicleservice.model.dto.vehicle.VehicleDto
import org.onishkovvalery.vehicleservice.model.dto.vehicle.VehicleFilterDto
import org.onishkovvalery.vehicleservice.model.entity.Coordinates
import org.onishkovvalery.vehicleservice.model.entity.enums.FuelType
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.util.LinkedMultiValueMap
import java.time.LocalDate

fun validRequest(): List<Arguments> = listOf(
    Arguments.of(
        Pair(
            VehicleFilterDto(name = "audi", model = "Q7"),
            PageRequest.of(0, 1)
        ),
        PageImpl(
            listOf(
                VehicleDto(
                    1,
                    "audi",
                    "Q7",
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
    ),
    Arguments.of(
        Pair(
            VehicleFilterDto(),
            null
        ),
        PageImpl(
            listOf(
                VehicleDto(
                    1,
                    "audi",
                    "Q7",
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
    ),
    Arguments.of(
        Pair(
            VehicleFilterDto(),
            PageRequest.of(0, 1)
        ),
        PageImpl(
            listOf(
                VehicleDto(
                    1,
                    "audi",
                    "Q7",
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
    ),
    Arguments.of(
        Pair(
            VehicleFilterDto(name = "audi"),
            PageRequest.of(0, 1)
        ),
        PageImpl(ArrayList<VehicleDto>())
    )
)

fun invalidRequest(): List<Arguments> = listOf(
    Arguments.of(LinkedMultiValueMap(mapOf("page" to listOf("-1"), "size" to listOf("10"))), 400),
    Arguments.of(LinkedMultiValueMap(mapOf("page" to listOf("0"), "size" to listOf("-1"))), 400),
    Arguments.of(LinkedMultiValueMap(mapOf("page" to listOf("0"), "size" to listOf("-1"))), 400),
    Arguments.of(LinkedMultiValueMap(mapOf("page" to listOf("0"), "size" to listOf("0"))), 400),
    Arguments.of(LinkedMultiValueMap(mapOf("page" to listOf("-1"), "size" to listOf("-1"))), 400),
    Arguments.of(LinkedMultiValueMap(mapOf("page" to listOf("0"), "size" to listOf("-1"))), 400),
    Arguments.of(LinkedMultiValueMap(mapOf("fuelType" to listOf("UNKNOWN"))), 400),
)

fun filteringAndPaginationRequest(): List<Arguments> = listOf<Arguments>()