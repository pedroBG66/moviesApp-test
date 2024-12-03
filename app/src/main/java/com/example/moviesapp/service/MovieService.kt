package com.example.moviesapp.service

import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    // Buscar películas por título
    @GET(".")
    suspend fun findMovieByTitle(
        @Query("s") title: String,
        @Query("apikey") apiKey: String = "fb7aca4",
        @Query("r") responseType: String = "json"
    ): MovieResponse

    // Buscar película por ID
    @GET(".")
    suspend fun findMovieById(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = "fb7aca4",
        @Query("r") responseType: String = "json"
    ): Movie
}
