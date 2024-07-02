package com.example.bearer.model.utils

import com.example.bearer.model.dto.PriceResponse
import com.example.bearer.model.dto.TransportInfo

fun convertHttpCallResponseToPriceResponse(httpsCallableResult: Any?): PriceResponse {
    val hashMap = httpsCallableResult as HashMap<*, *>

    // Check if any transport type is null or not
    val walking: TransportInfo = checkTransportIsNullOrNot(hashMap, "walking")
    val riding: TransportInfo = checkTransportIsNullOrNot(hashMap, "riding")
    val cycling: TransportInfo = checkTransportIsNullOrNot(hashMap, "cycling")

    return PriceResponse(
        status = hashMap["status"].toString(),
        code = hashMap["code"].toString().toInt(),
        walking = walking,
        riding = riding,
        cycling = cycling
    )
}

private fun checkTransportIsNullOrNot(hashMap: HashMap<*, *>, transport: String): TransportInfo {
    return if (hashMap[transport] != null) {
        TransportInfo(
            price = (hashMap[transport] as HashMap<*, *>)["price"].toString(),
            distance = (hashMap[transport] as HashMap<*, *>)["distance"].toString(),
            time = (hashMap[transport] as HashMap<*, *>)["time"].toString(),
            duration = (hashMap[transport] as HashMap<*, *>)["duration"].toString(),
            type = (hashMap[transport] as HashMap<*, *>)["type"].toString(),
            length = (hashMap[transport] as HashMap<*, *>)["length"].toString()
        )
    } else {
        TransportInfo(
            price = "",
            distance = "",
            time = "",
            duration = "",
            type = "",
            length = ""
        )
    }
}