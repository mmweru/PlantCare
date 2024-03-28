package com.example.plantcareai.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantcareai.R

@Preview
@Composable
fun SignUp() {
    Column {
        Text(
            text = "Create your account",
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(top = 60.dp, start = 110.dp)
        )
        Spacer(modifier = Modifier.height(13.dp))
        NameInputScreen()
        Spacer(modifier = Modifier.height(27.dp))
        Email1InputScreen()
        Spacer(modifier = Modifier.height(27.dp))
        PreviewPassword1TextField()
        Spacer(modifier = Modifier.height(30.dp))
        RegisterButton()
        Spacer(modifier = Modifier.height(36.dp))
        Google1LogIn()
        Spacer(modifier = Modifier.height(27.dp))
        SignInPrompt()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameInputBox(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = { onNameChange(it) },
        label = { Text("Full name", color = Color.Black) },
        textStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Person Icon",
                tint = Color(0xFF5DB075)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth(0.9f)
    )
}

@Composable
fun NameInputScreen() {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NameInputBox(name = name, onNameChange = { name = it })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email1InputBox(email: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        label = { Text("Email Address", color = Color.Black) },
        textStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = "Email Icon",
                tint = Color(0xFF5DB075)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth(0.9f)
    )
}

@Composable
fun Email1InputScreen() {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Email1InputBox(email = email, onEmailChange = { email = it })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password1TextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String, // Add confirmPassword parameter
    onConfirmPasswordChange: (String) -> Unit // Add confirmPassword change handler
) {
    var showError by remember { mutableStateOf(false) } // Track error visibility

    OutlinedTextField(
        value = password,
        onValueChange = { newPassword ->
            onPasswordChange(newPassword)
        },
        label = { Text("Password", color = Color.Black) },
        textStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
        visualTransformation = PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Password Icon",
                tint = Color(0xFF5DB075)
            )
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth(0.9f),
        isError = showError // Set error state for password field
    )

    if (showError) {
        Text(text = "Passwords don't match!", color = Color.Red)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasswordTextField(
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    password: String // Reference password from Password1TextField
) {
    var showError by remember { mutableStateOf(false) } // Track confirm password error

    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { newConfirmPassword ->
            onConfirmPasswordChange(newConfirmPassword)
            showError = password != newConfirmPassword // Update error state
        },
        label = { Text("Confirm Password", color = Color.Black) },
        textStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
        visualTransformation = PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Password Icon",
                tint = Color(0xFF5DB075)
            )
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth(0.9f),
        isError = showError // Set error state for confirm password field
    )

    if (showError) { // Display confirm password error conditionally
        Text(
            text = "Passwords don't match!",
            color = Color.Red,
        )
    }
}

@Composable
fun PreviewPassword1TextField() {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") } // Add confirm password state

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Password1TextField(
            password = password,
            onPasswordChange = { password = it },
            confirmPassword = confirmPassword, // Pass confirmPassword state
            onConfirmPasswordChange = {
                confirmPassword = it
            } // Update confirmPassword state on change
        )
        Spacer(modifier = Modifier.height(27.dp)) // Add spacing between fields
        ConfirmPasswordTextField(
            confirmPassword = confirmPassword,
            onConfirmPasswordChange = { confirmPassword = it },
            password = password // Pass password state for comparison
        )
    }
}


@Composable
fun RegisterButton(
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Button(
            onClick = { /**do this**/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5DB075)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(color = Color(0xFF5DB075), shape = RoundedCornerShape(size = 5.dp)),
//            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp, pressedElevation = 10.dp, focusedElevation = 0.dp, hoveredElevation = 4.dp, disabledElevation = 0.dp)
        ) {
            Text(text = "Register", color = Color.White, fontSize = 20.sp)

        }
        Spacer(modifier = Modifier.height(36.dp))
        Row {
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(136.29747.dp)
                    .height(1.dp)
                    .background(color = Color(0xFF000000))
            )
            Spacer(modifier = Modifier.width(15.7.dp))
            Text(
                text = "or",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                ), modifier = Modifier
                    .width(16.dp)
                    .height(18.dp)
            )
            Spacer(modifier = Modifier.width(15.7.dp))
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(136.29747.dp)
                    .height(1.dp)
                    .background(color = Color(0xFF000000))
            )
        }
    }

}


@Composable
fun Google1LogIn() {
    OutlinedButton(
        onClick = { /* Handle login with Google action */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        border = ButtonDefaults.outlinedButtonBorder,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Transparent,
            containerColor = Color.Transparent
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Log in with Google", color = Color(0xFF5DB075))
        }
    }
}

@Composable
fun SignInPrompt(
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(24.dp)
    ) {
        Text(
            text = "Already have an account? ",
            color = Color.Black,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = "Sign In",
            color = Color(0xFF5DB075),
            modifier = Modifier.clickable { /**do this**/ }
        )
    }
}
