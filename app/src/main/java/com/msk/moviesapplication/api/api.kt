package com.msk.moviesapplication.api

import com.msk.moviesapplication.Responces.Data.Discover.movies
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface api {

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("sort_by")
        sort_by:String=Sorting_Value.POPULARITY.value,
        @Query("with_genres")
        genres:String?
    ):movies

    @GET("genre/movie/list")
    suspend fun getGenre():genres
}