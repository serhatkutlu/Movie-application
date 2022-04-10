package com.msk.moviesapplication.ui.MovieDetailScreen

import android.graphics.BlurMaskFilter
import android.provider.MediaStore
import android.transition.Transition
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transition.CrossfadeTransition
import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.Util.addbaseUrl
import com.msk.moviesapplication.ui.theme.MoviesApplicationTheme

@Composable
fun DetailScreen(navController: NavController,Movieid:Int){
    val viewModel=hiltViewModel<DetailViewModel>()
    val details=viewModel.MovieDetailStateflow.collectAsState()
    SideEffect {
        viewModel.getDetails(Movieid)
    }
    Content(navController,details)
}
@Composable
private fun Content(navController: NavController, details: State<Details?>){
        Column {
            Poster(details)

            Text("ddddddddddd")
            Text("ddddddddddd")
            Text("ddddddddddd")
            Text("ddddddddddd")
            Text("ddddddddddd")
        }


}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Poster(details: State<Details?>) {
    details.value?.let {
       val backdroppath= rememberImagePainter(data = it.backdropPath.addbaseUrl(),builder = {
           transformations(BlurTransformation(LocalContext.current,5f))
           transition(CrossfadeTransition(1000,true))

       })
        val poster= rememberImagePainter(data = it.posterPath.addbaseUrl(), builder = {
            transformations(CircleCropTransformation())
        })

        Box() {
            Image(
                painter = backdroppath,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
                contentScale = ContentScale.FillWidth
            )

            Image(painter = poster,contentDescription = null, modifier = Modifier.height(200.dp).align(
                Alignment.Center))

        }


    }

}

@Preview
@Composable
fun prewiew(){
    MoviesApplicationTheme {

    }
}