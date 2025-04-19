package com.example.filmmovie.repository

import com.example.filmmovie.api.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: ApiServices){
    suspend fun searchMovie(name:String) = api.searchMovies(name)
}