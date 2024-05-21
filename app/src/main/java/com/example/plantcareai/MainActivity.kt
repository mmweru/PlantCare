package com.example.plantcareai

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.net.Uri
import com.example.plantcareai.authentication.PreviewShowScreen
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.plantcareai.AiCamera.Camera
import com.example.plantcareai.AiCamera.History
import com.example.plantcareai.authentication.AnimatedMarket
import com.example.plantcareai.authentication.AnimatedSplash
import com.example.plantcareai.authentication.LogIn
import com.example.plantcareai.authentication.SignUp
import com.example.plantcareai.authentication.TripleImageScreen
import com.example.plantcareai.dashboard.ApplePlant
import com.example.plantcareai.dashboard.BlueBerryPlant
import com.example.plantcareai.dashboard.ChatUiEvent
import com.example.plantcareai.dashboard.ChatViewModel
import com.example.plantcareai.dashboard.CherryPlant
import com.example.plantcareai.dashboard.GrapePlant
import com.example.plantcareai.dashboard.MaizePlant
import com.example.plantcareai.dashboard.ModelChatItem
import com.example.plantcareai.dashboard.PeachPlant
import com.example.plantcareai.dashboard.PlantSearchPage
import com.example.plantcareai.dashboard.UserChatItem
import com.example.plantcareai.dashboard.YourScreen
import com.example.plantcareai.firebaseauth.AuthRepositoryImpl
import com.example.plantcareai.firebaseauth.GoogleAuthUiClient
import com.example.plantcareai.firebaseauth.SignInViewModel
import com.example.plantcareai.profile.ProfileScreen
import com.example.plantcareai.ui.theme.PlantCareAITheme
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

     val uriState = MutableStateFlow("")
     val imagePicker =
        registerForActivityResult<PickVisualMediaRequest, Uri>(
            ActivityResultContracts.PickVisualMedia()
        ){ uri ->
            uri?.let {
                uriState.update { uri.toString() }

            }
        }
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("ComposableDestinationInComposeScope", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantCareAITheme {
                //Implementation of the navigation graph
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash_screen") {
                    composable("splash_screen") {
                        AnimatedSplash(navController = navController)

                    }
                    composable("welcome") {
                        PreviewShowScreen(navController = navController)
                    }
                    composable("SignUp") {
                        val viewModel: SignInViewModel by viewModels()

                        val state by viewModel.state.collectAsStateWithLifecycle()
                        LaunchedEffect(key1 = Unit) {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate("Login")
                            }
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
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate("home")
                            }
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
                    composable("home") {
                        PlantSearchPage(navController = navController)
                    }
                    composable("camera") {
                        if (!hasRequiredPermissions()) {
                            ActivityCompat.requestPermissions(
                                this@MainActivity, CAMERAX_PERMISSIONS, 0
                            )
                        }
                        Camera(navController = navController)
                    }

                    composable("apple") {
                        ApplePlant(navController = navController)
                    }
                    composable("cherry") {
                        CherryPlant(navController = navController)
                    }
                    composable("blueberry") {
                        BlueBerryPlant(navController = navController)
                    }
                    composable("grape") {
                        GrapePlant(navController = navController)
                    }
                    composable("maize") {
                        MaizePlant(navController = navController)
                    }
                    composable("peach") {
                        PeachPlant(navController = navController)
                    }
                    composable("history") {
                        History(navController = navController)
                    }
                    composable("about") {
                        YourScreen(navController = navController)
                    }
                    composable("market") {
                        AnimatedMarket(navController = navController)
                    }
                    composable("shop") {
                        TripleImageScreen(navController = navController)
                    }
                    composable("bot") {
                        MyChat(navController = navController)
                    }
                }
            }

                }


        }
    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA
        )
    }
    @Composable
    fun MyChat(navController: NavHostController) {

        val myfont = FontFamily(Font(R.font.happy_monkey))
        val backgroundImage = painterResource(id = R.drawable.oxalis)

        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF0D6446))
                        .height(55.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    IconButton(onClick = { navController.navigate("home")}) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(40.dp))

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(horizontal = 100.dp),
                        text = "Plancare Bot",
                        fontSize = 19.sp,
                        color = Color.White,
                        fontFamily = myfont,
                        fontWeight = FontWeight(200)
                    )
                }
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = backgroundImage,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.65f)
                )

                ChatScreen(paddingValues = it)
            }
        }
    }
    @Composable
    fun ChatScreen(paddingValues: PaddingValues){
        val chatViewModel = viewModel<ChatViewModel>()
        val chatState = chatViewModel.chatState.collectAsState().value
        val bitmap = getBitmap()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Bottom
        ) {

            LazyColumn (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                reverseLayout = true
            ){
                itemsIndexed(chatState.chatList) {index, chat ->
                    if (chat.isFromUser){
                        UserChatItem(
                            prompt = chat.prompt,
                            bitmap = chat.bitmap
                        )
                    } else {
                        ModelChatItem(response = chat.prompt)
                    }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column {
                    bitmap?.let {
                        Image(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(bottom = 2.dp)
                                .clip(RoundedCornerShape(6.dp)),
                            contentDescription = "picked image",
                            contentScale = ContentScale.Crop,
                            bitmap = it.asImageBitmap()
                        )
                    }
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                imagePicker.launch(
                                    PickVisualMediaRequest
                                        .Builder()
                                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        .build()
                                )
                            },
                        imageVector = Icons.Outlined.AddPhotoAlternate,
                        contentDescription = "Add Photo",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    modifier = Modifier
                        .weight(1f),
                    value = chatState.prompt,
                    onValueChange = {
                        chatViewModel.onEvent(ChatUiEvent.UpdatePrompt(it))
                    },
                    placeholder = {
                        Text(text = "Type a prompt")
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            chatViewModel.onEvent(ChatUiEvent.SendPrompt(chatState.prompt, bitmap))
                            uriState.update { "" }
                        },
                    imageVector = Icons.Outlined.Send,
                    contentDescription = "Send Button",
                    tint = Color.White
                )
            }
        }
    }

    @Composable
    fun getBitmap(): Bitmap? {
        val uri = uriState.collectAsState().value

        val imageState: AsyncImagePainter.State = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(uri)
                .size(Size.ORIGINAL)
                .build()
        ).state

        if (imageState is AsyncImagePainter.State.Success) {
            return imageState.result.drawable.toBitmap()
        }
        return null
    }


}
