package com.example.bearer.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bearer.R
import com.example.bearer.view.component.OriginMenu
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen() {
    val tehran = LatLng(35.7219, 51.3347)
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(tehran, 15f)
        }
    val markerState = rememberMarkerState()
    var isMarkerVisible by remember { mutableStateOf(true) }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.TERRAIN),
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        onMapClick = { latLng ->
            markerState.position = latLng
            cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
            isMarkerVisible = true
        }
    ) {
        ShowMarker(markerState = markerState, isMarkerVisible = isMarkerVisible)
    }
    OriginMenu(onCurrentLocationClickListener = {}, onConfirmLocationClickListener = {})
}

@Composable
fun ShowMarker(markerState: MarkerState, isMarkerVisible: Boolean) {
    if (isMarkerVisible) {
        MarkerComposable(
            state = MarkerState(position = markerState.position),
        ) {
            Image(
                painter = painterResource(id = R.drawable.origin_pin),
                contentDescription = "",
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MapScreenPreview(modifier: Modifier = Modifier) {
    MapScreen()
}