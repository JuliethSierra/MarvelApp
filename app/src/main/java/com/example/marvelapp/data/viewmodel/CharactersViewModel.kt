package com.example.marvelapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.api.retrofit.RetrofitService
import com.example.marvelapp.ui.screens.characters.uiState.CharactersUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUIState())
    val uiState: StateFlow<CharactersUIState> = _uiState.asStateFlow()

    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }

    init {
        viewModelScope.launch {
            Log.d("AndroidRuntime", "Start init vm")
            getCharacters()
        }
    }

    private suspend fun getCharacters() {
        val response = retrofitApi.getAllCharacters(offset = "0")
        Log.d("AndroidRuntime", response.toString())
        Log.d("AndroidRuntime", "Characters ${response.data}")
        Log.d("AndroidRuntime", "List characters ${response.data.results}")

        _uiState.value = _uiState.value.copy(
            listCharacters = response.data.results,
            isLoading = false
        )
    }


}