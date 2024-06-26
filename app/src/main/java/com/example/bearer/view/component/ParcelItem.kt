package com.example.bearer.view.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bearer.view.theme.DarkPurple
import com.google.firebase.Firebase
import com.google.firebase.storage.storage

@Composable
fun ParcelItem(
    imageUrl: String,
    title: String,
    weight: String,
    description: String,
    selected: Boolean = false,
    onItemClicked: () -> Unit
) {
    var imageHttpUrl by remember { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        val storageRef = Firebase.storage("gs://uber4things.appspot.com").reference
        val pathReference = storageRef.child(imageUrl)

        pathReference.downloadUrl.addOnSuccessListener { result ->
            Log.d("firebase storage", result.toString())
            imageHttpUrl = result.toString()
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = DarkPurple)
            .height(80.dp)
            .clickable { onItemClicked() }
            .padding(5.dp)
            .border(width = 1.dp, if (selected) Color.LightGray else DarkPurple),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(0.3f)) {
            AsyncImage(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                model = imageHttpUrl,
                contentDescription = "parcel image"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = title, color = Color.White)
        }
        Column(
            modifier = Modifier.weight(0.3f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = weight, color = Color.White, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = description, color = Color.White, fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParcelItemPreview(modifier: Modifier = Modifier) {
    ParcelItem(
        imageUrl = "",
        title = "Envelop",
        weight = "0.5 kg- 1.5 kg",
        description = "34 x 27 x 4 cm max"
    ) {}
}