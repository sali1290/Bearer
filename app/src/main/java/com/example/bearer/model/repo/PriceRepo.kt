package com.example.bearer.model.repo

import androidx.compose.runtime.MutableState
import com.example.bearer.model.dto.PriceRequest
import com.example.bearer.model.dto.PriceResponse

interface PriceRepo {
    fun pricing(priceRequest: PriceRequest, priceResponse: MutableState<PriceResponse?>)
}