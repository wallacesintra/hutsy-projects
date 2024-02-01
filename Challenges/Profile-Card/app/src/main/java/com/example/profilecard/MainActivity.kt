package com.example.profilecard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profilecard.ui.theme.ProfileCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenLayout()
                }
            }
        }
    }
}


@Composable
fun ScreenLayout(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileCard(
            userData = UserData(
                "Jane Doe",
                "jane.doe@gmail.com",
                "Freelancers for hire designed by Adrian for Epic Coders",
                R.drawable.femaleprofile
            )
        )
        ProfileCard(
            userData = UserData(
                "John Doe",
                "john.doe@gmail.com",
                "Freelancers for hire designed by Adrian for Epic Coders",
                R.drawable.maleprofile
            )
        )

    }
}
@Composable
fun ProfileCard(
//    modifier: Modifier = Modifier,
    userData: UserData,
){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .height(350.dp)
            .width(250.dp)
            .padding(15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = userData.profileImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp)
                    .clip(CircleShape)
            )
            Text(
                text = userData.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(5.dp)
            )
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 5.dp)
            ){
                Icon(
                    Icons.Rounded.MailOutline,
                    contentDescription = "mail",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = userData.email,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Text(
                text = userData.bio,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            val (isLoading, setIsLoading) = remember { mutableStateOf(false) }

            SideEffect {
                Log.d("TAG", "clicked : $isLoading")
            }

            val btnWidth = if (isLoading) 180.dp else 120.dp
            Button(
                onClick = { setIsLoading(!isLoading) },

                modifier = Modifier.animateContentSize(
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = LinearOutSlowInEasing
//                    )
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessHigh
                    )
                )
                    .width(btnWidth)
            ) {
                Text(
                    text = "View Profile",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileCardTheme {
        ProfileCard(
            userData = UserData(
                "Jane Doe",
                "jane.doe@gmail.com",
                "Freelancers for hire designed by Adrian for Epic Coders",
                R.drawable.femaleprofile
            )
        )
    }
}