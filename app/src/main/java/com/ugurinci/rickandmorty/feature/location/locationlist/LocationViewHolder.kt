package com.ugurinci.rickandmorty.feature.location.locationlist

import androidx.recyclerview.widget.RecyclerView
import com.ugurinci.rickandmorty.databinding.LocationRowItemBinding
import com.ugurinci.rickandmorty.network.model.location.LocationResult

class LocationViewHolder(private val binding: LocationRowItemBinding, private val click: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(locationResult: LocationResult) {
        binding.apply {
            textView.text = locationResult.name
            root.setOnClickListener {
                click(locationResult.id)
            }
        }
    }
}