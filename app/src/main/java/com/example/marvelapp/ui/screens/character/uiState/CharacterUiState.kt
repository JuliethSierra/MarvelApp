package com.example.marvelapp.ui.screens.character.uiState

import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.data.api.models.Comic

data class CharacterUiState(
    val listCharacters: List<Character> = emptyList(),
    val character: Character? = null,
    val comics: List<Comic> = emptyList(),
    val isCharacterLoading: Boolean = true,
    val isComicListLoading: Boolean = true,
)
