package com.msk.moviesapplication.ui.MovieDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transition.CrossfadeTransition
import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.Util.addbaseUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoilApi::class)
@Composable
fun Poster(details: State<Details?>, state: LazyListState, scope: CoroutineScope) {
    details.value?.let {
        val backdroppath= rememberImagePainter(data = it.backdropPath.addbaseUrl(),builder = {
            transformations(BlurTransformation(LocalContext.current,5f))
            transition(CrossfadeTransition(1000,true))

        })
        val poster= rememberImagePainter(data = it.posterPath.addbaseUrl(), builder = {
            transformations(CircleCropTransformation())
        })
        when(backdroppath.state){
            is ImagePainter.State.Error->{
            }
            is ImagePainter.State.Loading->{
            }
            else -> {
                scope.launch {
                    state.scrollToItem(0)
                }
            }
        }

        Box {
            Image(
                painter = backdroppath,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(230.dp).align(Alignment.TopCenter),
                contentScale = ContentScale.FillWidth
            )

            Image(painter = poster,contentDescription = null, modifier = Modifier.height(200.dp).align(
                Alignment.Center))


        }


    }

}