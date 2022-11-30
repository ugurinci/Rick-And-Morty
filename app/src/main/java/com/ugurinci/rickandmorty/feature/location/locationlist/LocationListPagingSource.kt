package com.ugurinci.rickandmorty.feature.location.locationlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ugurinci.rickandmorty.network.RickAndMortyService
import com.ugurinci.rickandmorty.network.model.location.LocationResult

class LocationListPagingSource(private val rickAndMortyService: RickAndMortyService) : PagingSource<Int, LocationResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationResult> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = rickAndMortyService.getLocationList(nextPageNumber)
            LoadResult.Page(response.results, null, nextPageNumber + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LocationResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}