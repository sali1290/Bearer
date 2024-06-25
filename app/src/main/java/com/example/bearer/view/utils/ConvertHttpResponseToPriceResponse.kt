package com.example.bearer.view.utils

import com.example.bearer.model.dto.PriceResponse
import com.example.bearer.model.dto.TransportInfo
import com.google.firebase.functions.HttpsCallableResult

fun convertHttpResponseToPriceResponse(httpsCallableResult: HttpsCallableResult): PriceResponse {
    val hashMap = httpsCallableResult.data as HashMap<*, *>
    return PriceResponse(
        status = hashMap["status"].toString(),
        code = hashMap["code"].toString().toInt(),
        walking = TransportInfo(
            price = (hashMap["walking"] as HashMap<*, *>)["price"].toString(),
            distance = (hashMap["walking"] as HashMap<*, *>)["distance"].toString(),
            time = (hashMap["walking"] as HashMap<*, *>)["time"].toString(),
            duration = (hashMap["walking"] as HashMap<*, *>)["duration"].toString(),
            type = (hashMap["walking"] as HashMap<*, *>)["type"].toString(),
            length = (hashMap["walking"] as HashMap<*, *>)["length"].toString()
        ),
        riding = TransportInfo(
            price = (hashMap["riding"] as HashMap<*, *>)["price"].toString(),
            distance = (hashMap["riding"] as HashMap<*, *>)["distance"].toString(),
            time = (hashMap["riding"] as HashMap<*, *>)["time"].toString(),
            duration = (hashMap["riding"] as HashMap<*, *>)["duration"].toString(),
            type = (hashMap["riding"] as HashMap<*, *>)["type"].toString(),
            length = (hashMap["riding"] as HashMap<*, *>)["length"].toString()
        ),
        cycling = TransportInfo(
            price = (hashMap["cycling"] as HashMap<*, *>)["price"].toString(),
            distance = (hashMap["cycling"] as HashMap<*, *>)["distance"].toString(),
            time = (hashMap["cycling"] as HashMap<*, *>)["time"].toString(),
            duration = (hashMap["cycling"] as HashMap<*, *>)["duration"].toString(),
            type = (hashMap["cycling"] as HashMap<*, *>)["type"].toString(),
            length = (hashMap["cycling"] as HashMap<*, *>)["length"].toString()
        )
    )
}