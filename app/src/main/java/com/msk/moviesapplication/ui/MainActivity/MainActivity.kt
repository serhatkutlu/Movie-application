package com.msk.moviesapplication.ui.MainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msk.moviesapplication.api.MovieApi
import com.msk.moviesapplication.ui.MovieDetailScreen.DetailScreen
import com.msk.moviesapplication.ui.Util.MoviesScreenRoute
import com.msk.moviesapplication.ui.movies.MoviesScreen
import com.msk.moviesapplication.ui.theme.MoviesApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var MovieApi: MovieApi


    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darktheme:MutableState<Boolean> = remember { mutableStateOf(true) }
            MoviesApplicationTheme(darkTheme =darktheme.value ) {
                val laststates: MutableState<LastStates?> = mutableStateOf(null)
                val navController= rememberNavController()
                NavHost(navController, startDestination = MoviesScreenRoute.MoviesMainScreen.route){
                    composable(route=MoviesScreenRoute.MoviesMainScreen.route){
                        MoviesScreen(navController,darktheme,laststates)
                    }
                    composable(route = MoviesScreenRoute.MoviesDetail.route+"?movieid={movieId}", arguments = listOf(
                        navArgument(
                            name = "movieId"
                        ){
                            type= NavType.IntType
                        }
                    ))
                    {
                        val MovieId=it.arguments?.getInt("movieId") ?: 634649
                        DetailScreen(navController, Movieid = MovieId)
                    }
                }
            }
        }
    }
}

