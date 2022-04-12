package com.msk.moviesapplication.ui.MovieDetailScreen


import android.annotation.SuppressLint
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.msk.moviesapplication.ui.MovieDetailScreen.components.DetailText
import com.msk.moviesapplication.ui.MovieDetailScreen.components.Poster
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(navController: NavController, Movieid: Int, darktheme: State<Boolean>){
    val viewModel=hiltViewModel<DetailViewModel>()
    val details=viewModel.MovieDetailStateflow.collectAsState()

    SideEffect {
        viewModel.movieId=Movieid
        viewModel.OnEvent(DetailEvent.getdetails)
        viewModel.OnEvent(DetailEvent.getComment)
    }

        //BottomSheet()
    Scaffold {
        Content(navController,details,darktheme)

    }



}
@ExperimentalMaterialApi
@Composable
fun BottomSheet(){
    val state = rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = state,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetPeekHeight = 15.dp,
        sheetContent = {
            Column (Modifier.wrapContentHeight().fillMaxWidth().background(Color.Blue).padding(20.dp)){
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
                Text("s")
            }
        },
        content = {
            Button(onClick = {
                coroutineScope.launch {
                    state.bottomSheetState.expand()
                }
            }){
                Text(  "adanadaf")
            }
        }
    )

}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun Content(
    navController: NavController,
    details: State<MovieDetailState>,
    darktheme: State<Boolean>
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
                DetailText(details,darktheme)
            }
        }
    }
}



