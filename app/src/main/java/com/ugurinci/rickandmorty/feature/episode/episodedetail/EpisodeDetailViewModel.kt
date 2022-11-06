package com.ugurinci.rickandmorty.feature.episode.episodedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var _episodeDetail = MutableLiveData<EpisodeResult>()
    val episodeDetail: LiveData<EpisodeResult> = _episodeDetail

    init {
        getEpisodeDetail()
    }

    private fun getEpisodeDetail() {
        val id = EpisodeDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
        RickAndMortyApi.rickAndMortyService.getEpisodeById(id).enqueue(object : Callback<EpisodeResult> {
            override fun onResponse(
                call: Call<EpisodeResult>,
                response: Response<EpisodeResult>
            ) {
                _episodeDetail.value = response.body()
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<EpisodeResult>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })
    }
}