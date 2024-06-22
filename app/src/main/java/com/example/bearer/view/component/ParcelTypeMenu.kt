package com.example.bearer.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bearer.view.theme.DarkPurple
import com.example.bearer.view.theme.LightPurple

@Composable
fun ParcelTypeMenu(
    onBackClickListener: () -> Unit,
    onNextClickListener: () -> Unit,
    isNextButtonEnabled: Boolean
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
        ) {
            Box(
                modifier = Modifier
                    .background(color = LightPurple)
                    .weight(0.15f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Choose the parcel type",
                    color = Color.White,
                )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(0.8f)
                    .background(color = DarkPurple)
            ) {
                items(3) {
                    ParcelItem(
                        imageUrl = "",
                        title = "Envelop",
                        weight = "0.5 kg- 1.5 kg",
                        description = "34 x 27 x 4 cm max"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .background(color = LightPurple)
                    .fillMaxWidth()
                    .weight(0.25f)
                    .padding(bottom = 40.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButton(
                    text = "Back",
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .fillMaxHeight(0.7f),
                    onClick = onBackClickListener
                )
                Spacer(modifier = Modifier.padding(15.dp))
                CustomButton(
                    text = "Next",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.7f),
                    enabled = isNextButtonEnabled,
                    onClick = onNextClickListener
                )
            }
        }
    }

}