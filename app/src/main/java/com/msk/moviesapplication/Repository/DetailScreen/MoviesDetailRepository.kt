package com.msk.moviesapplication.Repository.DetailScreen

import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.Responces.Data.comments.comment
import kotlinx.coroutines.flow.Flow

interface MoviesDetailRepository {

    fun getDetails(movieID:Int):  Flow<Result<Details>>
    fun getcomments(movieid:Int,page:Int): Flow<Result<comment>>
}