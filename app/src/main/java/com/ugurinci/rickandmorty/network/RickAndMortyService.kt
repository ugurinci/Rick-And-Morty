package com.ugurinci.rickandmorty.network

import com.ugurinci.rickandmorty.network.model.character.CharacterListModel
import com.ugurinci.rickandmorty.network.model.character.CharacterResult
import com.ugurinci.rickandmorty.network.model.episode.EpisodeListModel
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult
import com.ugurinci.rickandmorty.network.model.location.LocationListModel
import com.ugurinci.rickandmorty.network.model.location.LocationResult
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {
    @GET("character")
    suspend fun getCharacterList(): CharacterListModel

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): CharacterResult

    @GET("location")
    suspend fun getLocationList(): LocationListModel

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: String): LocationResult

    @GET("episode")
    suspend fun getEpisodeList(): EpisodeListModel

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: String): EpisodeResult
}