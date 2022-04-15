package com.msk.moviesapplication.ui.movies.components

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.msk.moviesapplication.Util.Sorting_Value
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.ui.movies.DefaulthRadioButton
import com.msk.moviesapplication.ui.movies.MoviesEvent
import com.msk.moviesapplication.ui.movies.MoviesState
import com.msk.moviesapplication.ui.movies.MoviesViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
 fun OrderSectionbutton(
    moviesState: State<MoviesState>,
    MoviesViewModel: MoviesViewModel,
    SortingData: State<Sorting_data>
) {
    AnimatedVisibility(
        visible = moviesState.value.isOrderSectionVisible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    )
    {
        val genres= remember { MoviesViewModel.Genres }
        Column() {

            Column {
                Text("Sort By", style = MaterialTheme.typography.h5, modifier = Modifier.align(
                    Alignment.CenterHorizontally))
                Row {
                    DefaulthRadioButton("POPULARITY", selected =SortingData.value.Sorting_value is Sorting_Value.POPULARITY, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.POPULARITY)))} )
                    DefaulthRadioButton("RELEASE", selected =SortingData.value.Sorting_value is Sorting_Value.RELEASE, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.RELEASE)))} )
                }
                Row {
                    DefaulthRadioButton("ALPHABETICALLY", selected =SortingData.value.Sorting_value is Sorting_Value.ALPHABETICALLY, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.ALPHABETICALLY)))} )
                    DefaulthRadioButton("VOTE AVARAGE", selected =SortingData.value.Sorting_value is Sorting_Value.VOTE_AVARAGE, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.VOTE_AVARAGE)))} )
                }
                Row {
                    DefaulthRadioButton("REVENUE", selected =SortingData.value.Sorting_value is Sorting_Value.REVENUE, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.REVENUE)))} )
                    DefaulthRadioButton("VOTE COUNT", selected =SortingData.value.Sorting_value is Sorting_Value.VOTE_COUNT, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.VOTE_COUNT)))} )
                }
                Text("Genre", style = MaterialTheme.typography.h5, modifier = Modifier.align(
                    Alignment.CenterHorizontally))

                Divider()

                if (genres.value.genres.isNotEmpty()){
                    LazyVerticalGrid(
                        cells = GridCells.Adaptive(130.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                    ){

                        items(genres.value.genres) {
                            val BackgroundColor=if (SortingData.value.Genre.contains(it)) Color.Gray else MaterialTheme.colors.background
                            Card (shape = RoundedCornerShape(10.dp), onClick = {
                                MoviesViewModel.OnEvent(MoviesEvent.OrderSection(SortingData.value.copy(Genre = mutableListOf(it))))
                            }, modifier = Modifier.fillMaxSize(), border = BorderStroke(1.dp, Color.Black), backgroundColor =BackgroundColor){
                                Text(text =it.name, textAlign = TextAlign.Center, modifier =  Modifier.padding(5.dp))

                            }

                        }
                    }
                }



            }

        }



    }

}