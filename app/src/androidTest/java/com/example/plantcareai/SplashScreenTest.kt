package com.example.plantcareai

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.plantcareai.authentication.AnimatedSplash
import com.example.plantcareai.authentication.LogoText
import com.example.plantcareai.authentication.Lottie
import com.example.plantcareai.authentication.Splash
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Composable
    @Preview
    fun PreviewLottie() {
        Lottie()
    }

    @Test
    fun animatedSplashTest() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        composeTestRule.setContent {
            AnimatedSplash(navController)
        }

        // Check if the Box is present in the composition
        composeTestRule.onNodeWithContentDescription("Box")
            .assertExists()

        // Check if the com.example.plantcareai.authentication.LogoText is present in the composition
        composeTestRule.onNodeWithText("PLANTCARE AI")
            .assertExists()

        // Check if the com.example.plantcareai.authentication.Lottie animation is present in the composition
        composeTestRule.onNodeWithContentDescription("LottieAnimation")
            .assertExists()

        // Wait for the animation to complete
        composeTestRule.waitForIdle()
    }

    @Test
    fun splashBoxTest() {
        composeTestRule.setContent {
            Splash(alpha = 1f, modifier = Modifier)
        }

        // Check if the Box is present in the composition
        composeTestRule.onNodeWithContentDescription("Box")
            .assertExists()
    }

    @Test
    fun logoTextTest() {
        composeTestRule.setContent {
            LogoText()
        }

        // Check if the com.example.plantcareai.authentication.LogoText is present in the composition
        composeTestRule.onNodeWithText("PLANTCARE AI")
            .assertExists()
    }

    @Test
    fun lottieTest() {
        composeTestRule.setContent {
            Lottie()
        }

        // Check if the com.example.plantcareai.authentication.Lottie animation is present in the composition
        composeTestRule.onNodeWithContentDescription("LottieAnimation")
            .assertExists()
    }
}
