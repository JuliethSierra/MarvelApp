package com.example.marvelapp.ui.screens.series.rv

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.api.models.Comic
import com.example.marvelapp.data.api.models.SeriesID
import com.example.marvelapp.databinding.SeriesViewBinding
import com.example.marvelapp.utils.loadImage

class SeriesViewHolder (
    private val binding: SeriesViewBinding,
): RecyclerView.ViewHolder(binding.root) {

        fun bind(series: SeriesID){
            with(binding){
                tvSeriesId.text = series.id.toString()
                tvSeriesTitle.text = series.title
                tvSeriesStartYear.text = series.startYear.toString()
                tvSeriesEndYear.text = series.endYear.toString()
                loadImage(series)
            }
        }

    private fun loadImage(series: SeriesID) {
        with(binding) {
            series.thumbnail?.let { thumbnail ->
                var imageUrl = "${thumbnail.path}.${thumbnail.extension}"

                if (imageUrl.startsWith("http://")) {
                    imageUrl = imageUrl.replace("http://", "https://")
                }
                Log.d("AndroidRuntime", imageUrl)
                ivSeriesPhoto.loadImage(imageUrl)
            }
        }
    }
}