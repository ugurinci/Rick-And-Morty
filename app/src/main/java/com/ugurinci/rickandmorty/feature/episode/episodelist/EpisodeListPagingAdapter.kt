package com.ugurinci.rickandmorty.feature.episode.episodelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ugurinci.rickandmorty.databinding.EpisodeRowItemBinding
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult

class EpisodeListPagingAdapter(
    diffCallback: DiffUtil.ItemCallback<EpisodeResult>
) : PagingDataAdapter<EpisodeResult, EpisodeViewHolder>(diffCallback) {

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
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }
}