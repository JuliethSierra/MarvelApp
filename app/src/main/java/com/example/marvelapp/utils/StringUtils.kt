package com.example.marvelapp.utils

fun String.getIdFromUrl(): Int? =
    this.split("/").lastOrNull()?.toIntOrNull()
