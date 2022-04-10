package com.msk.moviesapplication.Repository.MovieScreen

import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Responces.Data.Discover.Result
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.api.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(private val MovieApi: MovieApi) {

      fun getMovies( sortingData: Sorting_data,page:Int=1): Flow<Movies> {
        return flow {
            val Movies=MovieApi.getDiscoverMovie(sort_by = sortingData.Sorting_value.value, page = page, genreId = sortingData.GenreToString())
           checkNullPoster(Movies)
            emit(Movies)
        }
    }

     fun getGenres():Flow<genres>{
         return flow {
             val genre=MovieApi.getGenre()
             emit(genre)
         }
     }

    private fun checkNullPoster( Movies:Movies):Movies{
        val deletedItem= mutableListOf<Result>()
        Movies.results.forEach{
            if (it.posterPath.isNullOrEmpty()){
                deletedItem.add(it)
            }
        }
        Movies.results.removeAll(deletedItem)
        return Movies
    }
}