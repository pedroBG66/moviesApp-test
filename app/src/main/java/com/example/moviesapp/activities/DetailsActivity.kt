package com.example.moviesapp.activities

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moviesapp.R
import com.example.moviesapp.data.Movie
import com.example.moviesapp.databinding.ActivityDetailsBinding
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.utils.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_ID = "MOVIE_ID"
    }

    lateinit var binding: ActivityDetailsBinding

    var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imdbID = intent.getStringExtra(EXTRA_MOVIE_ID)!!
        getMovie(imdbID)

    }
    fun loadData() {
        movie?.let { movie ->

            Picasso.get().load(movie.poster).into(binding.imageViewPoster)

            binding.textViewTitle.text = movie.title
            binding.textViewYear.text = movie.year
            binding.textViewPlot.text = movie.plot
            binding.textViewRuntime.text = movie.runtime
            binding.textViewDirector.text = movie.director

            val genres = movie.getGenresList().joinToString(", ")
            binding.textViewGenre.text = genres

            binding.textViewCountry.text = movie.country
        } ?: run {
            Log.e("DetailsActivity", "Movie data is null!")
        }
    }


    fun getMovie(imdbID: String) {
        val service = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = service.findMovieById(imdbID)
                CoroutineScope(Dispatchers.Main).launch {
                    movie = result
                    loadData()
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }
}