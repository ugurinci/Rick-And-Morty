package com.ugurinci.rickandmorty.network.model.character

import com.google.gson.annotations.SerializedName
import com.ugurinci.rickandmorty.network.model.Info

data class CharacterListModel(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<CharacterResult>
)