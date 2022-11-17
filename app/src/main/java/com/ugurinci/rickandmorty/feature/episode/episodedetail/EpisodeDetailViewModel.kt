package com.ugurinci.rickandmorty.feature.episode.episodedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var _episodeDetail = MutableStateFlow<EpisodeResult?>(null)
    val episodeDetail = _episodeDetail.asStateFlow()

    init {
        viewModelScope.launch {
            val id = EpisodeDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
            _episodeDetail.value = RickAndMortyApi.rickAndMortyService.getEpisodeById(id)
        }
    }
}