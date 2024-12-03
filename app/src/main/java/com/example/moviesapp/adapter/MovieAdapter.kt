package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.Movie
import com.example.moviesapp.databinding.ItemMoviesdetailsBinding
import com.squareup.picasso.Picasso

class MovieAdapter(var items: List<Movie>, val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        holder.render(movie)
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesdetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Método para actualizar los items
    fun updateItems(items: List<Movie>) {
        this.items = items
        notifyDataSetChanged()
    }
}



class ViewHolder(val binding: ItemMoviesdetailsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(movie: Movie) {
        binding.textViewTitle.text = movie.title
        binding.textViewYear.text = movie.year
        Picasso.get().load(movie.poster).into(binding.imageViewPoster)
    }
}
