package com.example.bearer.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OriginMenu() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomButton(
                text = "Choose origin",
                modifier = Modifier.fillMaxWidth(0.83f)
            ) {}
            Spacer(modifier = Modifier.padding(10.dp))
            CurrentLocationButton {}
        }
    }
}