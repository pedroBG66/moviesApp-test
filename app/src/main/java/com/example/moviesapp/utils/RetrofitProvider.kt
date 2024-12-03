package com.example.moviesapp.utils

import com.example.moviesapp.service.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
        private const val BASE_URL = "https://www.omdbapi.com/"

        fun getRetrofit(): MovieService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MovieService::class.java)
        }
    }
}
