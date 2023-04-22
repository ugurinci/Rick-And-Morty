package com.ugurinci.rickandmorty.feature.location.locationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ugurinci.rickandmorty.databinding.CharacterRowItemBinding
import com.ugurinci.rickandmorty.feature.character.characterlist.CharacterViewHolder
import com.ugurinci.rickandmorty.network.model.character.CharacterResult

class CharacterListAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    var characterList = listOf<CharacterResult>()

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
        holder.bind(characterList[position])
    }

    override fun getItemCount() = characterList.size
}