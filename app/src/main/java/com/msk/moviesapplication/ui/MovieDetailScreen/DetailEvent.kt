package com.msk.moviesapplication.ui.MovieDetailScreen

sealed class DetailEvent{
    object  getdetails:DetailEvent()
    object  getComment:DetailEvent()
}
