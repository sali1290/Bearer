package com.example.bearer.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bearer.R
import com.example.bearer.view.theme.Blue

@Composable
fun OriginMenu(
    onCurrentLocationClickListener: () -> Unit,
    onConfirmLocationClickListener: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 50.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomButton(
                text = stringResource(R.string.choose_origin),
                modifier = Modifier.fillMaxWidth(0.83f),
                onClick = onConfirmLocationClickListener
            )

            Spacer(modifier = Modifier.padding(10.dp))

            IconButton(
                modifier = Modifier.background(color = Blue, shape = CircleShape),
                onClick = onCurrentLocationClickListener
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gps),
                    contentDescription = "gps icon"
                )
            }
        }
    }
}