package com.ugurinci.rickandmorty.network.model.episode

import com.google.gson.annotations.SerializedName
import com.ugurinci.rickandmorty.network.model.Info

data class EpisodeListModel(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<EpisodeResult>
)