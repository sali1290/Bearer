package com.example.bearer.view.utils

import com.google.android.gms.maps.model.LatLng
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


fun toRadians(degrees: Double): Double {
    return degrees * PI / 180.0
}

fun toDegrees(radians: Double): Double {
    return radians * 180.0 / PI
}

fun calculateMiddlePosition(
    origin: LatLng, destination: LatLng
): LatLng {
    // Convert degrees to radians
    val lat1 = toRadians(origin.latitude)
    val lon1 = toRadians(origin.longitude)
    val lat2 = toRadians(destination.latitude)
    val lon2 = toRadians(destination.longitude)

    // Compute Bx and By
    val bx = cos(lat2) * cos(lon2 - lon1)
    val by = cos(lat2) * sin(lon2 - lon1)

    // Compute midLat and midLon in radians
    val midLat = atan2(sin(lat1) + sin(lat2), sqrt((cos(lat1) + bx) * (cos(lat1) + bx) + by * by))
    val midLon = lon1 + atan2(by, cos(lat1) + bx)

    // Convert radians back to degrees
    val midLatDeg = toDegrees(midLat)
    val midLonDeg = toDegrees(midLon)

    return LatLng(midLatDeg, midLonDeg)
}
