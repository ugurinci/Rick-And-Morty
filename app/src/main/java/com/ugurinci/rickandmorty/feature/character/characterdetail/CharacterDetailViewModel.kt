package com.ugurinci.rickandmorty.feature.character.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.character.CharacterResult
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult
import com.ugurinci.rickandmorty.util.StringUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val rickAndMortyService: RickAndMortyService
) : ViewModel() {

    private var _characterDetail = MutableStateFlow<CharacterResult?>(null)
    val characterDetail = _characterDetail.asStateFlow()

    private var _episodeList = MutableStateFlow<List<EpisodeResult>?>(null)
    val episodeList = _episodeList.asStateFlow()

    init {
        viewModelScope.launch {
            val id = CharacterDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id.toString()
            _characterDetail.value = rickAndMortyService.getCharacterById(id)
        }
    }

    fun getEpisodes(episodeIdList: List<String>) {
        viewModelScope.launch {
            if (episodeIdList.size > 1) {
                _episodeList.value = rickAndMortyService.getEpisodeListByIdList(
                    StringUtil.joinIdListToString(episodeIdList)
                )
            } else if (episodeIdList.size == 1) {
                _episodeList.value = listOf(rickAndMortyService.getEpisodeById(episodeIdList[0]))
            }
        }
    }
}