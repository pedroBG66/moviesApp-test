package com.example.moviesapp.data


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search") val movies: List<Movie>,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Response") val response: String
)
class Movie (

    @SerializedName ("imdbID") val imdbID: String,
    @SerializedName ("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Country") val country: String,

    ) {

    fun getGenresList(): List<String> {
        return genre.split(",").map { it.trim() }
    }
}

