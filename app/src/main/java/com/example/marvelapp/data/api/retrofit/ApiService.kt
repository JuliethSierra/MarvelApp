package com.example.marvelapp.data.api.retrofit

import com.example.marvelapp.data.api.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String = RetrofitService.API_KEY,
        @Query("ts") ts: String = RetrofitService.timestamp,
        @Query("hash") hash: String = RetrofitService.hash(),
        @Query("offset") offset: String
    ): Response
}