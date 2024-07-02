package com.example.bearer.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.bearer.model.dto.Parcel
import com.example.bearer.model.dto.PriceRequest
import com.example.bearer.model.dto.PriceResponse
import com.example.bearer.model.repo.ParcelServiceRepo
import com.example.bearer.model.repo.PriceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ParcelViewModel @Inject constructor(
    private val parcelServiceRepo: ParcelServiceRepo,
    private val priceRepo: PriceRepo
) :
    ViewModel() {

    fun getParcels(parcels: SnapshotStateList<Parcel>) = parcelServiceRepo.getParcels(parcels)

    fun pricing(priceRequest: PriceRequest, priceResponse: MutableState<PriceResponse?>) =
        priceRepo.pricing(priceRequest, priceResponse)
}