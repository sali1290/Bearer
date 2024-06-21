package com.example.bearer.view.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource

@SuppressLint("MissingPermission")
fun getUserCurrentLocation(context: Context): LatLng {
    var latLng = LatLng(0.0, 0.0)
    val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fusedLocationProviderClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        CancellationTokenSource().token
    ).addOnSuccessListener {
        latLng = LatLng(it.latitude, it.longitude)
    }.addOnFailureListener { exception ->
        Toast.makeText(
            context,
            exception.message ?: "Something went wrong",
            Toast.LENGTH_LONG
        ).show()
    }
    return latLng
}