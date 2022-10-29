package com.ugurinci.rickandmorty.network

import com.ugurinci.rickandmorty.network.model.character.CharacterListModel
import com.ugurinci.rickandmorty.network.model.character.CharacterResult
import com.ugurinci.rickandmorty.network.model.episode.EpisodeListModel
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult
import com.ugurinci.rickandmorty.network.model.location.LocationListModel
import com.ugurinci.rickandmorty.network.model.location.LocationResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {
    @GET("character")
    fun getCharacterList(): Call<CharacterListModel>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: String): Call<CharacterResult>

    @GET("location")
    fun getLocationList(): Call<LocationListModel>

    @GET("location/{id}")
    fun getLocationById(@Path("id") id: String): Call<LocationResult>

    @GET("episode")
    fun getEpisodeList(): Call<EpisodeListModel>

    @GET("episode/{id}")
    fun getEpisodeById(@Path("id") id: String): Call<EpisodeResult>
}