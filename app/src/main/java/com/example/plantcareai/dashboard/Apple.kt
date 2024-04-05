package com.example.plantcareai.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.plantcareai.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ApplePlant(navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxSize()
        ) {
        val myfont = FontFamily(Font(R.font.happy_monkey))
        Column(modifier = Modifier.fillMaxHeight()){
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back", tint = Color(0xFF0D5210))
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.apple),
                    contentDescription = "image description",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(260.dp)
                        .offset(y = 10.dp)
                        .fillMaxHeight()
                )
                Spacer(modifier = Modifier.width(5.dp))
                Column (modifier = Modifier.offset(y = 8.dp)){
                    Text(text = "Humidity level: ", fontFamily = myfont, fontSize = 18.sp, color = Color(0xFF0D5210))
                    Text(text = "80% - 90%", fontFamily = FontFamily(Font(R.font.inter)), fontSize = 15.sp, color = Color(0xFF0D5210), modifier = Modifier.offset(x = 5.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Temperature", fontFamily = myfont, fontSize = 18.sp, color = Color(0xFF0D5210))
                    Text(text = "15 - 24", fontFamily = FontFamily(Font(R.font.inter)), fontSize = 15.sp, color = Color(0xFF0D5210), modifier = Modifier.offset(x = 5.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Sunlight: ", fontFamily = myfont, fontSize = 18.sp, color = Color(0xFF0D5210))
                    Text(text = "6 - 8hours (direct sunlight)", fontFamily = FontFamily(Font(R.font.inter)), fontSize = 15.sp, color = Color(0xFF0D5210), modifier = Modifier.offset(x = 5.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Soil pH: ", fontFamily = myfont, fontSize = 18.sp, color = Color(0xFF0D5210))
                    Text(text = "6.0 - 7.0", fontFamily = FontFamily(Font(R.font.inter)), fontSize = 15.sp, color = Color(0xFF0D5210), modifier = Modifier.offset(x = 5.dp))

                }

            }
            Spacer(modifier = Modifier.height(25.dp))
            val pagerState = rememberPagerState(pageCount = { 6 })
            Text(text = "Apple Plant (Malus domestica)", fontFamily = myfont, fontSize = 24.sp, color = Color(0xFF0D5210), modifier = Modifier.offset(x = 4.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Apples, belonging to the rose family, Rosaceae, share lineage with cherries, raspberries, and almonds. Despite their variety, they are all known for their stunning flowers and diverse fruits. However, apple trees face challenges such as apple scab, powdery mildew, and fire blight. To combat these diseases, orchard management is key. Regular pruning enhances airflow and sunlight, reducing fungal risks. Sanitation, including the removal of infected plant material, prevents disease spread. Fungicides like copper-based treatments curb apple scab, while sulfur-based options combat powdery mildew. Choosing resistant apple varieties decreases reliance on chemicals. Additionally, monitoring weather conditions allows for timely adjustments in management practices. Employing these strategies effectively manages diseases, ensuring healthy apple tree growth and robust fruit production.",modifier = Modifier.offset(x = 4.dp),  fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.inter)))
            Spacer(modifier = Modifier.height(15.dp))
            LazyRow(
                modifier = Modifier.padding(horizontal = 5.dp)

            ) {
                items(6) { index ->
                    when (index) {
                        0 -> InteractiveNeonRectangleWithImage1(navController = navController)
                        1 -> InteractiveNeonRectangleWithImage2(navController = navController)
                        2 -> InteractiveNeonRectangleWithImage3(navController = navController)
                        3 -> InteractiveNeonRectangleWithImage4(navController = navController)
                        4 -> InteractiveNeonRectangleWithImage5(navController = navController)
                        5 -> InteractiveNeonRectangleWithImage6(navController = navController)
                    }
                }
            }
        }

    }
}

@Composable
fun InteractiveNeonRectangleWithImage1(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.apple,
    navController: NavHostController,
) {
    val strokeColors = listOf(Color(0xFF027A12), Color.White)
    var isTapped by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.clickable(onClick = { navController.navigate("apple") })
    ) {
        Canvas(modifier = Modifier
            .width(150.dp) // Adjust as needed
            .height(200.dp) // Adjust as needed
            .background(color = Color(0x4D027A12))) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    if (isTapped) listOf(Color(0xFF027A12), Color(0xB007FFE5), Color.White) else strokeColors
                ),
                style = Stroke(width = 29f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(178.dp)
                .fillMaxSize()
        )

    }
    Spacer(modifier = Modifier.width(10.dp))


}
@Composable
fun InteractiveNeonRectangleWithImage2(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.peach,
    navController: NavHostController,
) {
    val strokeColors = listOf(Color(0xFF027A12), Color.White)
    var isTapped by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.clickable(onClick = { navController.navigate("peach") })
    ) {
        Canvas(modifier = Modifier
            .width(150.dp) // Adjust as needed
            .height(200.dp) // Adjust as needed
            .background(color = Color(0x4D027A12))) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    if (isTapped) listOf(Color(0xFF027A12), Color(0xB007FFE5), Color.White) else strokeColors
                ),
                style = Stroke(width = 29f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(178.dp)
                .fillMaxSize()
        )

    }
    Spacer(modifier = Modifier.width(10.dp))

}

@Composable
fun InteractiveNeonRectangleWithImage3(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.blueberry,
    navController: NavHostController,
) {
    val strokeColors = listOf(Color(0xFF027A12), Color.White)
    var isTapped by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.clickable(onClick = { navController.navigate("blueberry") })
    ) {
        Canvas(modifier = Modifier
            .width(150.dp) // Adjust as needed
            .height(200.dp) // Adjust as needed
            .background(color = Color(0x4D027A12))) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    if (isTapped) listOf(Color(0xFF027A12), Color(0xB007FFE5), Color.White) else strokeColors
                ),
                style = Stroke(width = 29f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(178.dp)
                .fillMaxSize()
        )
    }
    Spacer(modifier = Modifier.width(10.dp))


}
@Composable
fun InteractiveNeonRectangleWithImage4(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.grape,
    navController: NavHostController,
) {
    val strokeColors = listOf(Color(0xFF027A12), Color.White)
    var isTapped by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.clickable(onClick = { navController.navigate("grape") })
    ) {
        Canvas(modifier = Modifier
            .width(150.dp) // Adjust as needed
            .height(200.dp) // Adjust as needed
            .background(color = Color(0x4D027A12))) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    if (isTapped) listOf(Color(0xFF027A12), Color(0xB007FFE5), Color.White) else strokeColors
                ),
                style = Stroke(width = 29f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(178.dp)
                .fillMaxSize()
        )

    }
    Spacer(modifier = Modifier.width(10.dp))


}

@Composable
fun InteractiveNeonRectangleWithImage5(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.cherry,
    navController: NavHostController,
) {
    val strokeColors = listOf(Color(0xFF027A12), Color.White)
    var isTapped by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.clickable(onClick = { navController.navigate("cherry") })
    ) {
        Canvas(modifier = Modifier
            .width(150.dp) // Adjust as needed
            .height(200.dp) // Adjust as needed
            .background(color = Color(0x4D027A12))) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    if (isTapped) listOf(Color(0xFF027A12), Color(0xB007FFE5), Color.White) else strokeColors
                ),
                style = Stroke(width = 29f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(178.dp)
                .fillMaxSize()
        )

    }
    Spacer(modifier = Modifier.width(10.dp))


}
@Composable
fun InteractiveNeonRectangleWithImage6(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.maize,
    navController: NavHostController,
) {
    val strokeColors = listOf(Color(0xFF027A12), Color.White)
    var isTapped by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.clickable(onClick = { navController.navigate("maize") })
    ) {
        Canvas(modifier = Modifier
            .width(150.dp) // Adjust as needed
            .height(200.dp) // Adjust as needed
            .background(color = Color(0x4D027A12))) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    if (isTapped) listOf(Color(0xFF027A12), Color(0xB007FFE5), Color.White) else strokeColors
                ),
                style = Stroke(width = 29f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(178.dp)
                .fillMaxSize()
        )

    }
    Spacer(modifier = Modifier.width(10.dp))


}