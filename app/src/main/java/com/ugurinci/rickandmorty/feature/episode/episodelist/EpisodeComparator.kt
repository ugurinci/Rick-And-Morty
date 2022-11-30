package com.ugurinci.rickandmorty.feature.episode.episodelist

import androidx.recyclerview.widget.DiffUtil
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult

object EpisodeComparator : DiffUtil.ItemCallback<EpisodeResult>() {
    override fun areItemsTheSame(oldItem: EpisodeResult, newItem: EpisodeResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeResult, newItem: EpisodeResult): Boolean {
        return oldItem == newItem
    }
}