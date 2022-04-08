package com.msk.moviesapplication.Repository

import android.util.Log
import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.api.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(private val api: api) {

      fun getMovies( sortingData: Sorting_data,page:Int=1): Flow<Movies> {
        return flow {
            Log.d("hatalar",sortingData.Sorting_value.value +"adad"+sortingData.GenreToString())
            val Movies=api.getDiscoverMovie(sort_by = sortingData.Sorting_value.value, page = page, genreId = sortingData.GenreToString())
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