package com.example.marvelapp.ui.screens.series

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.utils.loadImage
import com.example.marvelapp.data.viewmodel.SeriesViewModel
import com.example.marvelapp.databinding.ActivitySeriesBinding
import com.example.marvelapp.ui.screens.character.CharacterActivity
import com.example.marvelapp.ui.screens.character.CharacterActivity.Companion
import com.example.marvelapp.ui.screens.series.rv.RVSeriesAdapter
import kotlinx.coroutines.launch

class SeriesActivity : ComponentActivity() {

    private lateinit var rvSeriesAdapter: RVSeriesAdapter
    private lateinit var binding: ActivitySeriesBinding
    private val seriesViewModel: SeriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
        getSeriesCharacterId()
    }

    private fun initRV(){
        rvSeriesAdapter = RVSeriesAdapter()
        binding.rvCharacterSeries.apply {
            layoutManager = LinearLayoutManager(this@SeriesActivity)
            adapter = rvSeriesAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            seriesViewModel.uiState.collect { uiState ->
                with(binding) {
                    uiState.character?.let { character ->
                        tvCharacterName.text = character.name
                        tvCharacterId.text =character.id.toString()
                        tvCharacterDescription.text = "Descripción: ${if (character.description.isNotBlank()) character.description else "No disponible"}"

                        loadImage(character)
                    }
                    if (uiState.series.isNotEmpty()) {
                        rvSeriesAdapter.series = uiState.series
                        rvSeriesAdapter.notifyDataSetChanged()
                    }
                    pbCharacter.visibility =
                        if (uiState.isCharacterLoading) View.VISIBLE else View.INVISIBLE
                    pbSeries.visibility =
                        if (uiState.isSeriesListLoading) View.VISIBLE else View.INVISIBLE
                }
            }
        }
    }

    private fun loadImage(character: Character) {
        with(binding) {
            // Construct image URL if thumbnail is not null
            character.thumbnail?.let { thumbnail ->
                var imageUrl = "${thumbnail.path}.${thumbnail.extension}"

                // Replace http for https if it is necessary
                if (imageUrl.startsWith("http://")) {
                    imageUrl = imageUrl.replace("http://", "https://")
                }
                Log.d("AndroidRuntime", imageUrl)
                // Load the image using the custom function
                ivCharacterPhoto.loadImage(imageUrl)
            }
        }
    }

    private fun getSeriesCharacterId() {
        val seriesCharacterId = intent.extras?.getInt(SERIES_CHARACTER_ID)
        seriesCharacterId?.let {
            seriesViewModel.getSeriesCharacterInfo(seriesCharacterId)
        }
    }

    companion object {
        const val SERIES_CHARACTER_ID = "seriesCharacterId"
    }
}