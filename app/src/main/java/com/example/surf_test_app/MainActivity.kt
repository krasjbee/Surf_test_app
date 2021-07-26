package com.example.surf_test_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.surf_test_app.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = FilmsAdapter { title ->
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
        }
        binding.rvFilmList.apply {
            this.adapter = adapter
            this.layoutManager = layoutManager
        }
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val films = Retrofit.filmsService.getMovies().filmResults
            launch(Dispatchers.Main) { adapter.submitList(films) }
        }

    }
}