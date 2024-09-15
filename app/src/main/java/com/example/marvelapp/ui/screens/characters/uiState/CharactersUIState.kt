package com.example.marvelapp.ui.screens.characters.uiState

import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.data.api.models.Characters

data class CharactersUIState(
    val listCharacters: List<Character> = emptyList(),
    val isLoading: Boolean = true
)
