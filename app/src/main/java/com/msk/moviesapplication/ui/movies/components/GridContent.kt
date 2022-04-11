package com.msk.moviesapplication.ui.movies.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msk.moviesapplication.ui.movies.MoviesEvent
import com.msk.moviesapplication.ui.movies.MoviesState
import com.msk.moviesapplication.ui.movies.MoviesViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
 fun Gridcontent(
    state: LazyListState,
    Movies: State<MoviesState>,
    navController: NavController,
    MoviesViewModel: MoviesViewModel,
    cardOnclick:(Int)->Unit
){
    LazyVerticalGrid(

        cells= GridCells.Fixed(2),
        state=state,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
    ){

        Movies.value.movies?.let {
            items(it.results.size){item->

                if (item >= it.results.size-2 &&!Movies.value.isLoading &&!Movies.value.endReached) {
                    MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.LoadNextPage)
                }

                MovieBoxScreen(navController,it.results[item],
                    Modifier.height(400.dp).width(600.dp).padding(8.dp), cardOnclick = cardOnclick)

            }



            if (Movies.value.isLoading){
                item {
                    Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.TopCenter) {
                        CircularProgressIndicator(Modifier.fillMaxSize(0.4f).offset(x=100.dp))

                    }
                }

            }



        }


    }



}