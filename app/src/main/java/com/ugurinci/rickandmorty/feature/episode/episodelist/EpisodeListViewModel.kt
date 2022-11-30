package com.ugurinci.rickandmorty.feature.episode.episodelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ugurinci.rickandmorty.network.RickAndMortyService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(private val rickAndMortyService: RickAndMortyService) : ViewModel() {

    val episodeListFlow = Pager(PagingConfig(20)) {
        EpisodeListPagingSource(rickAndMortyService)
    }.flow.cachedIn(viewModelScope)

    /*private var _episodeList = MutableStateFlow<EpisodeListModel?>(null)
    val episodeList = _episodeList.asStateFlow()

    init {
        viewModelScope.launch {
            _episodeList.value = rickAndMortyService.getEpisodeList()
        }
    }*/
}