package com.msk.moviesapplication.ui.MovieDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.msk.moviesapplication.Responces.Data.comments.Result
import com.msk.moviesapplication.Util.ChangeAvatarImage


@OptIn(ExperimentalCoilApi::class)
@Composable
fun commentBox( result: Result,maxline:Int=4){
    Box(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
        Row(modifier = Modifier.padding(15.dp)) {
            result.let {
                    val avatar= result.authorDetails.avatarPath?.ChangeAvatarImage()
                    val painter= rememberImagePainter(avatar, builder = {
                        transformations(RoundedCornersTransformation(15f))
                    })
                    when (painter.state){
                        is ImagePainter.State.Loading ->{}
                        is ImagePainter.State.Error->{

                            Icon(imageVector= Icons.Default.SupervisedUserCircle,contentDescription = null, modifier = Modifier.width(45.dp).height(45.dp))

                        }
                        else->{

                            Image(painter = painter, contentDescription ="user avatar", modifier = Modifier.width(45.dp).height(45.dp))

                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = result.author ?: "Unkown",
                            style = MaterialTheme.typography.body2,
                            maxLines = 1, fontWeight = FontWeight.ExtraBold, textDecoration = TextDecoration.Underline
                        )
                        Text(
                            result.content ?: "",
                            style = MaterialTheme.typography.body2,
                            maxLines = maxline, overflow = TextOverflow.Ellipsis
                        )


                    }
                }}


    }


}