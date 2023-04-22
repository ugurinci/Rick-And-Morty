package com.ugurinci.rickandmorty.feature.location.locationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ugurinci.rickandmorty.databinding.LocationRowItemBinding
import com.ugurinci.rickandmorty.network.model.location.LocationResult

class LocationListPagingAdapter(
    diffCallback: DiffUtil.ItemCallback<LocationResult>
) : PagingDataAdapter<LocationResult, LocationViewHolder>(diffCallback) {

    var click: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = LocationRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LocationViewHolder(binding, click)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }
}