package com.example.bearer.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bearer.R
import com.example.bearer.view.theme.Purple

@Composable
fun TransportMenu(
    onBackClickListener: () -> Unit,
    onConfirmTransportClickListener: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .background(color = Purple)
                .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.horizontalScroll(state = rememberScrollState())) {
                TransportItem(imageId = R.drawable.motorcycle)
                Spacer(modifier = Modifier.width(10.dp))
                TransportItem(imageId = R.drawable.bike)
                Spacer(modifier = Modifier.width(10.dp))
                TransportItem(imageId = R.drawable.walk)
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton(
                    text = stringResource(id = R.string.back),
                    modifier = Modifier.fillMaxWidth(0.25f),
                    onClick = onBackClickListener
                )
                Spacer(modifier = Modifier.padding(10.dp))
                CustomButton(
                    text = stringResource(R.string.choose_destination),
                    modifier = Modifier.fillMaxWidth(0.90f),
                    onClick = onConfirmTransportClickListener
                )
            }
        }
    }
}