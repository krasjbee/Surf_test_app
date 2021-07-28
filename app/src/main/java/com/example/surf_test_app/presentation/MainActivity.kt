package com.example.surf_test_app.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.surf_test_app.R
import com.example.surf_test_app.databinding.ActivityMainBinding
import com.example.surf_test_app.util.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            } else {
                GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
            }
        val adapter = FilmsAdapter { title ->
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
        }
        binding.rvFilmList.apply {
            this.adapter = adapter
            this.layoutManager = layoutManager
        }
        viewModel.filmList.observe(this) { filmList ->
            adapter.submitList(filmList)
        }

        viewModel.state.observe(this) { state ->
            when (state) {
                is State.Loading -> showLoading()
                is State.Error -> showError()
                is State.WrongRequest -> showWrongRequest()
                is State.Success -> showList()
            }
        }

        binding.svSearch.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    //TODO add progress bar, hide keyboard
                    viewModel.searchMovies(query ?: "")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            }
        )

        binding.srContainer.setOnRefreshListener {
            if (binding.svSearch.query.isNotBlank()) {
                viewModel.searchMovies(binding.svSearch.query.toString())
            } else {
                viewModel.discoverFilms()
            }
            binding.srContainer.isRefreshing = false
        }
    }

    private fun showList() {
        binding.pbLoading.isVisible = false
        binding.rvFilmList.isVisible = true
        binding.groupRequestError.isVisible = false
        binding.groupError.isVisible = false

    }

    private fun showWrongRequest() {
        binding.pbLoading.isVisible = false
        binding.rvFilmList.isVisible = false
        binding.groupRequestError.isVisible = true
        binding.groupError.isVisible = false

        if (binding.svSearch.query.isNotBlank()) {
            val query = StringBuilder("\"").append(binding.svSearch.query).append("\"").toString()
            val text = getString(R.string.wrong_request_text, query)
            binding.tvRequestError.text = text
        } else {
            val text = getString(R.string.wrong_request_text, "")
            binding.tvRequestError.text = text
        }
    }

    private fun showError() {
        binding.pbLoading.isVisible = false
        binding.rvFilmList.isVisible = false
        binding.groupRequestError.isVisible = false
        binding.groupError.isVisible = true

    }

    private fun showLoading() {
        binding.pbLoading.isVisible = true
        binding.rvFilmList.isVisible = false
        binding.groupRequestError.isVisible = false
        binding.groupError.isVisible = false
    }
}