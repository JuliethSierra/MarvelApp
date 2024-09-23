package com.example.marvelapp.data.api.models

data class Comics(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comic>
)
