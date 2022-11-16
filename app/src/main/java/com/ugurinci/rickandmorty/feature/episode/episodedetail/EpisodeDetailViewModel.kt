package com.ugurinci.rickandmorty.feature.episode.episodedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyApi
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var _episodeDetail = MutableLiveData<EpisodeResult>()
    val episodeDetail: LiveData<EpisodeResult> = _episodeDetail

    init {
        viewModelScope.launch {
            val id = EpisodeDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
            _episodeDetail.value = RickAndMortyApi.rickAndMortyService.getEpisodeById(id)
        }
    }
}