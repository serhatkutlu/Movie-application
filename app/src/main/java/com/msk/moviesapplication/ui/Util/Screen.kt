package com.msk.moviesapplication.ui.Util

sealed class MoviesScreenRoute(val route:String){
    object MoviesMainScreen:MoviesScreenRoute("movies_screen")
    object MoviesDetail:MoviesScreenRoute("movies_screen")
}
