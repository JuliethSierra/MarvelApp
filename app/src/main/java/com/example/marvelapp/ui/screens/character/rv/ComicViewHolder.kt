package com.example.marvelapp.ui.screens.character.rv

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.data.api.models.Comic
import com.example.marvelapp.databinding.ComicViewBinding
import com.example.marvelapp.utils.loadImage

class ComicViewHolder (
    private val binding: ComicViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(comic: Comic){
            with(binding){
                tvComicId.text = comic.id.toString()
                tvComicName.text = comic.title
                tvComicFormat.text = comic.format
                tvComicPages.text = comic.pageCount.toString()
                loadImage(comic)
            }
        }

    private fun loadImage(comic: Comic) {
        with(binding) {
            comic.thumbnail?.let { thumbnail ->
                var imageUrl = "${thumbnail.path}.${thumbnail.extension}"

                if (imageUrl.startsWith("http://")) {
                    imageUrl = imageUrl.replace("http://", "https://")
                }
                Log.d("AndroidRuntime", imageUrl)
                ivComicPhoto.loadImage(imageUrl)
            }
        }
    }

    }
