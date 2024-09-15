package com.example.marvelapp.data.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object RetrofitService {

    private var apiService: ApiService? = null
    private const val BASE_URL = "https://gateway.marvel.com"
    val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
    const val API_KEY = "19dd8447d2a8a8d5d96cd32093caca32"
    const val PRIVATE_KEY = "9eae2e0f8e998fd892d55708dbb3964595ce0816"
    const val limit = "20"
    fun hash():String{
        val input = "$timestamp$PRIVATE_KEY$API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun getInstance(): ApiService =
        apiService ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

}