package com.example.bearer.view.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState

@Composable
fun CustomMarker(markerState: MarkerState, iconId: Int) {
    MarkerComposable(
        state = MarkerState(position = markerState.position),
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = "marker icon",
        )
    }
}