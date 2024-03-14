package com.example.weather.components

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R

@Composable
fun  RowIconText(
    icon: Int,
    text: String,

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
                .padding(6.dp)
                .size(28.dp)
        )
        Text(
            text = text,
            fontSize = 15.sp
        )
    }
}

@Composable
fun ColumnIconText(
    icon: Int,
    text1: String,
    text2: String
){
    Card (
        modifier = Modifier.padding(4.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 14.dp)
        ){
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(54.dp)
                    .padding(4.dp)
            )
            Text(
                text = text1,
                fontSize = 15.sp,

                )
            Text(
                text = text2,
                fontSize = 24.sp,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnIconTextPreview(){
    ColumnIconText(R.drawable.sun, "Now", "29")
}