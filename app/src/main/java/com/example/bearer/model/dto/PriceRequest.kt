package com.example.bearer.model.dto

import com.google.android.gms.maps.model.LatLng

data class PriceRequest(
    val origin: LatLng,
    val destination: LatLng,
    val vehicleType: VehicleType,
    val type: String,
    val description: String,
    val minWeight: Float,
    val maxWeight: Float
)