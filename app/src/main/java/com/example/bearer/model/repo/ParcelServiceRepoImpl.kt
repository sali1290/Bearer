package com.example.bearer.model.repo

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.bearer.model.dto.Parcel
import com.example.bearer.model.dto.VehicleType
import com.example.bearer.model.utils.Keys
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import javax.inject.Inject

class ParcelServiceRepoImpl @Inject constructor() : ParcelServiceRepo {
    override fun getParcels(parcels: SnapshotStateList<Parcel>) {
        val db = Firebase.firestore
        parcels.clear()
        db.collection("bearerParcels").get().addOnSuccessListener { documents ->
            for (document in documents) {
                Log.d("firestore answer", "${document.id} -> ${document.data}")
                parcels.add(
                    Parcel(
                        description = document.data.getValue(Keys.DESCRIPTION).toString(),
                        imageUrl = document.data.getValue(Keys.IMAGE_URL).toString(),
                        maxWeight = document.data.getValue(Keys.MAX_WEIGHT).toString().toFloat(),
                        minWeight = document.data.getValue(Keys.MIN_WEIGHT).toString().toFloat(),
                        type = document.data.getValue(Keys.TYPE).toString(),
                        vehicleType = VehicleType(
                            bicycling = (document.data.getValue(Keys.VEHICLE_TYPE) as HashMap<*, *>)[Keys.BICYCLING].toString()
                                .toBoolean(),
                            driving = (document.data.getValue(Keys.VEHICLE_TYPE) as HashMap<*, *>)[Keys.DRIVING].toString()
                                .toBoolean(),
                            walking = (document.data.getValue(Keys.VEHICLE_TYPE) as HashMap<*, *>)[Keys.WALKING].toString()
                                .toBoolean()
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