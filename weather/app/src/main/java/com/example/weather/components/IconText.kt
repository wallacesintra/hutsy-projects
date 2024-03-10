package com.example.weather.components

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun  RowIconText(
    icon: Int,
    text: String,
    iconSize: Int,
    textSize: Int
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
    ) {
        Icon(
            painter = painterResource(id = icon) ,
            contentDescription = "icon",
            modifier = Modifier
                .padding(4.dp)
                .size(iconSize.dp)
        )
        Text(
            text = text,
            fontSize = textSize.sp
        )
    }
}