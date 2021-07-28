package com.example.surf_test_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surf_test_app.domain.FilmsInteractor
import com.example.surf_test_app.model.DiscoverResponse
import com.example.surf_test_app.model.FilmResult
import com.example.surf_test_app.model.SearchResponse
import com.example.surf_test_app.util.Resource
import com.example.surf_test_app.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val filmsInteractor: FilmsInteractor) :
    ViewModel() {
    private val _filmList: MutableLiveData<List<FilmResult>> = MutableLiveData(emptyList())
    val filmList: LiveData<List<FilmResult>> = _filmList

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State> = _state

    init {
        discoverFilms()
    }

    fun searchMovies(query: String) {
        _state.postValue(State.Loading)
        viewModelScope.launch {
            val listResource = filmsInteractor(query)
            val searchResponse: SearchResponse
            when (listResource) {
                is Resource.Success -> {
                    searchResponse = listResource.data!!
                    if (searchResponse.totalResults == 0) {
                        _state.postValue(State.WrongRequest)
                    } else {
                        _state.postValue(State.Success)
                        _filmList.postValue(searchResponse.filmResults)
                    }
                }
                is Resource.RequestError -> _state.postValue(State.WrongRequest)
                is Resource.UnexpectedError -> _state.postValue(State.Error)
            }
        }
    }

    fun discoverFilms() {
        _state.postValue(State.Loading)
        viewModelScope.launch {
            val listResource = filmsInteractor()
            val discoverResponse: DiscoverResponse
            when (listResource) {
                is Resource.Success -> {
                    discoverResponse = listResource.data!!
                    if (discoverResponse.totalResults == 0) {
                        _state.postValue(State.WrongRequest)
                    } else {
                        _state.postValue(State.Success)
                        _filmList.postValue(discoverResponse.filmResults)
                    }
                }
                is Resource.RequestError -> _state.postValue(State.WrongRequest)
                is Resource.UnexpectedError -> _state.postValue(State.Error)
            }

        }
    }
}