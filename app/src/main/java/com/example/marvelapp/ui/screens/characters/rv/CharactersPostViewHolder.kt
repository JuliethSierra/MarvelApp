package com.example.marvelapp.ui.screens.characters.rv

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.databinding.CharacterViewBinding
import com.example.marvelapp.utils.loadImage

class CharactersPostViewHolder(
    private val binding: CharacterViewBinding,
    private val onComicsClickListener: (position: Int) -> Unit,
    private val onDescriptionClickListener: (id: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        with(binding) {
            btnComics.setOnClickListener{
                onComicsClickListener(character.id)
            }

            btnSeries.setOnClickListener{
                onDescriptionClickListener(character.id)
            }

            idCharacter.text = "id: ${character.id}"
            tvCharacterName.text = character.name
            loadImage(character)
        }
    }

    private fun loadImage(character: Character) {
        with(binding) {
            character.thumbnail?.let { thumbnail ->
                var imageUrl = "${thumbnail.path}.${thumbnail.extension}"

                if (imageUrl.startsWith("http://")) {
                    imageUrl = imageUrl.replace("http://", "https://")
                }
                Log.d("AndroidRuntime", imageUrl)
                ivCharacterPicture.loadImage(imageUrl)
            }
        }
    }
}