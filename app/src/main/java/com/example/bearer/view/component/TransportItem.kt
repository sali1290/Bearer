package com.example.bearer.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
fun TransportItem(
    imageId: Int,
    price: String,
    time: String,
    selected: Boolean = false,
    onItemClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .height(120.dp)
            .background(
                color = if (price.isEmpty() || time.isEmpty()) Color.LightGray else Blue,
                shape = RoundedCornerShape(size = 20.dp)
            )
            .border(
                width = 1.dp,
                color = if (selected) Color.LightGray else Color.Transparent
            )
            .clickable(enabled = !(price.isEmpty() || time.isEmpty())) { onItemClicked() }
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
        Text(text = price, color = Color.White, modifier = Modifier.weight(0.25f))
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = time, color = Color.White, modifier = Modifier.weight(0.25f))
    }
}