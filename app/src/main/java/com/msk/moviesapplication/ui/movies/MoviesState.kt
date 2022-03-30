package com.msk.moviesapplication.ui.movies

import com.msk.moviesapplication.Responces.Data.Discover.movies
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Util.Sorting_Value

data class MoviesState(
    val movies:movies? =null,
    val sorting:Sorting_Value=Sorting_Value.POPULARITY,
    val genre:Genre?=null,
    var isOrderSectionVisible:Boolean=false
)