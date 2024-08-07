package com.example.bearer.view.screen

import androidx.collection.emptyObjectLongMap
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bearer.R
import com.example.bearer.model.dto.Parcel
import com.example.bearer.model.dto.PriceRequest
import com.example.bearer.model.dto.PriceResponse
import com.example.bearer.view.component.CustomMarker
import com.example.bearer.view.component.DestinationMenu
import com.example.bearer.view.component.OriginMenu
import com.example.bearer.view.component.ParcelTypeMenu
import com.example.bearer.view.component.TransportMenu
import com.example.bearer.view.utils.calculateMiddlePosition
import com.example.bearer.view.utils.getUserCurrentLocation
import com.example.bearer.viewmodel.ParcelViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen() {
    val context = LocalContext.current
    val parcels = remember { mutableStateListOf<Parcel>() }
    val priceResponse = remember { mutableStateOf<PriceResponse?>(null) }
    val parcelViewModel: ParcelViewModel = hiltViewModel()
    val tehran = LatLng(35.7219, 51.3347)
    val cameraPositionState =
        rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(tehran, 15f) }

    var step by remember { mutableIntStateOf(0) }
    val origin = rememberMarkerState(position = tehran)
    val destination = rememberMarkerState(position = tehran)
    var parcelIndex by remember { mutableIntStateOf(-1) }
    var transportIndex by remember { mutableIntStateOf(-1) }

    // Make markers follow center of the user screen
    LaunchedEffect(key1 = cameraPositionState.position) {
        if (step == 0) {
            origin.position = cameraPositionState.position.target
            destination.position = cameraPositionState.position.target
        }

        if (step == 1)
            destination.position = cameraPositionState.position.target
    }

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

        if (step == 2) {
            // Calculates middle position of polyline and sets it as center of map
            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                calculateMiddlePosition(origin.position, destination.position), 13.5f
            )
            Polyline(points = listOf(origin.position, destination.position))
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
                onConfirmLocationClickListener = {
                    parcelViewModel.getParcels(parcels)
                    step++
                }
            )

            2 -> {
                ParcelTypeMenu(
                    parcels = parcels,
                    isNextButtonEnabled = parcelIndex != -1,
                    onBackClickListener = { step-- },
                    onItemClickListener = { selectedItem ->
                        parcelIndex = selectedItem
                    },
                    onNextClickListener = {
                        val request = PriceRequest(
                            origin = origin.position,
                            destination = destination.position,
                            vehicleType = parcels[parcelIndex].vehicleType,
                            description = parcels[parcelIndex].description,
                            minWeight = parcels[parcelIndex].minWeight,
                            maxWeight = parcels[parcelIndex].maxWeight,
                            type = parcels[parcelIndex].type
                        )
                        parcelViewModel.pricing(request, priceResponse)
                        step++
                    }
                )
            }

            3 -> {
                TransportMenu(
                    priceResponse = priceResponse.value,
                    onBackClickListener = { step-- },
                    isNextButtonEnabled = transportIndex != -1,
                    onItemClickListener = { index ->
                        transportIndex = index
                    },
                    onNextClickListener = {}
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MapScreenPreview(modifier: Modifier = Modifier) {
    MapScreen()
}