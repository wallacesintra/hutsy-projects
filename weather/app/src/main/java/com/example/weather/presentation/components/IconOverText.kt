package com.example.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.presentation.utils.drawableMap

@Composable
fun IconOverText(
    iconName: String,
    text: String
){
    val icon = drawableMap[iconName] ?: R.drawable.ic_launcher_foreground
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(15.dp)
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Serif,
            fontSize = 180.sp,
            letterSpacing = 0.01.sp,
            modifier = Modifier.padding(5.dp)
        )
        Image(
            painter = painterResource(icon),
            contentDescription = "weather Icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(130.dp)
                .shadow(
                    elevation = 0.001.dp,
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconOverTextPreview(){
    IconOverText("Rain", "43")
}