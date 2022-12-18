package com.ugurinci.rickandmorty.feature.character.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ugurinci.rickandmorty.network.RickAndMortyService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val rickAndMortyService: RickAndMortyService) : ViewModel() {

    val characterListFlow = Pager(PagingConfig(20)) {
        CharacterListPagingSource(rickAndMortyService)
    }.flow.cachedIn(viewModelScope)
}