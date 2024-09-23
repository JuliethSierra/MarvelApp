package com.example.marvelapp.data.viewmodel

import SeriesUiState
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.data.api.models.SeriesID
import com.example.marvelapp.data.api.retrofit.RetrofitService
import com.example.marvelapp.utils.getIdFromUrl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SeriesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SeriesUiState())
    val uiState: StateFlow<SeriesUiState> = _uiState.asStateFlow()

    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }

    fun getSeriesCharacterInfo(id: Int) {
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
            setCharactersSeries(responseCharacter.data.results.first())
        }
    }

    private suspend fun setCharactersSeries(character: Character) {
        val series = arrayListOf<SeriesID>()
        character.characterSeries.items
            .subList(0, character.characterSeries.items.size / 2) // Asegúrate de que la sublista esté basada en `comics.items`
            .forEach { item ->
                val comicId = item.resourceURI.getIdFromUrl()
                comicId?.let { id ->
                    val seriesResponse = retrofitApi.getSeriesById(id)
                    Log.d("AndroidRuntime", seriesResponse.toString())
                    Log.d("AndroidRuntime", "series ${seriesResponse.data}")
                    Log.d("AndroidRuntime", "List series ${seriesResponse.data.results}")// Obtén la respuesta del cómic
                    seriesResponse.data.results.forEach { seriesID ->
                        series.add(seriesID) // Agrega cada cómic individualmente a la lista
                    }
                }
            }
        _uiState.update {
            _uiState.value.copy(
                series = series,
                isSeriesListLoading = false
            )
        }
    }
}