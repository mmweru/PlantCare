package com.example.plantcareai.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.plantcareai.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CherryPlant(navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        val myfont = FontFamily(Font(R.font.happy_monkey))
        Column(modifier = Modifier.fillMaxHeight()){
            IconButton(onClick = { navController.navigate("home")}) {
                Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back", tint = Color(0xFF0D5210))
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.cherry),
                    contentDescription = "image description",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(260.dp)
                        .offset(y = 10.dp)
                        .fillMaxHeight()
                )
                Spacer(modifier = Modifier.width(5.dp))
                Column (modifier = Modifier.offset(y = 8.dp)){
                    Text(text = "Watering: ", fontFamily = myfont, fontSize = 18.sp, color = Color(0xFF0D5210))
                    Text(text = "(Adequate and constantly)", fontFamily = FontFamily(Font(R.font.inter)), fontSize = 15.sp, color = Color(0xFF0D5210), modifier = Modifier.offset(x = 5.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Temperature", fontFamily = myfont, fontSize = 18.sp, color = Color(0xFF0D5210))
                    Text(text = "15 - 27", fontFamily = FontFamily(Font(R.font.inter)), fontSize = 15.sp, color = Color(0xFF0D5210), modifier = Modifier.offset(x = 5.dp))
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
            Text(text = "Cherry Plant  (Prunus avium)", fontFamily = myfont, fontSize = 24.sp, color = Color(0xFF0D5210), modifier = Modifier.offset(x = 4.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Prunus avium, known as cherry, is a deciduous tree native to Europe, Western Asia, and North Africa. Cherries are celebrated for their sweet and tart flavor profiles. Disease management entails regular pruning to improve sunlight penetration and airflow, as well as employing fungicides to combat diseases like cherry leaf spot and brown rot. Additionally, selecting disease-resistant cultivars and maintaining overall tree health are essential for successful cherry production.", fontSize = 12.sp, fontFamily = FontFamily(
                Font(R.font.inter)
            ), modifier = Modifier.offset(x = 4.dp)
            )
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