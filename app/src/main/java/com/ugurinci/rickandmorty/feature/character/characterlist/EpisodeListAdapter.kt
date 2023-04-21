package com.ugurinci.rickandmorty.feature.character.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ugurinci.rickandmorty.databinding.EpisodeRowItemBinding
import com.ugurinci.rickandmorty.feature.episode.episodelist.EpisodeViewHolder
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult

class EpisodeListAdapter : RecyclerView.Adapter<EpisodeViewHolder>() {

    var episodeList = listOf<EpisodeResult>()

    var click: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = EpisodeRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EpisodeViewHolder(binding, click)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    override fun getItemCount() = episodeList.size
}