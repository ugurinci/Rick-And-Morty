package com.ugurinci.rickandmorty.feature.character.characterlist

import androidx.recyclerview.widget.DiffUtil
import com.ugurinci.rickandmorty.network.model.character.CharacterResult

object CharacterComparator : DiffUtil.ItemCallback<CharacterResult>() {
    override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
        return oldItem == newItem
    }
}