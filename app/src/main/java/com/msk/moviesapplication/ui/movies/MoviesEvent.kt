package com.msk.moviesapplication.ui.movies

import androidx.navigation.NavController
import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.ui.MainActivity.LastStates

sealed class MoviesEvent()
{
    data class OrderSection(val sortingData: Sorting_data):MoviesEvent()
    data class openDetailScreen(val navController: NavController,val movieId:Int):MoviesEvent()
    data class AddLastMovies(val lastStates: LastStates):MoviesEvent()
    object   resetMovies:MoviesEvent()
    object   ToggleOrderSection:MoviesEvent()
    object   LoadNextPage:MoviesEvent()

    //search
}