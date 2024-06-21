package com.example.bearer.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.bearer.view.component.PermissionDialog
import com.example.bearer.view.screen.MapScreen
import com.example.bearer.view.theme.BearerTheme
import com.example.bearer.view.utils.checkLocationPermissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BearerTheme {
                DetermineFirstScreen()
            }
        }
    }
}

@Composable
fun DetermineFirstScreen() {
    var permissionsAreGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        permissionsAreGranted = checkLocationPermissions(context)
    }

    if (permissionsAreGranted)
        MapScreen()
    else
        PermissionDialog {
            permissionsAreGranted = true
        }
}