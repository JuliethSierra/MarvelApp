package com.example.marvelapp.data.api.models

import com.google.gson.annotations.SerializedName

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    @SerializedName("comics")
    val characterComics: CharacterComics,
    @SerializedName("series")
    val characterSeries: CharacterSeries
)
