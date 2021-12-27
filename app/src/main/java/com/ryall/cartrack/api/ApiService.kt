package com.ryall.cartrack.api

import com.ryall.cartrack.models.MovieResponse
import com.ryall.cartrack.util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
     fun getMovieData(
        @Query("t") type: String,
        @Query("s") title: String,
        @Query("apikey") apikey: String = API_KEY
    ): Call<MovieResponse>
}