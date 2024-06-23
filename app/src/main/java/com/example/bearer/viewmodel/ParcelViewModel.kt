package com.example.bearer.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.bearer.model.dto.Parcel
import com.example.bearer.model.repo.ParcelServiceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ParcelViewModel @Inject constructor(private val parcelServiceRepo: ParcelServiceRepo) :
    ViewModel() {

    fun getParcels(parcels: SnapshotStateList<Parcel>) = parcelServiceRepo.getParcels(parcels)

}