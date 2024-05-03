package com.example.plantcareai.dashboard

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.plantcareai.R

@Composable
fun YourScreen(navController: NavHostController) {
    val myfont = FontFamily(Font(R.font.happy_monkey))

    val infiniteTransition = rememberInfiniteTransition()

    val buttonWidth1 by infiniteTransition.animateFloat(
        initialValue = 80f,
        targetValue = 120f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val buttonWidth2 by infiniteTransition.animateFloat(
        initialValue = 80f,
        targetValue = 120f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing, delayMillis = 333),
            repeatMode = RepeatMode.Reverse
        )
    )

    val buttonWidth3 by infiniteTransition.animateFloat(
        initialValue = 80f,
        targetValue = 120f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing, delayMillis = 666),
            repeatMode = RepeatMode.Reverse
        )
    )
    IconButton(onClick = { navController.navigate("home") }) {
        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back", tint = Color(0xFF0D5210))
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(buttonWidth3.dp)
                        .padding(top = 16.dp), colors = ButtonDefaults.buttonColors(Color(0xFF0D5210))
                ) {
                    Text("Light", color = Color.White)
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(buttonWidth2.dp)
                        .padding(top = 16.dp), colors = ButtonDefaults.buttonColors(Color(0xFF0D5210))
                ) {
                    Text("Temp.", color = Color.White)
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(buttonWidth1.dp)
                        .padding(top = 16.dp), colors = ButtonDefaults.buttonColors(Color(0xFF0D5210))
                ) {
                    Text("Humidity", color = Color.White)
                }
            }

            Image(
                painter = painterResource(id = R.drawable.planth),
                contentDescription = "Your Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            )
        }
        Text(text = "About PlantCare AI", color = Color(0xFF0D5210), fontSize = 22.sp, fontFamily = myfont)

        Text(
            text = "Ever worried your plant is whispering secrets of a mysterious illness? PlantCare AI is here to be your green thumb's best friend! Simply snap a picture and our intelligent assistant will diagnose potential problems and suggest solutions, all within seconds. Watch your favorite flora flourish with the power of AI in the palm of your hand!",
            color = Color.Black,
            modifier = Modifier.padding(16.dp), fontSize = 18.sp, fontFamily = myfont
        )
    }
}
