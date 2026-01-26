package org.onishkovvalery.shopservice.ejb

import jakarta.ejb.Remote

@Remote
interface ShopRemote {
    fun getVehiclesByFuelType(fuelType: String?, pageNumber: Int, pageSize: Int): String?
    fun resetVehicleDistance(vehicleId: Long): String?
}
