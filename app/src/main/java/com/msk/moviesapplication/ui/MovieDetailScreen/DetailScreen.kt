package com.msk.moviesapplication.ui.MovieDetailScreen


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transition.CrossfadeTransition
import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.Util.addbaseUrl
import com.msk.moviesapplication.ui.MovieDetailScreen.components.DetailText
import com.msk.moviesapplication.ui.MovieDetailScreen.components.Poster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

@Composable
fun DetailScreen(navController: NavController, Movieid: Int, darktheme: State<Boolean>){
    val viewModel=hiltViewModel<DetailViewModel>()
    val details=viewModel.MovieDetailStateflow.collectAsState()

    SideEffect {
        viewModel.getDetails(Movieid)
    }

    Scaffold {
        Content(navController,details,darktheme)
    }


}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun Content(
    navController: NavController,
    details: State<Details?>,
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
                DetailText(details,darktheme)
            }
        }
    }
}



