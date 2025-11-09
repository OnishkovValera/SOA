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

fun validCreateRequestData(): List<Arguments> = listOf(
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = 1F
            val y = 2.0
        }
        val enginePower = 10.1F
        val numberOfWheels = 4
        val distanceTravelled = 5
        val fuelType = FuelType.DIESEL
    }),
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = 61F
            val y = 2.0
        }
        val enginePower = 1F
        val numberOfWheels = 1
        val distanceTravelled = 1
        val fuelType = FuelType.DIESEL
    })
)

fun invalidCreateRequestData(): List<Any?> = listOf(
    Arguments.of(object {
        val name = ""
        val model = "Q7"
        val coordinate = object {
            val x = 1F
            val y = 2.0
        }
        val enginePower = 10.1F
        val numberOfWheels = 4
        val distanceTravelled = 5
        val fuelType = FuelType.DIESEL
    }),
    Arguments.of(object {
        val name = "   "
        val model = "Q7"
        val coordinate = object {
            val x = 1F
            val y = 2.0
        }
        val enginePower = 10.1F
        val numberOfWheels = 4
        val distanceTravelled = 5
        val fuelType = FuelType.DIESEL
    }),
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = 62F
            val y = 2.0
        }
        val enginePower = 10F
        val numberOfWheels = 4
        val distanceTravelled = 1
        val fuelType = FuelType.DIESEL
    }),


    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = null
            val y = 2.0
        }
        val enginePower = 1F
        val numberOfWheels = 4
        val distanceTravelled = 4
        val fuelType = FuelType.DIESEL
    }),
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = 1F
            val y = 2.0
        }
        val enginePower = null
        val numberOfWheels = 4
        val distanceTravelled = 5
        val fuelType = FuelType.DIESEL
    }),
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = 1F
            val y = 2.0
        }
        val enginePower = -10F
        val numberOfWheels = 4
        val distanceTravelled = 5
        val fuelType = FuelType.DIESEL
    }),
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = 1F
            val y = 2.0
        }
        val enginePower = 10.1F
        val numberOfWheels = -20
        val distanceTravelled = 5
        val fuelType = FuelType.DIESEL
    }),
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = 1F
            val y = 2.0
        }
        val enginePower = 10.1F
        val numberOfWheels = 4
        val distanceTravelled = -200
        val fuelType = FuelType.DIESEL
    }),
    Arguments.of(null),
    Arguments.of(object {
        val name = null
        val model = null
        val coordinate = object {
            val x = null
            val y = null
        }
        val enginePower = null
        val numberOfWheels = null
        val distanceTravelled = null
        val fuelType = null
    }),
)

fun validUpdateRequestData(): List<Arguments> = listOf(
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = 1F
            val y = 2.0
        }
        val enginePower = 10.1F
        val numberOfWheels = 4
        val distanceTravelled = 5
    }),
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = null
        val enginePower = null
        val numberOfWheels = null
        val distanceTravelled = null
    }),
)

fun invalidUpdateRequestData(): List<Arguments> = listOf(
    Arguments.of(object {
        val name = ""
        val model = "Q7"
        val coordinate = object {
            val x = 62F
            val y = 2.0
        }
        val enginePower = -10.1F
        val numberOfWheels = 4
        val distanceTravelled = 5
    }),
    Arguments.of(object {
        val name = "audi"
        val model = "Q7"
        val coordinate = object {
            val x = null
            val y = 2.0
        }
        val enginePower = 10.1F
        val numberOfWheels = -4
        val distanceTravelled = -5
    }),

)