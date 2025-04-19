package com.example.filmmovie.repository

import com.example.filmmovie.api.ApiServices
import javax.inject.Inject

class GenresRepository @Inject constructor(private val api : ApiServices) {

    suspend fun moviesGenresList(id: Int) = api.moviesGenresList(id)

    suspend fun genresList() = api.genresList()
}