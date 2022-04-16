package com.msk.moviesapplication.Repository.MovieScreen

import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpFake:MovieRepository{

    private val movies=Movies(page = 1, results = mutableListOf(), totalPages = 5, totalResults = 100)
    private val genres=genres(mutableListOf())

    override fun getMovies(sortingData: Sorting_data, page: Int): Flow<Result<Movies>> {
        return flow {

            emit(Result.success(movies.copy(page = page)))
        }
    }

    override fun getGenres(): Flow<Result<genres>> {
        return flow {
            emit(Result.success(genres))
        }
    }


}