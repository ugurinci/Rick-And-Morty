package com.ugurinci.rickandmorty.feature.episode.episodelist

import androidx.recyclerview.widget.RecyclerView
import com.ugurinci.rickandmorty.databinding.EpisodeRowItemBinding
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult

class EpisodeViewHolder(private val binding: EpisodeRowItemBinding, private val click: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(episodeResult: EpisodeResult) {
        binding.apply {
            textView.text = episodeResult.name
            root.setOnClickListener {
                click(episodeResult.id)
            }
        }
    }
}