package com.msk.moviesapplication.api

import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("page")
        page:Int=1  ,
        @Query("with_genres")
        genreId: String?=null,
        @Query("sort_by")
        sort_by:String?=null
    ): Movies

    @GET("genre/movie/list")
    suspend fun getGenre():genres
}

