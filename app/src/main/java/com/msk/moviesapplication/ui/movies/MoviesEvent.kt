package com.msk.moviesapplication.ui.movies

import com.msk.moviesapplication.Responces.Data.Discover.movies
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Util.Sorting_Value

sealed class MoviesEvent()
{
    data class OrderSection(val sortingValue: Sorting_Value,var genre: Genre?=null):MoviesEvent()
    data class openDetailScreen(val movies: movies):MoviesEvent()
    object  ToggleOrderSection:MoviesEvent()
    //search
}