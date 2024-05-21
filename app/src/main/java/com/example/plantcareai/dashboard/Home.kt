package com.example.plantcareai.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.YoutubeSearchedFor
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.DrawerValue
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.plantcareai.ui.theme.PlantCareAITheme
import kotlinx.coroutines.launch


val plantList = listOf(
    Plant(R.drawable.apple, "Apple"),
    Plant(R.drawable.maize, "Maize"),
    Plant(R.drawable.blueberry, "Blueberry"),
    Plant(R.drawable.peach, "Peach"),
    Plant(R.drawable.grape, "Grape"),
    Plant(R.drawable.cherry, "Cherry"),
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantSearchPage(navController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

        Column(modifier = Modifier.fillMaxSize()) {
            val myfont = FontFamily(Font(R.font.happy_monkey))
            TopAppBar(
                title = {
                    Text(
                        text = "PlantCare AI",
                        fontFamily = myfont,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 50.dp, vertical = 10.dp),
                        color = Color(0xFF0D6446)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Open menu", tint = Color(0xFF0D6446))
                    }
                }
            )

            ModalDrawer(
                drawerState = drawerState,
                drawerContent = {
                    PlantMenuDrawer { selectedItem ->
                        // Handle click action based on the selected item
                        when (selectedItem) {
                            "Home" -> { navController.navigate("home") }
                            "Logout" -> { navController.navigate("SignUp") }
                            "About" -> { navController.navigate("about") }
                            "History" -> { navController.navigate("history") }
                            "Camera" -> { navController.navigate("camera")}
                            "Market" -> { navController.navigate("market")}
                            "Chat" -> { navController.navigate("bot")}
                        }
                    }
                },
                content = {
                    PlantCareAITheme(darkTheme = isSystemInDarkTheme()) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            // Inside your Column composable
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Search, // Use the search icon from Material Icons
                                    contentDescription = "Search Icon",
                                    modifier = Modifier.padding(8.dp),
                                    tint = Color(0xFF0D6446)
                                )
                                OutlinedTextField(
                                    value = searchText,
                                    onValueChange = { searchText = it },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    placeholder = {
                                        Text(
                                            "Search plants...",
                                            color = Color(0xFF0D6446)
                                        )
                                    },
                                    textStyle = TextStyle(color = Color.Black),
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                                )
                            }

                            // Card grid with filtered plants
                            PlantCardGrid(plants = plantList.filter {
                                it.name.contains(
                                    searchText,
                                    ignoreCase = true
                                )
                            }, navController = navController)
                        }
                    }
                }
            )
        }
}


@Composable
fun PlantMenuDrawer(onItemClick: (String) -> Unit) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.menu)
    )
    val isPlaying = remember { mutableStateOf(true) }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying.value
    )
    LaunchedEffect(progress) {
        isPlaying.value = progress == 0f
    }

    Box(modifier = Modifier.fillMaxHeight()) {
        Image(
            painter = painterResource(id = R.drawable.platin),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column {
            DrawerItem(text = "Home", onItemClick)
            DrawerItem(text = "About", onItemClick)
            DrawerItem(text = "History", onItemClick)
            DrawerItem(text = "Camera", onItemClick)
            DrawerItem(text = "Market", onItemClick)
            DrawerItem(text = "Chat", onItemClick)
            Spacer(modifier = Modifier.height(150.dp))
            DrawerItem(text = "Logout", onItemClick)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 450.dp)
                .semantics { contentDescription = "LottieAnimation" }, // Set content description here
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                modifier = Modifier.fillMaxSize(),
                composition = composition,
                alignment = Alignment.Center,
                iterations = 10
            )
        }
    }
}

@Composable
fun DrawerItem(text: String, onItemClick: (String) -> Unit) {
    val myfont = FontFamily(Font(R.font.happy_monkey))
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onItemClick(text) },
        fontFamily = myfont,
        fontSize = 20.sp,
        color = Color.White
    )
}



@Composable
fun PlantCardGrid(plants: List<Plant>, navController: NavHostController) {
    val gridState = GridCells.Fixed(2) // Two columns

    Row(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = gridState,
            modifier = Modifier.weight(1f)
        ) {
            items(plants) { plant ->
                PlantCard(plant = plant, navController = navController)
            }
        }
    }
}

@Composable
fun PlantCard(plant: Plant, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth(0.45f)
            .fillMaxHeight(1f), // Adjust width as needed for 2 columns
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color(0xFF0D6446)),
        onClick = {
            when (plant.name) {
                "Apple" -> navController.navigate("apple")
                "Peach" -> navController.navigate("peach")
                "Maize" -> navController.navigate("maize")
                "Grape" -> navController.navigate("grape")
                "Cherry" -> navController.navigate("cherry")
                "Blueberry" -> navController.navigate("blueberry")
                else -> { /* Handle default case */ }
            }
        }
    ) {
        Column {
            Image(
                painter = painterResource(id = plant.imageId),
                contentDescription = plant.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = plant.name,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }
}
