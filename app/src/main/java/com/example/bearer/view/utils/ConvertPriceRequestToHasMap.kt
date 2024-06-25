package com.example.bearer.view.utils

import com.example.bearer.model.dto.PriceRequest
import java.io.Serializable

fun convertPriceRequestToHasMap(priceRequest: PriceRequest): HashMap<String, Serializable> {
    val data = hashMapOf(
        "origin" to hashMapOf(
            "lat" to priceRequest.origin.latitude,
            "lng" to priceRequest.origin.longitude
        ),
        "destination" to hashMapOf(
            "lat" to priceRequest.destination.latitude,
            "lng" to priceRequest.destination.longitude
        ),
        "vehicle_type" to hashMapOf(
            "walking" to priceRequest.vehicleType.driving,
            "walking" to priceRequest.vehicleType.walking,
            "cycling" to priceRequest.vehicleType.bicycling
        ),
        "parcel_type" to priceRequest.type,
        "parcel_description" to priceRequest.description,
        "parcel_min_weight" to priceRequest.minWeight,
        "parcel_max_weight" to priceRequest.maxWeight,
    )
    return data
}