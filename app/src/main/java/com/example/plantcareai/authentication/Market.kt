package com.example.plantcareai.authentication

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FloatAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeableDefaults.AnimationSpec
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.ImagePainter
import com.example.plantcareai.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedMarket(navController: NavHostController){
    var startAnimation by remember{
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(5500)
        navController.popBackStack()
        navController.navigate("shop")
    }
    MarketPlaceScreen()

}

@Composable
fun MarketPlaceScreen() {
    var animationState by remember { mutableStateOf(0) } // 0 - market, 1 - shopping
    val textList = listOf("Market ", "Shopping ")
    var text by remember { mutableStateOf("") }

    LaunchedEffect(key1 = animationState) {
        text = ""
        for (char in textList[animationState]) {
            text += char
            delay(200) // delay between each character
        }
        delay(1000) // delay before switching to the next word
        animationState = (animationState + 1) % 2
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.shopy), // Replace with your image resource
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // White Circle
        val myfont = FontFamily(Font(R.font.happy_monkey))
        Box(
            modifier = Modifier
                .size(180.dp)
                .background(
                    color = Color.White.copy(alpha = 0.8f), // Set the alpha transparency for the background color
                    shape = CircleShape
                )
                .align(Alignment.Center)
                .shadow(
                    elevation = 4.dp,
                    shape = CircleShape,
                    clip = true
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$text Place",
                color = when (animationState) {
                    0 -> Color(0xFF0D5210)
                    1 -> Color.Black
                    else -> error("Unexpected animation state")
                },
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center),
                fontFamily = myfont
            )
        }
    }
}
