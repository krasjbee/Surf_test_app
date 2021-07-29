package com.example.surf_test_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surf_test_app.domain.FilmsInteractor
import com.example.surf_test_app.model.DiscoverResponse
import com.example.surf_test_app.model.FilmResult
import com.example.surf_test_app.model.SearchResponse
import com.example.surf_test_app.repository.FavoritesRepository
import com.example.surf_test_app.util.Resource
import com.example.surf_test_app.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val filmsInteractor: FilmsInteractor,
    private val favouriteRepository: FavoritesRepository
) :
    ViewModel() {
    private var fetchedList: List<FilmResult> = emptyList()
    private val _filmList: MutableLiveData<List<FilmResult>> = MutableLiveData(emptyList())
    val filmList: LiveData<List<FilmResult>> = _filmList

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State> = _state


    init {
        discoverFilms()
    }

    fun searchMovies(query: String) {
        if (!_filmList.value.isNullOrEmpty()) {
            val filtredList = _filmList.value!!.filter {
                it.title.lowercase(Locale.getDefault())
                    .contains(query.lowercase(Locale.getDefault()))
            }
            _filmList.postValue(filtredList)
        }
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
                        //Merge with favourites
                        fetchedList = searchResponse.filmResults
                        mergeWithFavourites()
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
                        //Merge with favourites
                        fetchedList = discoverResponse.filmResults
                        mergeWithFavourites()
                    }
                }
                is Resource.RequestError -> _state.postValue(State.WrongRequest)
                is Resource.UnexpectedError -> _state.postValue(State.Error)
            }
        }
    }

    private fun mergeWithFavourites() {
        val favouriteIdsList = favouriteRepository.getSharedFavourites().toList()
        fetchedList.forEach { film ->
            if (favouriteIdsList.contains(film.id.toString())) {
                film.isFavorite = true
            }
        }
        _filmList.postValue(fetchedList)
    }

    fun setFavourite(id: String) {
        favouriteRepository.addSharedFavourite(id)
        mergeWithFavourites()
    }

    fun removeFavourite(id: String) {
        favouriteRepository.deleteSharedFavourite(id)
        mergeWithFavourites()
    }
}