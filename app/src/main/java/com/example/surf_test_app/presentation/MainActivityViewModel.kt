package com.example.surf_test_app.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surf_test_app.model.FilmResult
import com.example.surf_test_app.repository.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: FilmsRepository) :
    ViewModel() {
    val mutableFilmList: MutableLiveData<List<FilmResult>> = MutableLiveData(emptyList())
    val filmList: LiveData<List<FilmResult>> = mutableFilmList

    fun searchMovies(query: String) {
        viewModelScope.launch {
            mutableFilmList.postValue(repository.searchFilms(query).filmResults)
        }
    }

    fun discoverFilms() {
        viewModelScope.launch {
            mutableFilmList.postValue(repository.getFilms().filmResults.also {
                Log.d("MainActivityViewModel", "discoverFilms: ${it.size} ")
            })
        }
    }
}