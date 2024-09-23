package com.example.marvelapp.data.api.models

data class CharacterComics(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicItem>,
    val returned: Int
)
