package com.example.bearer.view.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MarkerState

// Permissions already granted in DetermineFirstScreen
@SuppressLint("MissingPermission")
fun getUserCurrentLocation(
    context: Context,
    currentLocation: MarkerState,
    cameraPositionState: CameraPositionState
) {
    val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fusedLocationProviderClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        CancellationTokenSource().token
    ).addOnSuccessListener {
        try {
            currentLocation.position = LatLng(it.latitude, it.longitude)
            cameraPositionState.position =
                CameraPosition.fromLatLngZoom(LatLng(it.latitude, it.longitude), 15f)
        } catch (nullPointerException: NullPointerException) {
            Toast.makeText(context, "Make sure your GPS is on", Toast.LENGTH_LONG)
                .show()
        }
    }.addOnFailureListener { exception ->
        Toast.makeText(
            context,
            exception.message ?: "Something went wrong",
            Toast.LENGTH_LONG
        ).show()
    }
}