package com.example.plantcareai.authentication

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.plantcareai.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplash(navController: NavHostController){
    var startAnimation by remember{
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec= tween(
            durationMillis = 4500
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(5500)
        navController.popBackStack()
        navController.navigate("welcome")
    }
    Splash(alpha = alphaAnim.value, modifier = Modifier.semantics { contentDescription = "Splash" })

}

@Composable
fun Splash(alpha: Float, modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
            .semantics { contentDescription = "Box" },
        contentAlignment = Alignment.Center
    ) {
        LogoText()
        Lottie()
    }
}

@Composable
fun LogoText() {
    Box(
        modifier = Modifier
            .width(393.dp)
            .height(361.dp)
            .background(color = Color(0xFF0D6446), shape = RoundedCornerShape(7.dp))
            .padding(vertical = 430.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PLANTCARE AI",
                style = TextStyle(
                    fontSize = 38.sp,
                    fontFamily = FontFamily(Font(R.font.happy_monkey)),
                    fontWeight = FontWeight(400),
                    color = Color.White
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy((-5).dp),
                verticalAlignment = Alignment.Top
            ) {
                repeat(4) {
                    MyImage()
                }
            }
        }
    }
}

@Composable
fun Lottie() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lottie)
    )
    val isPlaying = remember {
        mutableStateOf(true)
    }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying.value
    )
    LaunchedEffect(progress) {
        isPlaying.value = progress == 0f
    }
    Box(
        modifier = Modifier.fillMaxSize()
            .semantics { contentDescription = "LottieAnimation" }, // Set content description here
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(393.dp, 616.dp),
            composition = composition,
            alignment = Alignment.Center,
            restartOnPlay = true
        )
    }
}


@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.fruits2),
        contentDescription = "image description",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(101.dp)
    )
}
