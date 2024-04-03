package com.example.plantcareai

import android.annotation.SuppressLint
import com.example.plantcareai.authentication.PreviewShowScreen
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantcareai.authentication.AnimatedSplash
import com.example.plantcareai.authentication.LogIn
import com.example.plantcareai.authentication.SignUp
import com.example.plantcareai.firebaseauth.AuthRepositoryImpl
import com.example.plantcareai.firebaseauth.GoogleAuthUiClient
import com.example.plantcareai.firebaseauth.SignInViewModel
import com.example.plantcareai.profile.ProfileScreen
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @SuppressLint("ComposableDestinationInComposeScope", "SuspiciousIndentation")
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
                composable("SignUp"){
                    val viewModel: SignInViewModel by viewModels()

                    val state by viewModel.state.collectAsStateWithLifecycle()
                    LaunchedEffect(key1 = Unit){
//                        if(googleAuthUiClient.getSignedInUser() != null){
//                            navController.navigate("camera")
//                        }
                    }


                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(), onResult = {
                                result ->
                            if(result.resultCode == RESULT_OK){
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )
                    SignUp(
                        state = state,
                        onSignInClick = {
                            lifecycleScope.launch {
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )

                            }
                        },
                        navController = navController
                    )
                }
                composable(route = "Login") {
                    val viewModel: SignInViewModel by viewModels()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    LaunchedEffect(key1 = Unit) {
//                        if (googleAuthUiClient.getSignedInUser() != null) {
//                            navController.navigate("camera")
//                        }
                    }

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )

                    LogIn(
                        state = state,
                        onSignInClick = {
                            lifecycleScope.launch {
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        },
                        navController = navController
                    )
                }



                }

            }

        }
}
