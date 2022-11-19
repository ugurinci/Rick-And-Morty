package com.ugurinci.rickandmorty.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyModule {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Singleton
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun rickAndMortyService(): RickAndMortyService = retrofit.create(RickAndMortyService::class.java)
}