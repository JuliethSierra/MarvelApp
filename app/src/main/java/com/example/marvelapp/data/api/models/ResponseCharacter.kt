package com.example.marvelapp.data.api.models

data class ResponseCharacter(
    val attributionHTML: String,
    val attributionText: String,
    val coe: Int,
    val copyright: String,
    val `data`: Characters,
    val etag: String,
    val status: String
)