package com.example.surf_test_app.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.surf_test_app.databinding.CardFilmBinding
import com.example.surf_test_app.model.FilmResult

class FilmsAdapter(private val clickListener: (String) -> Unit) :
    ListAdapter<FilmResult, FilmViewHolder>(itemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CardFilmBinding.inflate(inflater, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.rootView.setOnClickListener {
            clickListener(getItem(position).title)
        }
    }

    private companion object {
        val itemCallback = object : DiffUtil.ItemCallback<FilmResult>() {
            override fun areItemsTheSame(oldItem: FilmResult, newItem: FilmResult): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FilmResult, newItem: FilmResult): Boolean =
                oldItem.overview == newItem.overview
        }
    }
}
