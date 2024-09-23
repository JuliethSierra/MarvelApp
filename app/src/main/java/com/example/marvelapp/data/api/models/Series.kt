package com.example.marvelapp.data.api.models

data class Series(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<SeriesID>,
    val total: Int
)
