package com.msk.moviesapplication.api

import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailApi {

    @GET("movie/{movieid}")
    suspend fun GetMovieDetails(
        @Path("movieid")
        movieid:Int
    ):Details
}