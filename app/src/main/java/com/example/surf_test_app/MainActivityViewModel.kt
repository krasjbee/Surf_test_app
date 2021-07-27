package com.example.surf_test_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.surf_test_app.model.FilmResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val api: TMDbAPI) : ViewModel() {
    val liveData: LiveData<List<FilmResult>> = androidx.lifecycle.liveData {
        val data = api.getMovies()
        emit(data.filmResults)
    }
}