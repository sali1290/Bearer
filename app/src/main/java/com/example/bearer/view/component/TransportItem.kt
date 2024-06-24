package com.example.bearer.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bearer.view.theme.Blue

@Composable
fun TransportItem(imageId: Int) {

    Column(
        modifier = Modifier
            .width(120.dp)
            .height(120.dp)
            .background(color = Blue, shape = RoundedCornerShape(size = 20.dp))
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "transport",
            modifier = Modifier.weight(0.50f)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "12.0$ - 18.0$", color = Color.White, modifier = Modifier.weight(0.25f))
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "15 - 20 min", color = Color.White, modifier = Modifier.weight(0.25f))
    }
}