package com.msk.moviesapplication.ui.movies.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.msk.moviesapplication.Responces.Data.Discover.Result
import com.msk.moviesapplication.Util.addbaseUrl

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalCoilApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun MovieBoxScreen(navController: NavController, movie: Result, modifier: Modifier = Modifier, cardOnclick:(Int)->Unit){
    Box(modifier){
        Card(shape = AbsoluteRoundedCornerShape(10.dp), modifier = Modifier.fillMaxSize(), onClick = {cardOnclick(movie.id)}) {
            val painter= rememberImagePainter(data=movie.posterPath?.addbaseUrl())
            when(painter.state){
                is ImagePainter.State.Loading->{
                    Box(modifier= Modifier.fillMaxSize(0.3f)) {
                        CircularProgressIndicator(modifier= Modifier.align(Alignment.Center))
                    }
                }
                is ImagePainter.State.Error->{
                    Image(Icons.Default.Movie,contentDescription = "error")
                }
                else->{
                    Image(painter,contentDescription = null,modifier=Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)

                }


            }
        }
        Box(modifier = Modifier.fillMaxWidth().background(Color(0xFF79272626)).align(Alignment.BottomCenter)){
            Text(movie.title, style = MaterialTheme.typography.h5, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, maxLines = 1, color = Color.White, modifier = Modifier.align(Alignment.Center))
        }
        Row(modifier=Modifier.align(Alignment.TopEnd).background(Color(0xFF79272626))) {
            Icon(Icons.Default.Star,contentDescription = null, tint = Color.Yellow,modifier=Modifier.size(35.dp))
            Text(text = movie.voteAverage.toString(), color = Color.Yellow, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
        }


    }
}
