package com.msk.moviesapplication.api

import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.Responces.Data.comments.comment
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailApi {

    @GET("movie/{movieid}")
    suspend fun GetMovieDetails(
        @Path("movieid")
        movieid:Int
    ):Details

    @GET("movie/{movieid}/reviews")
    suspend fun GetMovieComments(
        @Path("movieid")
        movieid:Int,
        @Query("page")
        page:Int=1
    ):comment
}