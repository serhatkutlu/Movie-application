package com.msk.moviesapplication.ui.movies

import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Util.Sorting_data

data class MoviesState(
    var movies:Movies? =null,
    var isOrderSectionVisible:Boolean=false,
    var isLoading:Boolean=false,
    var endReached:Boolean=false,
    var error:String=""
)