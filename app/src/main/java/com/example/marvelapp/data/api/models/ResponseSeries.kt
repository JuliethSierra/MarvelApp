package com.example.marvelapp.data.api.models

data class ResponseSeries(
    val attributionHTML: String,
    val attributionText: String,
    val coe: Int,
    val copyright: String,
    val `data`: Series,
    val etag: String,
    val status: String
)
