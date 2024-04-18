package com.example.weather.presentation.screens.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.weather.R

@Composable
fun LocationNotFound(
    onDismissRequest: () -> Unit
){

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            border = BorderStroke(1.dp, MaterialTheme.colors.error),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(MaterialTheme.colors.onPrimary)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.no_location),
                    contentDescription = stringResource(id = R.string.no_location),
//                    tint = MaterialTheme.colors.error,
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = stringResource(id = R.string.no_location),
                    fontSize = 18.sp,
//                    color = MaterialTheme.colors.error,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )

            }

        }

    }
}

@Preview
@Composable
fun LocationNotFoundPreview(){
    LocationNotFound {

    }
}