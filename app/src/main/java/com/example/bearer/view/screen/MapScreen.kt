package com.example.bearer.view.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.bearer.R
import com.example.bearer.view.component.CustomMarker
import com.example.bearer.view.component.OriginMenu
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
    val markerState = rememberMarkerState()


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.TERRAIN),
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        onMapClick = { latLng ->
            markerState.position = latLng
            cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
        }
    ) {
        CustomMarker(
            markerState = markerState,
            iconId = R.drawable.origin_pin
        )
    }
    OriginMenu(onCurrentLocationClickListener = {
        getUserCurrentLocation(context, markerState, cameraPositionState)
    }, onConfirmLocationClickListener = {})
}

@Composable
@Preview(showBackground = true)
fun MapScreenPreview(modifier: Modifier = Modifier) {
    MapScreen()
}