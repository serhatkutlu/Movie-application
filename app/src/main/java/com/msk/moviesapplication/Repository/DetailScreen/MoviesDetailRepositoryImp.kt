package com.msk.moviesapplication.Repository.DetailScreen

import android.util.Log
import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.Responces.Data.comments.comment
import com.msk.moviesapplication.api.MovieDetailApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesDetailRepositoryImp @Inject constructor(private val Moviedetailapi:MovieDetailApi):MoviesDetailRepository {
    override fun getDetails(movieID:Int): Flow<Result<Details>> {
        return flow {
            try {
                val responce= Moviedetailapi.GetMovieDetails(movieID)
                emit(Result.success(responce))
            }catch (e:Exception){
                emit(Result.failure(e))
            }

        }
    }
    override fun getcomments(movieid:Int,page:Int):Flow<Result<comment>>{
        return flow {
            try {
                val responce=Moviedetailapi.GetMovieComments(movieid,page)
                emit(Result.success(responce))
            }catch (e:Exception){
                emit(Result.failure(e))
            }


        }
    }
}