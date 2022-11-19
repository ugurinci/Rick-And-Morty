package com.ugurinci.rickandmorty.feature.episode.episodelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.episode.EpisodeListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(private val rickAndMortyService: RickAndMortyService) : ViewModel() {

    private var _episodeList = MutableStateFlow<EpisodeListModel?>(null)
    val episodeList = _episodeList.asStateFlow()

    init {
        viewModelScope.launch {
            _episodeList.value = rickAndMortyService.getEpisodeList()
        }
    }
}