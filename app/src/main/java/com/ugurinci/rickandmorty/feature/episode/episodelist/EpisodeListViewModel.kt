package com.ugurinci.rickandmorty.feature.episode.episodelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.episode.EpisodeListModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EpisodeListViewModel : ViewModel() {

    private var _episodeList = MutableStateFlow<EpisodeListModel?>(null)
    val episodeList = _episodeList.asStateFlow()

    init {
        viewModelScope.launch {
            _episodeList.value = RickAndMortyApi.rickAndMortyService.getEpisodeList()
        }
    }
}