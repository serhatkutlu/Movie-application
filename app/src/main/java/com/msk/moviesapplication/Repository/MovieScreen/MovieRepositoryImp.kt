package com.msk.moviesapplication.Repository.MovieScreen

import android.util.Log
import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.api.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(private val MovieApi: MovieApi):MovieRepository{

      override fun getMovies( sortingData: Sorting_data,page:Int): Flow<Result<Movies>> {
        return flow {
            try {
                var Movies=MovieApi.getDiscoverMovie(sort_by = sortingData.Sorting_value.value, page = page, genreId = sortingData.GenreToString())
                Movies=checkNullPoster(Movies)
                emit(Result.success(Movies))
            }catch (e:Exception){
             emit(Result.failure(e))
            }

        }
    }

     override fun getGenres():Flow<Result<genres>>{
         return flow {
             try {
                 val genre=MovieApi.getGenre()
                 emit(Result.success(genre))
             }catch (e:Exception){
                 emit(Result.failure(e))
             }

         }
     }

    private fun checkNullPoster( Movies:Movies):Movies{
        val deletedItem= mutableListOf<com.msk.moviesapplication.Responces.Data.Discover.Result>()
        Movies.results.forEach{
            if (it.posterPath.isNullOrEmpty()){
                deletedItem.add(it)
            }
        }
        Movies.results.removeAll(deletedItem)
        return Movies
    }
}