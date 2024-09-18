package com.example.marvelapp.ui.screens.characters.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.databinding.CharacterViewBinding

class RVCharactersAdapter (
    private val onComicsClickListener: (id: Int) -> Unit,
    private val onDescriptionClickListener: (id: Int) -> Unit
): RecyclerView.Adapter<CharactersPostViewHolder>(){

    var characters = emptyList<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersPostViewHolder {
        val binding = CharacterViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharactersPostViewHolder(
            binding = binding,
            onComicsClickListener = onComicsClickListener,
            onDescriptionClickListener = onDescriptionClickListener
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharactersPostViewHolder, position: Int) {
        holder.bind(characters[position])
    }
}