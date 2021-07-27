package com.example.surf_test_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.surf_test_app.model.FilmResult
import com.example.surf_test_app.repository.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: FilmsRepository) :
    ViewModel() {
    val liveData: LiveData<List<FilmResult>> = androidx.lifecycle.liveData {
        val data = repository.discoverFilms()
        emit(data.filmResults)
    }
}