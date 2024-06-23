package com.example.bearer.model.dto

data class Parcel(
    val description: String,
    val imageUrl: String,
    val maxWeight: Float,
    val minWeight: Float,
    val type: String,
    val vehicleType: VehicleType
)