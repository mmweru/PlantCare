package com.example.plantcareai

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.plantcareai.dashboard.YourScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AboutScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)
        composeTestRule.setContent {
            YourScreen(navController = navController)
        }
    }

    @Test
    fun testYourScreen() {
        // Test that the "Light" button is displayed
        composeTestRule.onNodeWithText("Light").assertIsDisplayed()

        // Test that the "Temp." button is displayed
        composeTestRule.onNodeWithText("Temp.").assertIsDisplayed()

        // Test that the "Humidity" button is displayed
        composeTestRule.onNodeWithText("Humidity").assertIsDisplayed()

        // Test that the "About PlantCare AI" text is displayed
        composeTestRule.onNodeWithText("About PlantCare AI").assertIsDisplayed()

        // Test that the about us text is displayed
        composeTestRule.onNodeWithText("Ever worried your plant is whispering secrets of a mysterious illness? PlantCare AI is here to be your green thumb's best friend! Simply snap a picture and our intelligent assistant will diagnose potential problems and suggest solutions, all within seconds. Watch your favorite flora flourish with the power of AI in the palm of your hand!").assertIsDisplayed()
    }
}

