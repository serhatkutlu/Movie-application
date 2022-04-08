package com.msk.moviesapplication.ui.movies

import android.annotation.SuppressLint
import android.text.style.ParagraphStyle
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import com.msk.moviesapplication.Responces.Data.Discover.Result
import coil.compose.rememberImagePainter
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.Util.addbaseUrl
import com.msk.moviesapplication.ui.theme.MoviesApplicationTheme
import kotlin.math.log

@Composable
fun MoviesScreen(
    navController: NavController,
    ThemeColor: MutableState<Boolean>
){
    val scaffoldState= rememberScaffoldState()
    val MoviesViewModel= hiltViewModel<MoviesViewModel>()
    val moviesState=MoviesViewModel.MoviesState
    val state= rememberLazyListState()
    val Sorting_data=MoviesViewModel.SortingData.collectAsState()



   LaunchedEffect(Unit){
       MoviesViewModel.SortingData.collect{
           state.scrollToItem(0)
           MoviesViewModel.resetmovies()
       }
   }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {Surface(Modifier.fillMaxWidth().wrapContentHeight()) {
            AppBar(ThemeColor)
        }}
        , content = { mainContent(state,moviesState,navController,MoviesViewModel,Sorting_data)
        }
    )

}
@Composable
fun mainContent(
    state: LazyListState,
    moviesState: State<MoviesState>,
    navController: NavController,
    MoviesViewModel: MoviesViewModel,
    SortingData:State<Sorting_data>,
    orderSectionClick:()->Unit={MoviesViewModel.OnEvent(MoviesEvent.ToggleOrderSection)}
) {
    Column{

        Box(Modifier.fillMaxWidth()){
            IconButton(modifier = Modifier.size(40.dp).fillMaxWidth().align(Alignment.CenterEnd), onClick =orderSectionClick){
                Icon(Icons.Default.Sort,contentDescription = "Sort", modifier = Modifier.size(30.dp))
            }
        }
            OrderSection(moviesState,MoviesViewModel,SortingData)


        Gridcontent(state,moviesState,navController,MoviesViewModel)
    }



}
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
private fun OrderSection(
    moviesState: State<MoviesState>,
    MoviesViewModel: MoviesViewModel,
    SortingData: State<Sorting_data>
) {
    AnimatedVisibility(
        visible = moviesState.value.isOrderSectionVisible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut()+ slideOutVertically())
    {
        val genres= remember { MoviesViewModel.Genres }
        Column() {

            Column {
                Text("Sort By", style = MaterialTheme.typography.h5, modifier = Modifier.align(
                    Alignment.CenterHorizontally))
                Row {
                    DefaulthRadioButton("POPULARITY", selected =SortingData.value.Sorting_value is Sorting_Value.POPULARITY, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.POPULARITY)))} )
                    DefaulthRadioButton("RELEASE", selected =SortingData.value.Sorting_value is Sorting_Value.RELEASE, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.RELEASE)))} )
                    DefaulthRadioButton("REVENUE", selected =SortingData.value.Sorting_value is Sorting_Value.REVENUE, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.REVENUE)))} )
                }
                Row {
                    DefaulthRadioButton("ALPHABETICALLY", selected =SortingData.value.Sorting_value is Sorting_Value.ALPHABETICALLY, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.ALPHABETICALLY)))} )
                    DefaulthRadioButton("VOTE AVARAGE", selected =SortingData.value.Sorting_value is Sorting_Value.VOTE_AVARAGE, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.VOTE_AVARAGE)))} )
                }
                Row {
                    DefaulthRadioButton("VOTE COUNT", selected =SortingData.value.Sorting_value is Sorting_Value.VOTE_COUNT, onSelected = {MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.OrderSection(SortingData.value.copy(Sorting_value = Sorting_Value.VOTE_COUNT)))} )
                }
                Text("Genre", style = MaterialTheme.typography.h5, modifier = Modifier.align(Alignment.CenterHorizontally))

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
@Composable
private fun AppBar( ThemeColor: MutableState<Boolean>) {
    val colorPrimary = if (ThemeColor.value) Color.Black else Color.Red
    val colorSecondary = if (ThemeColor.value) Color.White else Color.Black
    val colorThird = if (ThemeColor.value) Color.Red else Color.White

    Row(Modifier.background(colorPrimary).fillMaxWidth().height(50.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)
    {
        Box(){

        }
        Text(text = "Movies", color = colorThird, style =MaterialTheme.typography.h4.copy(fontFamily = FontFamily.SansSerif, textGeometricTransform = TextGeometricTransform(1.5f)), modifier = Modifier.offset(x = 18.dp))

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
            items(it.results.count()){item->
                if (item >= it.results.size-1 &&!Movies.value.isLoading &&!Movies.value.endReached) {
                    MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.LoadNextPage)
                }

                    MovieBoxScreen(it.results[item],Modifier.height(200.dp).width(150.dp).padding(8.dp))

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
            val painter= rememberImagePainter(data=movie.posterPath?.addbaseUrl())
            when(painter.state){
                is ImagePainter.State.Loading->{
                    CircularProgressIndicator(modifier = Modifier.size(12.dp))
                }
                is ImagePainter.State.Error->{
                    Log.d("hatalar",(painter.state as ImagePainter.State.Error).result.throwable.localizedMessage ?: "image")
                    Image(Icons.Default.Movie,contentDescription = "error")
                }
               else->{
                    Image(painter,contentDescription = null, contentScale = ContentScale.FillBounds, modifier = Modifier.fillMaxSize())

                }


            }
        }
        Box(modifier = Modifier.fillMaxWidth().background(Color(0xFF79272626)).align(Alignment.BottomCenter)){
            //Text(movie.title, style = MaterialTheme.typography.h5, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, maxLines = 1, color = Color.White)
        }


    }
}

