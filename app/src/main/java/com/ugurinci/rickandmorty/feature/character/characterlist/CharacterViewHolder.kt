package com.ugurinci.rickandmorty.feature.character.characterlist

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurinci.rickandmorty.databinding.CharacterRowItemBinding
import com.ugurinci.rickandmorty.network.model.character.CharacterResult

class CharacterViewHolder(private val binding: CharacterRowItemBinding, private val click: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(characterResult: CharacterResult) {
        binding.apply {
            textView.text = characterResult.name
            root.setOnClickListener {
                click(characterResult.id)
            }
            Glide.with(root).load(characterResult.image).into(imageView)
        }
    }
}