package com.example.bearer.view.component

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun PermissionDialog(onPermissionGranted: () -> Unit) {

    val locationPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permission ->
            var isGranted = true
            permission.entries.forEach {
                if (!it.value) {
                    isGranted = false
                    return@forEach
                }
            }
            if (isGranted) {
                onPermissionGranted()
            }
        }

    LaunchedEffect(key1 = Unit) {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    // locationPermissionLauncher dialog is not going to show again after we deny that
    Dialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(size = 10.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Location permission required", fontSize = 18.sp)
        }
    }
}