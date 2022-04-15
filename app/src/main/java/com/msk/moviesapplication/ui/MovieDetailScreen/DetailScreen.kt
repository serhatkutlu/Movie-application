package com.msk.moviesapplication.ui.MovieDetailScreen


import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.msk.moviesapplication.ui.MovieDetailScreen.components.DetailText
import com.msk.moviesapplication.ui.MovieDetailScreen.components.Poster
import com.msk.moviesapplication.ui.MovieDetailScreen.components.commentBox
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(navController: NavController, Movieid: Int, darktheme: State<Boolean>){
    val viewModel=hiltViewModel<DetailViewModel>()
    val details=viewModel.MovieDetailStateflow.collectAsState()
    LaunchedEffect(true) {
        viewModel.movieId=Movieid
        viewModel.OnEvent(DetailEvent.getdetails)
        viewModel.OnEvent(DetailEvent.getComment)
    }

    BottomSheet(navController,details,darktheme,viewModel)

}
@ExperimentalMaterialApi
@Composable
fun BottomSheet(
    navController: NavController,
    details: State<MovieDetailState>,
    darktheme: State<Boolean>,
    viewModel: DetailViewModel
) {
    val state = rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    val coroutineScope = rememberCoroutineScope()
    val backgroundColor=if (darktheme.value) Color.DarkGray else Color.LightGray
    val dividerColor=if (darktheme.value) Color.White else Color.Black
    val showsheet:()-> Unit= {
        coroutineScope.launch {
            state.bottomSheetState.expand()
        }
    }
    BottomSheetScaffold(
        scaffoldState = state,
        sheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        sheetPeekHeight = 0.dp,
        backgroundColor = backgroundColor,
        sheetContent = {

            Column (Modifier.fillMaxHeight(0.85f).fillMaxWidth().padding(25.dp)){
                Divider(Modifier.width(100.dp).height(5.dp).align(Alignment.CenterHorizontally).offset(y = -20.dp) , color = dividerColor)

                LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    details.value.comment?.results?.let {results->
                        items(results.size){item->
                            details.value.also {
                                if (it.isLoading==false&&it.endReached==false&&results.size-1<=item){
                                    viewModel.OnEvent(DetailEvent.getComment)
                                }
                            }
                            commentBox(results[item],100)
                        }
                    }

                }

            }
        },
        content = {
            Content(details,darktheme,showsheet)

            BackHandler(state.bottomSheetState.isExpanded) {
                coroutineScope.launch {
                    state.bottomSheetState.collapse()
                }
            }

}
    )

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun Content(
    details: State<MovieDetailState>,
    darktheme: State<Boolean>,
    showsheet: () -> Unit
){
    val state= rememberLazyListState()
    val scope= rememberCoroutineScope()
    Box{
        LazyColumn(state = state) {
            item{
                Poster(details,state,scope)
            }
            item{
                Spacer(modifier = Modifier.height(15.dp))
                DetailText(details,darktheme,showsheet)
            }
        }
    }
}



