package com.example.plantcareai

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.plantcareai.authentication.EmailInputBox
import com.example.plantcareai.authentication.LogIn
import com.example.plantcareai.authentication.RememberMeCheckbox
import org.junit.Rule
import org.junit.Test

class LogInScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRememberMeCheckbox_initialState() {
        composeTestRule.setContent {
            RememberMeCheckbox(onCheckedChange = {}, onForgotPasswordClick = {})
        }

    }

    @Test
    fun testRememberMeCheckbox_toggleState() {
        composeTestRule.setContent {
            RememberMeCheckbox(onCheckedChange = {}, onForgotPasswordClick = {})
        }

        // Click the checkbox to toggle its state
        val checkbox = composeTestRule.onNodeWithTag("Check")
        checkbox.performClick()


        // Click the checkbox again to toggle its state
        checkbox.performClick()

    }

    @Test
    fun testLogInScreen() {
//        composeTestRule.setContent {
//            LogIn()
//        }

        // Check if "Login in to your account" text is displayed
        composeTestRule.onNodeWithText("Login in to your account")
            .assertIsDisplayed()

        // Check if "Email or Phone number" input field is displayed
        composeTestRule.onNodeWithText("Email or Phone number")
            .assertIsDisplayed()

        // Check if "Password" input field is displayed
        composeTestRule.onNodeWithText("Password")
            .assertIsDisplayed()

        // Check if "Login" button is displayed
        composeTestRule.onNodeWithText("Login")
            .assertIsDisplayed()

        // Check if "Log in with Google" button is displayed
        composeTestRule.onNodeWithText("Log in with Google")
            .assertIsDisplayed()

        // Check if "Don't have an account?" text is displayed
        composeTestRule.onNodeWithText("Don't have an account?")
            .assertIsDisplayed()

        // Check if "Signup" text button is displayed
        composeTestRule.onNodeWithText("Signup")
            .assertIsDisplayed()
    }
}



