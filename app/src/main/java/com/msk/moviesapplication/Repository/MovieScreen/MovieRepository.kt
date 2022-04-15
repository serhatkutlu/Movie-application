package com.msk.moviesapplication.Repository.MovieScreen

import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Responces.Data.Discover.Result
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_data
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

     fun getMovies(sortingData: Sorting_data, page:Int): Flow<kotlin.Result<Movies>>

     fun getGenres(): Flow<kotlin.Result<genres>>

}