package com.ugurinci.rickandmorty.feature.episode.episodelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.episode.EpisodeListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeListViewModel : ViewModel() {

    private var _episodeList = MutableLiveData<EpisodeListModel>()
    val episodeList: LiveData<EpisodeListModel> = _episodeList

    init {
        getEpisodeList()
    }

    private fun getEpisodeList() {
        RickAndMortyApi.rickAndMortyService.getEpisodeList().enqueue(object : Callback<EpisodeListModel> {
            override fun onResponse(
                call: Call<EpisodeListModel>,
                response: Response<EpisodeListModel>
            ) {
                _episodeList.value = response.body()
                Log.i("onResponse", "-> " + "onResponse")
            }

            override fun onFailure(call: Call<EpisodeListModel>, t: Throwable) {
                Log.i("onFailure", "-> " + "onFailure")
            }
        })
    }
}