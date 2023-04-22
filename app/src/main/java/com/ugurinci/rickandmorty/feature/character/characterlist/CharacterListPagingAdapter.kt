package com.ugurinci.rickandmorty.feature.character.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ugurinci.rickandmorty.databinding.CharacterRowItemBinding
import com.ugurinci.rickandmorty.network.model.character.CharacterResult

class CharacterListPagingAdapter(
    diffCallback: DiffUtil.ItemCallback<CharacterResult>
) : PagingDataAdapter<CharacterResult, CharacterViewHolder>(diffCallback) {

    var click: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding, click)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }
}