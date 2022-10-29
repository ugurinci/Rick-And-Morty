package com.ugurinci.rickandmorty.network.model.location

import com.google.gson.annotations.SerializedName
import com.ugurinci.rickandmorty.network.model.Info

data class LocationListModel(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<LocationResult>
)