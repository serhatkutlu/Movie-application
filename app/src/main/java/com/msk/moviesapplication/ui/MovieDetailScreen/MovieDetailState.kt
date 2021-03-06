package com.msk.moviesapplication.ui.MovieDetailScreen

import androidx.compose.runtime.mutableStateOf
import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.Responces.Data.comments.comment

data class MovieDetailState(
    val comment: comment? = null,
    val details: Details?=null,
    var endReached:Boolean=false,
    var isLoading:Boolean=false,
    var isError:String=""
)
