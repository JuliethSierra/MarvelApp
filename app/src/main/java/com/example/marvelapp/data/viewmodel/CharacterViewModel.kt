package com.example.marvelapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.data.api.models.Comic
import com.example.marvelapp.data.api.models.ResponseCharacter
import com.example.marvelapp.data.api.retrofit.RetrofitService
import com.example.marvelapp.ui.screens.character.uiState.CharacterUiState
import com.example.marvelapp.utils.getIdFromUrl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CharacterUiState())
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }

    fun getCharacterInfo(id: Int) {
        viewModelScope.launch {
            val responseCharacter = retrofitApi.getCharacterById(id = id)
            Log.d("AndroidRuntime", responseCharacter.toString())
            Log.d("AndroidRuntime", "Characters ${responseCharacter.data}")
            Log.d("AndroidRuntime", "List characters ${responseCharacter.data.results}")
            val newUiState = _uiState.value.copy(
                //Se trae el objeto qe se encuentra en esa lista!!!
                character = responseCharacter.data.results.first(),
                isCharacterLoading = false
            )
            _uiState.value = newUiState
            setCharactersComics(responseCharacter.data.results.first())
        }
    }

    private suspend fun setCharactersComics(character: Character) {
        val comics = arrayListOf<Comic>()
        character.comics.items
            .subList(0, character.comics.items.size / 2) // Asegúrate de que la sublista esté basada en `comics.items`
            .forEach { item ->
                val comicId = item.resourceURI.getIdFromUrl()
                comicId?.let { id ->
                    val comicResponse = retrofitApi.getComicById(id)
                    Log.d("AndroidRuntime", comicResponse.toString())
                    Log.d("AndroidRuntime", "comics ${comicResponse.data}")
                    Log.d("AndroidRuntime", "List comics ${comicResponse.data.results}")// Obtén la respuesta del cómic
                    comicResponse.data.results.forEach { comic ->
                        comics.add(comic) // Agrega cada cómic individualmente a la lista
                    }
                }
            }
        _uiState.update {
            _uiState.value.copy(
                comics = comics,
                isComicListLoading = false
            )
        }
    }

}
