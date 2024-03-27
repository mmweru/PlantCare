package com.example.plantcareai.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.plantcareai.R


@Preview
@Composable
fun Splash(){
        Box {
            LogoText()
            Lottie()
        }


}

@Preview
@Composable
fun LogoText(){
    Box(
        Modifier
            .width(393.dp)
            .height(361.dp)
            .offset(y = 430.dp)
            .background(color = Color(0xFF0D6446), shape = RoundedCornerShape(size = 7.dp))
    ){
        Column {
            Text(
                text = "PLANTCARE AI",
                style = TextStyle(
                    fontSize = 38.sp,
                    fontFamily = FontFamily(Font(R.font.happy_monkey)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                ), modifier = Modifier
                    .width(274.dp)
                    .height(65.dp)
                    .offset(x = 50.dp, y = 190.dp), textAlign = TextAlign.Center

            )
            Row(
                horizontalArrangement = Arrangement.spacedBy((-5).dp, Alignment.Start),
                verticalAlignment = Alignment.Top, modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 190.dp)
                    .height(101.dp)
            ) {
                MyImage()
                MyImage()
                MyImage()
                MyImage()
            }
        }
    }
}

@Composable
fun Lottie(){
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie))
    var isPlaying by remember {
        mutableStateOf(true)
    }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
    )
    LaunchedEffect(key1 = progress) {
        if (progress == 0f){
            isPlaying = true
        }
        if (progress == 1f){
            isPlaying = false
        }
    }
    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 140.dp),
        Alignment.Center
    ) {
        LottieAnimation(
            modifier = Modifier
                .width(393.dp)
                .height(616.dp),
            composition = composition,
            alignment = Alignment.Center,
            restartOnPlay = true
        )
    }
}

@Composable
fun MyImage(){
    Image(
        painter = painterResource(id = R.drawable.fruits2),
        contentDescription = "image description",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(101.dp)
            .height(101.dp)
    )
}