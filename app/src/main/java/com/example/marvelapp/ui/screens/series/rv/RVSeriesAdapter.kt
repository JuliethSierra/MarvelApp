package com.example.marvelapp.ui.screens.series.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.api.models.SeriesID
import com.example.marvelapp.databinding.SeriesViewBinding

class RVSeriesAdapter (
): RecyclerView.Adapter<SeriesViewHolder>(){

    var series = emptyList<SeriesID>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = SeriesViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SeriesViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = series.size

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(series[position])
    }

}