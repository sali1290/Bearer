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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bearer.R
import com.example.bearer.model.dto.Parcel
import com.example.bearer.view.theme.DarkPurple
import com.example.bearer.view.theme.LightPurple

@Composable
fun ParcelTypeMenu(
    parcels: SnapshotStateList<Parcel>,
    onBackClickListener: () -> Unit,
    onNextClickListener: () -> Unit,
    onItemClickListener: (Int) -> Unit,
    isNextButtonEnabled: Boolean
) {
    var selectedItem by remember { mutableIntStateOf(-1) }

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
                    text = stringResource(R.string.choose_the_parcel_type),
                    color = Color.White,
                )
            }
            if (parcels.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(0.8f)
                        .background(color = DarkPurple)
                ) {
                    itemsIndexed(parcels) { index, item ->
                        ParcelItem(
                            imageUrl = "",
                            title = item.type,
                            weight = "${item.minWeight} kg - ${item.maxWeight} kg",
                            description = item.description,
                            selected = selectedItem == index
                        ) {
                            selectedItem = index
                            onItemClickListener(selectedItem)
                        }
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
                    text = stringResource(R.string.back),
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .fillMaxHeight(0.83f),
                    onClick = onBackClickListener
                )
                Spacer(modifier = Modifier.padding(15.dp))
                CustomButton(
                    text = stringResource(R.string.next),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.83f),
                    enabled = isNextButtonEnabled,
                    onClick = onNextClickListener
                )
            }
        }
    }

}