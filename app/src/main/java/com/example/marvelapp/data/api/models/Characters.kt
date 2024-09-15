package com.example.marvelapp.data.api.models

data class Characters(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Character>,
    val total: Int
)
