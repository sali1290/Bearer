package com.example.bearer.model.repo

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.bearer.model.dto.Parcel

interface ParcelServiceRepo {
    fun getParcels(parcels: SnapshotStateList<Parcel>)
}