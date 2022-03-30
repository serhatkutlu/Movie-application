package com.msk.moviesapplication.Repository

import com.msk.moviesapplication.Responces.Data.Discover.movies
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import com.msk.moviesapplication.api.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(private val api: api) {

      fun getMovies( sortingValue: Sorting_Value,genre: Genre?): Flow<movies> {
        return flow {
            val Movies=api.getDiscoverMovie(sort_by = sortingValue.value, genres = genre?.name)
            emit(Movies)
        }
    }

     fun getGenres():Flow<genres>{
         return flow {
             val genre=api.getGenre()
             emit(genre)
         }
     }
}