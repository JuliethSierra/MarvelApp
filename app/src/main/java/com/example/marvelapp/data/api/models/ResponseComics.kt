package com.example.marvelapp.data.api.models

data class ResponseComics(
    val attributionHTML: String,
    val attributionText: String,
    val coe: Int,
    val copyright: String,
    val `data`: ComicUrl,
    val etag: String,
    val status: String
)