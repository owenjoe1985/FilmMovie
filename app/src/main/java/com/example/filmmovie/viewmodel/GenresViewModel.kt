package com.example.filmmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmmovie.models.home.ResponseGenresList
import com.example.filmmovie.models.home.ResponseMoviesList
import com.example.filmmovie.repository.GenresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(private val repository: GenresRepository) : ViewModel() {

    val genresMoviesList = MutableLiveData<ResponseMoviesList>()
    val genresList = MutableLiveData<ResponseGenresList>()
    val loading = MutableLiveData<Boolean>()

    fun loadGenresMoviesList(id: Int) = viewModelScope.launch {
        val response = repository.moviesGenresList(id)
        if (response.isSuccessful){
            genresMoviesList.postValue(response.body())
        }
    }

    fun loadGenresList() = viewModelScope.launch {
        val response = repository.genresList()
        if (response.isSuccessful){
            genresList.postValue(response.body())
        }
    }
}