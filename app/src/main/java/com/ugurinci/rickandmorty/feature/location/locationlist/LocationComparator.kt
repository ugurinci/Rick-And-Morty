package com.ugurinci.rickandmorty.feature.location.locationlist

import androidx.recyclerview.widget.DiffUtil
import com.ugurinci.rickandmorty.network.model.location.LocationResult

object LocationComparator : DiffUtil.ItemCallback<LocationResult>() {
    override fun areItemsTheSame(oldItem: LocationResult, newItem: LocationResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationResult, newItem: LocationResult): Boolean {
        return oldItem == newItem
    }
}