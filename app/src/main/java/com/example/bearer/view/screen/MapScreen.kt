package com.example.bearer.view.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.bearer.R
import com.example.bearer.view.component.CustomMarker
import com.example.bearer.view.component.DestinationMenu
import com.example.bearer.view.component.OriginMenu
import com.example.bearer.view.component.ParcelTypeMenu
import com.example.bearer.view.utils.getUserCurrentLocation
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen() {
    val context = LocalContext.current
    val tehran = LatLng(35.7219, 51.3347)
    val cameraPositionState =
        rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(tehran, 15f) }

    var step by remember { mutableIntStateOf(0) }
    val origin = rememberMarkerState(position = tehran)
    val destination = rememberMarkerState(position = tehran)

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.TERRAIN),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            rotationGesturesEnabled = step < 2,
            scrollGesturesEnabled = step < 2,
            scrollGesturesEnabledDuringRotateOrZoom = step < 2,
            zoomGesturesEnabled = step < 2
        ),
        onMapClick = { latLng ->
            when (step) {
                0 -> {
                    origin.position = latLng
                    destination.position = latLng
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                }

                1 -> {
                    destination.position = latLng
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                }
            }
        }
    ) {
        CustomMarker(
            markerState = origin,
            iconId = R.drawable.origin_pin
        )
        if (step > 0) {
            CustomMarker(
                markerState = destination,
                iconId = R.drawable.destination_pin
            )
        }
    }

    AnimatedContent(targetState = step, label = "Menus") { targetState ->
        when (targetState) {
            0 -> OriginMenu(onCurrentLocationClickListener = {
                getUserCurrentLocation(context, origin, cameraPositionState)
            }, onConfirmLocationClickListener = { step++ })

            1 -> DestinationMenu(
                onBackClickListener = { step-- },
                onCurrentLocationClickListener = {
                    getUserCurrentLocation(
                        context,
                        destination,
                        cameraPositionState
                    )
                },
                onConfirmLocationClickListener = { step++ }
            )

            2 -> {
                ParcelTypeMenu(
                    isNextButtonEnabled = false,
                    onBackClickListener = { step-- },
                    onNextClickListener = { /* Todo */ })
            }


        }
    }
}

@Composable
@Preview(showBackground = true)
fun MapScreenPreview(modifier: Modifier = Modifier) {
    MapScreen()
}