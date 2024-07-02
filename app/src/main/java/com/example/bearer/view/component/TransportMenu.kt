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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bearer.R
import com.example.bearer.model.dto.PriceResponse
import com.example.bearer.view.theme.DarkPurple
import com.example.bearer.view.theme.Purple

@Composable
fun TransportMenu(
    priceResponse: PriceResponse?,
    onBackClickListener: () -> Unit,
    isNextButtonEnabled: Boolean,
    onItemClickListener: (Int) -> Unit,
    onNextClickListener: () -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(-1) }
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
            if (priceResponse != null) {
                LazyRow {
                    items(3) { index ->
                        when (index) {
                            0 -> TransportItem(
                                imageId = R.drawable.motorcycle,
                                price = priceResponse.riding.price,
                                time = priceResponse.riding.time,
                                selected = selectedItem == 0
                            ) {
                                selectedItem = 0
                                onItemClickListener(selectedItem)
                            }

                            1 -> TransportItem(
                                imageId = R.drawable.bike,
                                price = priceResponse.cycling.price,
                                time = priceResponse.cycling.time,
                                selected = selectedItem == 1
                            ) {
                                selectedItem = 1
                                onItemClickListener(selectedItem)
                            }

                            2 -> TransportItem(
                                imageId = R.drawable.walk,
                                price = priceResponse.walking.price,
                                time = priceResponse.walking.time,
                                selected = selectedItem == 2
                            ) {
                                selectedItem = 2
                                onItemClickListener(selectedItem)
                            }

                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.8f)
                        .background(color = DarkPurple),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
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
                CustomButton(
                    text = stringResource(R.string.next),
                    modifier = Modifier.fillMaxWidth(0.90f),
                    enabled = isNextButtonEnabled,
                    onClick = onNextClickListener
                )
            }
        }
    }
}