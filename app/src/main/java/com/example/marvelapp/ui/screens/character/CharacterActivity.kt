package com.example.marvelapp.ui.screens.character

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
import com.example.marvelapp.data.viewmodel.CharacterViewModel
import com.example.marvelapp.databinding.ActivityCharacterBinding
import com.example.marvelapp.ui.screens.character.rv.RVComicsAdapter
import com.example.marvelapp.utils.loadImage
import kotlinx.coroutines.launch

class CharacterActivity : ComponentActivity() {

    private lateinit var rvComicsAdapter: RVComicsAdapter
    private lateinit var binding: ActivityCharacterBinding
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
        getCharacterId()
    }

    private fun initRV() {
        rvComicsAdapter = RVComicsAdapter()
        binding.rvCharacterComics.apply {
            layoutManager = LinearLayoutManager(this@CharacterActivity)
            adapter = rvComicsAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            characterViewModel.uiState.collect { uiState ->
                with(binding) {
                    uiState.character?.let { character ->
                        tvCharacterName.text = character.name
                        tvCharacterId.text =character.id.toString()
                        tvCharacterDescription.text = "Descripción: ${if (character.description.isNotBlank()) character.description else "No disponible"}"

                        loadImage(character)
                    }
                    if (uiState.comics.isNotEmpty()) {
                        rvComicsAdapter.comics = uiState.comics
                        rvComicsAdapter.notifyDataSetChanged()
                    }
                    pbCharacter.visibility =
                        if (uiState.isCharacterLoading) View.VISIBLE else View.INVISIBLE
                    pbEpisodes.visibility =
                        if (uiState.isComicListLoading) View.VISIBLE else View.INVISIBLE
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

    private fun getCharacterId() {
        val characterId = intent.extras?.getInt(CHARACTER_ID)
        characterId?.let {
            characterViewModel.getCharacterInfo(characterId)
        }
    }

    companion object {
        const val CHARACTER_ID = "characterId"
    }
}
