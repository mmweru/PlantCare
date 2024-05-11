package com.example.plantcareai.authentication

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.plantcareai.R
import kotlinx.coroutines.delay

@Composable
fun TripleImageScreen(navController: NavHostController) {
    var imageState by remember { mutableStateOf(0) } // Tracks current image index



    val images = listOf(
        R.drawable.first, // Replace with your image resources
        R.drawable.second,
        R.drawable.third
    )

    val animationSpec = repeatable<Float>( // Specify animation type (Float for progress)
        iterations = Int.MAX_VALUE,
        repeatMode = RepeatMode.Restart,
        animation = tween(durationMillis = 90000) // Adjust duration for image transition
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background Image (Animated)
        Image(
            painter = painterResource(id = images[imageState]),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds // Fill entire screen
        )

        IconButton(onClick = { navController.navigate("home") }) {
            Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back", tint = Color.White)
        }

        LaunchedEffect(animationSpec) {
            while (true) {
                delay(5000)
                imageState = (imageState + 1) % images.size
            }
        }
        val myfont = FontFamily(Font(R.font.happy_monkey))
        Column(modifier = Modifier.offset(y = 250.dp, x = 15.dp)) {
            Text(
                text = "PlantCare",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = myfont,
                fontWeight = FontWeight(400)
            )
        }
        Column(modifier = Modifier.offset(y = 275.dp, x = 15.dp)) {
            Text(
                text = "Market",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = myfont,
                fontWeight = FontWeight(400)
            )
        }


        // White Box with Rounded Corners

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.50f) // Cover bottom 3/4 of the screen
                .background(Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Our Market Services",
                    style = MaterialTheme.typography.h5,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 26.dp),
                    fontFamily = myfont
                )

                OutlinedButton(
                    onClick = { /* Handle Location click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 22.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF0D5210),
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color(0xFF0D5210)),
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color(0xFF0D5210)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Location", fontFamily = myfont)
                }

                OutlinedButton(
                    onClick = { /* Handle Order click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 22.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF0D5210),
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color(0xFF0D5210)),
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Order",
                        tint = Color(0xFF0D5210)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Order", fontFamily = myfont)
                }

                OutlinedButton(
                    onClick = { /* Handle Recommendations click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 22.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF0D5210),
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color(0xFF0D5210)),
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        Icons.Outlined.Star,
                        contentDescription = "Recommendations",
                        tint = Color(0xFF0D5210)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Recommendations", fontFamily = myfont)
                }
            }
        }

    }
}


