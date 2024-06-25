package com.example.bearer.model.dto

data class PriceResponse(
    val status: String,
    val code: Int,
    val walking: TransportInfo,
    val riding: TransportInfo,
    val cycling: TransportInfo
)