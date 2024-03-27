package com.example.plantcareai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantcareai.ui.theme.PlantCareAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantCareAITheme {
                // Your content setup here
                Surface(modifier = Modifier.fillMaxSize()) {
                    Greeting(name = "Android")
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello, $name!", modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun DefaultPreview() {
    PlantCareAITheme {
        Surface(modifier = Modifier.fillMaxSize()) {
        }
    }
}
