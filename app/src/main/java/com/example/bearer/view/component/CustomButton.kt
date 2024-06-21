package com.example.bearer.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.bearer.R
import com.example.bearer.view.theme.Blue

@Composable
fun CustomButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color.White)
        }
    }
}

@Composable
fun CurrentLocationButton(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier.background(color = Blue, shape = CircleShape),
        onClick = onClick
    ) {
        Icon(painter = painterResource(id = R.drawable.gps), contentDescription = "gps icon")
    }
}