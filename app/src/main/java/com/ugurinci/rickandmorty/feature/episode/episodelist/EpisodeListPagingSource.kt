package com.ugurinci.rickandmorty.feature.episode.episodelist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.episode.EpisodeResult

class EpisodeListPagingSource(private val rickAndMortyService: RickAndMortyService) : PagingSource<Int, EpisodeResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeResult> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = rickAndMortyService.getEpisodeList(nextPageNumber)
            LoadResult.Page(response.results, null, nextPageNumber + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EpisodeResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}