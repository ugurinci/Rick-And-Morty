package com.ugurinci.rickandmorty.feature.episode.episodedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val rickAndMortyService: RickAndMortyService) : ViewModel() {

    private var _episodeDetail = MutableStateFlow<EpisodeResult?>(null)
    val episodeDetail = _episodeDetail.asStateFlow()

    init {
        viewModelScope.launch {
            val id = EpisodeDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
            _episodeDetail.value = rickAndMortyService.getEpisodeById(id)
        }
    }
}