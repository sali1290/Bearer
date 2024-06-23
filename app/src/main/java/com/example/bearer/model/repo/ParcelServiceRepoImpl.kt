package com.example.bearer.model.repo

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.bearer.model.dto.Parcel
import com.example.bearer.model.dto.VehicleType
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import javax.inject.Inject

class ParcelServiceRepoImpl @Inject constructor() : ParcelServiceRepo {
    override fun getParcels(parcels: SnapshotStateList<Parcel>) {
        val db = Firebase.firestore
        db.collection("parcelBearers").get().addOnSuccessListener { documents ->
            for (document in documents) {
                parcels.add(
                    Parcel(
                        description = document.data.getValue("description").toString(),
                        imageUrl = document.data.getValue("imageUrl").toString(),
                        maxWeight = document.data.getValue("maxWeight").toString().toFloat(),
                        minWeight = document.data.getValue("minWeight").toString().toFloat(),
                        type = document.data.getValue("type").toString(),
                        vehicleType = VehicleType(
                            bicycling = true,
                            driving = true,
                            walking = true
                        )
                    )
                )
            }
        }
            .addOnFailureListener {
                parcels.clear()
            }
    }
}