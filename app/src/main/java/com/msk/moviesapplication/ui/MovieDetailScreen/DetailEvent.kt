package com.msk.moviesapplication.ui.MovieDetailScreen

sealed class DetailEvent{
    data class  getdetails(val movieid:Int?=null):DetailEvent()
    data class  getComment(val movieid:Int?=null):DetailEvent()
}
