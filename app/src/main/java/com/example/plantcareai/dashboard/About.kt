package com.example.plantcareai.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantcareai.R


@Preview
@Composable
fun AboutPage() {
    val background = painterResource(id = R.drawable.image1)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = background,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )

        Surface(
            color = Color.Transparent, // Make the surface background transparent
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()) .offset(y = 140.dp)
            ) {
                // Glassmorphism Card for App Description
                GlassmorphismCard(
                    content = {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            // App Description
                            Text(
                                text = "PlantCare AI",
                                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 8.dp),
                                color = Color.White
                            )

                            Text(
                                text = "Turn your thumb into a green thumb! Our app uses cutting-edge technology to diagnose plant diseases and recommend the perfect growing conditions for a thriving garden. Simply snap a picture, and we'll be your pocket plant doctor, providing solutions and guidance for happy, healthy flora. Download now and watch your plants flourish!",
                                style = TextStyle(fontSize = 16.sp),
                                modifier = Modifier.padding(bottom = 8.dp),
                                color = Color.White
                            )
                        }
                    },
                    modifier = Modifier.padding(bottom = 32.dp),
                    elevation = 8.dp // Adjust elevation as needed
                )

            }
        }
    }
}

@Composable
fun CreatorItem(name: String, role: String, avatarResId: Int, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Image(
            painter = painterResource(id = avatarResId),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(text = name, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            Text(text = role, style = TextStyle(fontSize = 14.sp))
        }
    }
}

@Composable
fun GlassmorphismCard(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    backgroundColor: Color = Color.White.copy(alpha = 0.5f),
    contentColor: Color = Color.Black
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        content()
    }
}





