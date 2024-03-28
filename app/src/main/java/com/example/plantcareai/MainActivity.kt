package com.example.plantcareai

import com.example.plantcareai.authentication.PreviewShowScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantcareai.authentication.AnimatedSplash

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Implementation of the navigation graph
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash_screen") {
                composable("splash_screen") {
                    AnimatedSplash(navController = navController)

                }
                composable("welcome") {
                    PreviewShowScreen(navController = navController)
                }
            }

        }
    }

}
