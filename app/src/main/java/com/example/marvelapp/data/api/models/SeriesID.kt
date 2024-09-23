package com.example.marvelapp.data.api.models

data class SeriesID(
    val id: Int,
    val title: String,
    val startYear: Int,
    val endYear : Int,
    val thumbnail: Thumbnail,
)
