package com.example.convertationapp.data.api

import com.example.convertationapp.domain.entities.CurrenciesList
import retrofit2.http.GET

interface ApiService {

    @GET("daily_json.js")
    suspend fun getCurrenciesList(): CurrenciesList
}