package com.msk.moviesapplication.ui.movies


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.ui.MainActivity.LastStates
import com.msk.moviesapplication.ui.NoInternetConnection.NoInternetConnectionScreen
import com.msk.moviesapplication.ui.Util.MoviesScreenRoute
import com.msk.moviesapplication.ui.movies.components.Gridcontent
import com.msk.moviesapplication.ui.movies.components.OrderSectionbutton
import com.msk.moviesapplication.ui.movies.components.customizeAppBar

@Composable
fun MoviesScreen(
    navController: NavController,
    ThemeColor: MutableState<Boolean>,
    lastStates: MutableState<LastStates?>
){
    val scaffoldState= rememberScaffoldState()
    val MoviesViewModel= hiltViewModel<MoviesViewModel>()
    val moviesState=MoviesViewModel.MoviesState.collectAsState()
    val state= rememberLazyListState()
    val Sorting_data=MoviesViewModel.SortingData.collectAsState()

   LaunchedEffect(Unit){

       MoviesViewModel.SortingData.collect{
           if(lastStates.value==null){
               state.scrollToItem(0)
               MoviesViewModel.OnEvent(MoviesEvent.resetMovies)
           }
           else{
               MoviesViewModel.OnEvent(MoviesEvent.AddLastMovies(lastStates.value!!))
               lastStates.value=null

           }

       }


   }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {Surface(Modifier.fillMaxWidth().wrapContentHeight()) {

            customizeAppBar(ThemeColor)
        }}
        , content = { mainContent(state,moviesState,navController,MoviesViewModel,Sorting_data){
            lastStates.value= LastStates(moviesState = moviesState.value, sortingData = Sorting_data.value)
            MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.openDetailScreen(navController,it))
        }
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
    orderSectionClick:()->Unit={MoviesViewModel.OnEvent(MoviesEvent.ToggleOrderSection)},
    cardOnclick:(Int)->Unit
) {
    Column{

        Box(Modifier.fillMaxWidth()){
            IconButton(modifier = Modifier.size(40.dp).fillMaxWidth().align(Alignment.CenterEnd), onClick =orderSectionClick){
                Icon(Icons.Default.Sort,contentDescription = "Sort", modifier = Modifier.size(30.dp))
            }
        }
        OrderSectionbutton(moviesState,MoviesViewModel,SortingData)
        if (moviesState.value.movies==null&&moviesState.value.error.isNotBlank()){
            NoInternetConnectionScreen {

                MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.LoadNextPage)
                MoviesViewModel.OnEvent(MoviesEvent = MoviesEvent.resetMovies)

            }

        }
        Box(modifier = Modifier.fillMaxSize()) {
            Gridcontent(state,moviesState,navController,MoviesViewModel, cardOnclick = cardOnclick)
            if (moviesState.value.isLoading||moviesState.value.error.isNotBlank()){
            Box(modifier=Modifier.fillMaxSize(0.2f).align(Alignment.Center)) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).fillMaxSize(), strokeWidth = 7.dp)
            }
        }}
    }



}







