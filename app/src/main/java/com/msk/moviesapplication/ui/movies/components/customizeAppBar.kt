package com.msk.moviesapplication.ui.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.dp

@Composable
fun customizeAppBar( ThemeColor: MutableState<Boolean>) {
    val colorPrimary = if (ThemeColor.value) Color.Black else Color.Red
    val colorSecondary = if (ThemeColor.value) Color.White else Color.Black
    val colorThird = if (ThemeColor.value) Color.Red else Color.White

    Row(
        Modifier.background(colorPrimary).fillMaxWidth().height(50.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)
    {
        Box(){

        }
        Text(text = "Movies", color = colorThird, style = MaterialTheme.typography.h4.copy(fontFamily = FontFamily.SansSerif, textGeometricTransform = TextGeometricTransform(1.5f)), modifier = Modifier.offset(x = 18.dp))

        IconButton(onClick = {ThemeColor.value=!ThemeColor.value}){
            Icon(imageVector = Icons.Default.WbSunny,contentDescription = null, tint = colorSecondary)
        }

    }
}