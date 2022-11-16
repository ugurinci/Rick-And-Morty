package com.ugurinci.rickandmorty.feature.episode.episodelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.episode.EpisodeListModel
import kotlinx.coroutines.launch

class EpisodeListViewModel : ViewModel() {

    private var _episodeList = MutableLiveData<EpisodeListModel>()
    val episodeList: LiveData<EpisodeListModel> = _episodeList

    init {
        viewModelScope.launch {
            _episodeList.value = RickAndMortyApi.rickAndMortyService.getEpisodeList()
        }
    }
}