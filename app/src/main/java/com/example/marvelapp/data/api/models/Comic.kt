package com.example.marvelapp.data.api.models

data class Comic(
    val id: Int,
    val title: String,
    val format: String,
    val pageCount: Int,
    val thumbnail: Thumbnail
)
