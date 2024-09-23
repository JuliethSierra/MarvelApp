package com.example.marvelapp.data.api.retrofit

import com.example.marvelapp.data.api.models.Response
import com.example.marvelapp.data.api.models.ResponseCharacter
import com.example.marvelapp.data.api.models.ResponseComics
import com.example.marvelapp.data.api.models.ResponseSeries
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String = RetrofitService.API_KEY,
        @Query("ts") ts: String = RetrofitService.timestamp,
        @Query("hash") hash: String = RetrofitService.hash(),
        @Query("offset") offset: String
    ): Response

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") id: Int,  // Mueve el @Path antes de los @Query
        @Query("apikey") apikey: String = RetrofitService.API_KEY,
        @Query("ts") ts: String = RetrofitService.timestamp,
        @Query("hash") hash: String = RetrofitService.hash()
    ): ResponseCharacter

    @GET("/v1/public/comics/{comicId}")
    suspend fun getComicById(
        @Path("comicId") id: Int,
        @Query("apikey") apikey: String = RetrofitService.API_KEY,
        @Query("ts") ts: String = RetrofitService.timestamp,
        @Query("hash") hash: String = RetrofitService.hash()
    ): ResponseComics

    @GET("/v1/public/series/{seriesId}")
    suspend fun getSeriesById(
        @Path("seriesId") id: Int,
        @Query("apikey") apikey: String = RetrofitService.API_KEY,
        @Query("ts") ts: String = RetrofitService.timestamp,
        @Query("hash") hash: String = RetrofitService.hash()
    ): ResponseSeries
}