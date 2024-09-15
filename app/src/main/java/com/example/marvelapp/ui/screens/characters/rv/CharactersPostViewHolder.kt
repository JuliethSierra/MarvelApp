package com.example.marvelapp.ui.screens.characters.rv

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.databinding.CharacterViewBinding
import com.example.marvelapp.utils.loadImage

class CharactersPostViewHolder(
    private val binding: CharacterViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        with(binding) {
            idCharacter.text = "id: ${character.id}"
            tvCharacterName.text = character.name
            tvCharacterDescription.text = "description: ${character.description}"
            loadImage(character)
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
                ivCharacterPicture.loadImage(imageUrl)
            }
        }
    }
}
