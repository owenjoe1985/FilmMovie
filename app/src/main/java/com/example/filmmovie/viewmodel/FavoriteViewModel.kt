package com.example.filmmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmmovie.db.MovieEntity
import com.example.filmmovie.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  FavoriteViewModel @Inject constructor(private val repository: FavoriteRepository) : ViewModel() {

    val favoriteList = MutableLiveData<MutableList<MovieEntity>>()
    val empty = MutableLiveData<Boolean>()

    fun loadFavoriteList() = viewModelScope.launch {
        val list = repository.allFavoriteList()
        if (list.isNotEmpty()) {
            favoriteList.postValue(list)
            empty.postValue(false)
        } else {
            empty.postValue(true)
        }
    }
}