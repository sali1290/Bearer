package com.example.bearer.model.repo

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.bearer.model.dto.PriceRequest
import com.example.bearer.model.dto.PriceResponse
import com.example.bearer.model.utils.convertHttpCallResponseToPriceResponse
import com.example.bearer.view.utils.convertPriceRequestToHasMap
import com.google.firebase.functions.FirebaseFunctions
import javax.inject.Inject


class PriceRepoImpl @Inject constructor() : PriceRepo {
    override fun pricing(priceRequest: PriceRequest, priceResponse: MutableState<PriceResponse?>) {
        val functions = FirebaseFunctions.getInstance()
        functions.getHttpsCallable("pricing").call(convertPriceRequestToHasMap(priceRequest))
            .continueWith { task ->
                val result = task.result?.data
                result
            }
            .addOnFailureListener { exception ->
                Log.d("function answer", exception.message ?: "Something went wrong.")
            }
            .addOnSuccessListener { response ->
                try {
                    Log.d("function answer", response.toString())
                    priceResponse.value = convertHttpCallResponseToPriceResponse(response)
                } catch (exception: Exception) {
                    Log.d("function answer", exception.message ?: "Something went wrong.")
                }
            }
    }
}