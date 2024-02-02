package com.example.profilecard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimateCard(){

    val (isClicked, setIsClicked) = remember{
        mutableStateOf(false)
    }

    val rotationZ = if (isClicked) -10f else 10f
    val translationY = if (isClicked) -100f else 100f
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),


        modifier = Modifier
            .size(200.dp)
            .padding(50.dp)
//            .clickable { setIsClicked(!isClicked) }
//            .animateContentSize(
//                animationSpec = spring(
//                    dampingRatio = Spring.DampingRatioMediumBouncy,
//                    stiffness = Spring.StiffnessHigh
//                )
//            )

            .pointerInput(isClicked) {
                detectTapGestures(onTap = {setIsClicked(!isClicked)})
            }

            .graphicsLayer(
//                translationX = 70f,
                translationY = translationY,
//                rotationX = 20f,
//                rotationY = -20f,
                rotationZ = -rotationZ
            )
            .animateContentSize(
//                animationSpec = spring(
//                    dampingRatio = Spring.DampingRatioNoBouncy,
//                    stiffness = Spring.StiffnessLow
//                )
                animationSpec = tween(
                    durationMillis = 600,
                    delayMillis = 100,
                    easing = FastOutSlowInEasing
                )
            )
    ){
        Text(text = "Animate Card")
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatePreview(){
    AnimateCard()
}