package com.example.surf_test_app.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.surf_test_app.databinding.CardFilmBinding
import com.example.surf_test_app.extentions.toDate
import com.example.surf_test_app.model.FilmResult

class FilmViewHolder(private val binding: CardFilmBinding) : RecyclerView.ViewHolder(binding.root) {
    val favButton = binding.like

    fun bind(filmResult: FilmResult) {
        val imageUrl =
            StringBuilder("https://image.tmdb.org/t/p/w500").append(filmResult.posterPath)
                .toString()

        with(binding) {
            tvFilmTitle.text = filmResult.title
            tvFilmDetails.text = filmResult.overview
            tvFilmDate.text = filmResult.releaseDate.toDate()
            like.isChecked = filmResult.isFavorite
            Glide.with(this@FilmViewHolder.itemView.context)
                .load(imageUrl)
                .into(ivFilmPoster)
        }
    }


}

