package com.msk.moviesapplication.ui.NoInternetConnection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrowserNotSupported
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.msk.moviesapplication.ui.movies.MoviesEvent

@Composable
fun NoInternetConnectionScreen(onclick:()->Unit){
    Box (modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No internet connection", style = MaterialTheme.typography.h5)
            Icon(
                Icons.Default.BrowserNotSupported,
                contentDescription = "no internet connection",
                modifier = Modifier.fillMaxSize(0.1f)
            )
            Button(onClick = onclick) {
                Text(text = "retry", style = MaterialTheme.typography.h6)
            }
        }
    }
}
