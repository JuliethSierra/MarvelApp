package com.example.marvelapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.data.viewmodel.CharactersViewModel
import com.example.marvelapp.databinding.ActivityCharactersBinding
import com.example.marvelapp.ui.screens.characters.rv.RVCharactersAdapter
import com.example.marvelapp.ui.theme.MarvelAppTheme
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
        rvCharactersAdapter = RVCharactersAdapter()
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvCharactersAdapter
        }
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
