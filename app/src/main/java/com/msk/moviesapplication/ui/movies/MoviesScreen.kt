package com.msk.moviesapplication.ui.movies

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import com.msk.moviesapplication.Responces.Data.Discover.Result
import coil.compose.rememberImagePainter
import com.msk.moviesapplication.Util.addbaseUrl
import com.msk.moviesapplication.ui.theme.MoviesApplicationTheme

@Composable
fun MoviesScreen(
    navController: NavController,
    ThemeColor: MutableState<Boolean>
){
    val scaffoldState= rememberScaffoldState()
    val MoviesViewModel= hiltViewModel<MoviesViewModel>()
    val moviesState=MoviesViewModel.MoviesState
    val state= rememberLazyListState()
    LaunchedEffect(Unit){
        MoviesViewModel.SortingFlow.collect{
            state.scrollToItem(0)

            //MoviesViewModel.resetPagination()
        }

    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {Surface(Modifier.fillMaxWidth().wrapContentHeight()) {
            AppBar(ThemeColor)
        }}
        , content = { Gridcontent(state,moviesState,navController,MoviesViewModel) }
    )

}
@Composable
private fun AppBar( ThemeColor: MutableState<Boolean>) {
    val colorPrimary = if (ThemeColor.value) Color.Black else Color.White
    val colorSecondary = if (ThemeColor.value) Color.White else Color.Black

    Row(Modifier.background(colorPrimary).fillMaxWidth().height(50.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)
    {
        Box(){

        }
        Text(text = "Movies", color = Color.Red, style =MaterialTheme.typography.h4)

        IconButton(onClick = {ThemeColor.value=!ThemeColor.value}){
            Icon(imageVector = Icons.Default.WbSunny,contentDescription = null, tint = colorSecondary)
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Gridcontent(
    state: LazyListState,
    Movies: State<MoviesState>,
    navController: NavController,
    MoviesViewModel: MoviesViewModel
){
    LazyVerticalGrid(
        cells=GridCells.Fixed(2),
        state=state,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
    ){
        Movies.value.movies?.let {

            items(it.results.size){item->
                if (item >= it.results.size-1 &&!Movies.value.isLoading &&!Movies.value.endReached) {
                    MoviesViewModel.loadNextItems()
                }

                MovieBoxScreen(it.results.get(item),Modifier.height(200.dp).width(150.dp).padding(8.dp))

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

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieBoxScreen(movie:Result,modifier:Modifier=Modifier){
    Box(modifier){
        Card(shape = AbsoluteRoundedCornerShape(10.dp), modifier = Modifier.fillMaxSize()) {
            //val painter1= Icons.Default.Movie
            val painter= rememberImagePainter(data=movie.posterPath.addbaseUrl())
            when(painter.state){
                is ImagePainter.State.Loading->{
                    CircularProgressIndicator(modifier = Modifier.size(12.dp))
                }
                is ImagePainter.State.Error->{
                    Log.d("hatalar",(painter.state as ImagePainter.State.Error).result.throwable.localizedMessage ?: "")
                    painter.request.placeholder
                    Image(Icons.Default.Movie,contentDescription = "error")
                }
                else ->{
                    Image(painter,contentDescription = movie.originalTitle, contentScale = ContentScale.FillBounds, modifier = Modifier.fillMaxSize())

                }

            }
        }
        Box(modifier = Modifier.fillMaxWidth().background(Color(0xFF79272626)).align(Alignment.BottomCenter)){
            Text(movie.title, style = MaterialTheme.typography.h5, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, maxLines = 1, color = Color.White)
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesApplicationTheme(true) {

        }

    }
