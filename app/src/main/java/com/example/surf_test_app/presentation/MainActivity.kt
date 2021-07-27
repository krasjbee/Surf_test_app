package com.example.surf_test_app.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.surf_test_app.databinding.ActivityMainBinding
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

        val observer = viewModel.liveData.observe(this) {
            adapter.submitList(it)
        }
    }
}