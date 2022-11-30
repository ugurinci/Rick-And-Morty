package com.ugurinci.rickandmorty.feature.character.characterlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.character.CharacterResult

class CharacterListPagingSource(private val rickAndMortyService: RickAndMortyService) : PagingSource<Int, CharacterResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = rickAndMortyService.getCharacterList(nextPageNumber)
            LoadResult.Page(response.results, null, nextPageNumber + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}