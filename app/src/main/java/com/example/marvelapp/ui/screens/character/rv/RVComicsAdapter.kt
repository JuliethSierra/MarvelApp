package com.example.marvelapp.ui.screens.character.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.api.models.Comic
import com.example.marvelapp.databinding.ComicViewBinding

class RVComicsAdapter():
    RecyclerView.Adapter<ComicViewHolder>(){

        var comics = emptyList<Comic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = ComicViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ComicViewHolder(binding)
    }

    override fun getItemCount(): Int = comics.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(comics[position])
    }
}