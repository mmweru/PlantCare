import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.plantcareai.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MyApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = Color.Black,
            onPrimary = Color.Blue,
            background = Color.Black,
            onBackground = Color.Magenta
        ),
        typography = typography,
        content = content
    )
}

@Composable
fun TypingText(text: String, modifier: Modifier = Modifier) {
    var typedText by remember { mutableStateOf("") }
    var isTyping by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        text.forEachIndexed { index, _ ->
            launch {
                delay((index + 1) * 100L)
                typedText = text.take(index + 1)
                if (index == text.length - 1) {
                    isTyping = false
                }
            }
        }
    }
    val my_font = FontFamily(Font(R.font.istok_bold_italic))

    Text(
        text = typedText,
        modifier = modifier,
        color = Color.White,
        fontSize = 26.sp,
        textAlign = TextAlign.Center,
        fontFamily = my_font
    )
}

@Composable
fun ShowScreen(alpha: Float) {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme(context)
    val images = listOf(
        R.drawable.page,
        R.drawable.page1,
        R.drawable.page2
    )
    var pageIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(4500L) // Switch between screens every 5 seconds
            pageIndex = (pageIndex + 1) % images.size
        }
    }

    val colors = if (isDarkTheme) {
        darkColors(
            primary = Color.Black,
            onPrimary = Color.White,
            background = Color.Black,
            onBackground = Color.White
        )
    } else {
        lightColors(
            primary = Color.White,
            onPrimary = Color.Black,
            background = Color.White,
            onBackground = Color.Black
        )
    }

    MaterialTheme(
        colors = colors,
        typography = typography
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = images[pageIndex]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (pageIndex) {
                    0 -> TypingText(text = "Want your farm produce this fresh?")
                    1 -> TypingText(text = "Always puzzled about the disease your crop is suffering from?")
                    2 -> TypingText(text = "Get Started with PlantCare AI\u2028For disease prediction and hence healthier crops...")
                }
            }
        }
    }
}

@Composable
fun isSystemInDarkTheme(context: android.content.Context) =
    context.resources.configuration.uiMode and
            android.content.res.Configuration.UI_MODE_NIGHT_MASK == android.content.res.Configuration.UI_MODE_NIGHT_YES

@Composable
fun PreviewShowScreen(navController: NavHostController){
    var startAnimation by remember{
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec= tween(
            durationMillis = 8500
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(10500)
//        navController.popBackStack()
//        navController.navigate("log_in")
    }
    MyApplicationTheme {
        ShowScreen(alpha = alphaAnim.value)

    }
}
