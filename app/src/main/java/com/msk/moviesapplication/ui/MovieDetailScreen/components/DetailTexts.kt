package com.msk.moviesapplication.ui.MovieDetailScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.msk.moviesapplication.Responces.Data.comments.comment
import com.msk.moviesapplication.ui.MovieDetailScreen.MovieDetailState
import kotlin.random.Random

@Composable
fun DetailText(details: State<MovieDetailState>, darktheme: State<Boolean>, showsheet: () -> Unit) {
    //val color=if (darktheme.value) Color(0xFF252727) else Color(0xFFe4e4e2)
    details.value.details?.let {
        Card(shape = RoundedCornerShape(35.dp)) {
            val randomColors:MutableList<Color> = remember {
                val list= mutableListOf<Color>()
                repeat(5){
                    list.add(RandomColor())
                }
            return@remember list}

            Column(Modifier.padding(bottom = 70.dp)) {
                Text(
                    it.title ?:"",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(start = 3.dp, top = 30.dp, end = 3.dp)
                )
                Divider()
                Spacer(Modifier.height(30.dp))
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Duration", style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)
                        Icon(imageVector = Icons.Default.Timer,contentDescription = null)
                        Text(text = it.runtime.toString(), style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)

                    }
                    Spacer(Modifier.width(3.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Date", style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)
                        Icon(imageVector = Icons.Default.DateRange,contentDescription = null)
                        Text(text = it.releaseDate ?:"", style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)

                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Vote avg", style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)
                        Icon(imageVector = Icons.Default.Star,contentDescription = null, tint = Color.Yellow)
                        Text(text =it.voteAverage.toString(), style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    val fontsize=if(it.genres?.size ?:0 >3)  10.sp else 15.sp
                    for (i in (0..(it.genres?.size?.minus(1) ?: 0))) {
                            Card(shape = RoundedCornerShape(20.dp), border = BorderStroke(2.dp, randomColors[i]), modifier = Modifier.padding(horizontal = 4.dp) ) {

                            it.genres?.get(i)?.let { it1 ->

                                Text(text = it1.name, style = MaterialTheme.typography.body2.copy(fontSize = fontsize), modifier = Modifier.padding(6.dp))
                            }
                        }
                    }

                }

                Text(
                    it.tagline ?:"",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(start = 4.dp, top = 10.dp, end = 4.dp)
                )
                Divider()
                Text(
                    it.overview ?:"",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(start =15.dp, top = 10.dp, end =15.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                details.value.comment?.let {
                    Column(Modifier.clickable { showsheet() }) {
                        if(it.results.size>1){
                            Text("See all(${it.totalResults})", style = MaterialTheme.typography.h6, fontWeight = FontWeight.ExtraBold, color = Color.Red, modifier = Modifier.align(Alignment.End).padding(vertical = 15.dp))

                        }
                        if (it.results.isNotEmpty()){
                            commentBox(it.results[0])
                        }

                    }

                }


            }

        }

    }


}

fun RandomColor()= Color(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
