package com.msk.moviesapplication.Repository.DetailScreen

import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.api.MovieDetailApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesDetailRepositoryImp @Inject constructor(private val Moviedetailapi:MovieDetailApi) {
    fun getDetails(movieID:Int): Flow<Details> {
        return flow {
           val responce= Moviedetailapi.GetMovieDetails(movieID)
           emit(responce)
        }
    }
}