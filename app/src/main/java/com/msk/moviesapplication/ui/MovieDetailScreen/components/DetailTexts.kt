package com.msk.moviesapplication.ui.MovieDetailScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import kotlin.random.Random

@Composable
fun DetailText(details: State<Details?>, darktheme: State<Boolean>) {
    val color=if (darktheme.value) Color(0xFF252727) else Color(0xFFe4e4e2)
    details.value?.let {
        Card(shape = RoundedCornerShape(35.dp)) {

            Column(Modifier.background(color).padding(bottom = 70.dp)) {
                Text(
                    it.title,
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
                        Text(text = it.releaseDate, style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)

                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Vote avg", style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)
                        Icon(imageVector = Icons.Default.Star,contentDescription = null, tint = Color.Yellow)
                        Text(text =it.voteAverage.toString(), style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    for (genre in it.genres) {
                        Card(shape = RoundedCornerShape(20.dp), border = BorderStroke(2.dp, RandomColor()), modifier = Modifier.padding(horizontal = 4.dp) ) {
                            Text(text = genre.name, style = MaterialTheme.typography.body1, modifier = Modifier.padding(6.dp))
                        }
                    }

                }

                Text(
                    it.tagline,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(start = 4.dp, top = 10.dp, end = 4.dp)
                )
                Divider()
                Text(
                    it.overview,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(start =15.dp, top = 10.dp, end =15.dp)
                )


            }
        }

    }


}

fun RandomColor()= Color(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))

