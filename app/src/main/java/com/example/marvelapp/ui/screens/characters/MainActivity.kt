package com.example.marvelapp.ui.screens.characters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.data.viewmodel.CharactersViewModel
import com.example.marvelapp.databinding.ActivityCharactersBinding
import com.example.marvelapp.ui.screens.character.CharacterActivity
import com.example.marvelapp.ui.screens.character.CharacterActivity.Companion.CHARACTER_ID
import com.example.marvelapp.ui.screens.characters.rv.RVCharactersAdapter
import com.example.marvelapp.ui.screens.series.SeriesActivity
import com.example.marvelapp.ui.screens.series.SeriesActivity.Companion.SERIES_CHARACTER_ID
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val charactersViewModel: CharactersViewModel by viewModels()
    private lateinit var binding: ActivityCharactersBinding
    private lateinit var rvCharactersAdapter: RVCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()

        initUiStateLifecycle()
    }
    private fun initRV() {
        rvCharactersAdapter = RVCharactersAdapter(
            onComicsClickListener = {characterId ->
                launchCharacterActivity(characterId)
            },
            onDescriptionClickListener = {seriesCharacterId ->
                launchDescriptionActivity(seriesCharacterId)
            }
        )
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvCharactersAdapter
        }
    }

    private fun launchCharacterActivity(characterId: Int) {
        startActivity(
            Intent(
                this,
                CharacterActivity::class.java
            ).apply {
                putExtras(
                    bundleOf(
                        CHARACTER_ID to characterId
                    )
                )
            }
        )
    }

    private fun launchDescriptionActivity(seriesCharacterId: Int) {
        startActivity(
            Intent(
                this,
                SeriesActivity::class.java
            ).apply {
                putExtras(
                    bundleOf(
                        SERIES_CHARACTER_ID to seriesCharacterId
                    )
                )
            }
        )
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            charactersViewModel.uiState.collect { uiState ->
                uiState.listCharacters?.let { listCharacters ->
                    rvCharactersAdapter.characters = listCharacters
                    rvCharactersAdapter.notifyDataSetChanged()
                }
                binding.rvCharacters.visibility = if (uiState.isLoading) View.INVISIBLE else View.VISIBLE
                binding.pbCharacters.visibility = if (uiState.isLoading.not()) View.INVISIBLE else View.VISIBLE
            }
        }
    }
}
