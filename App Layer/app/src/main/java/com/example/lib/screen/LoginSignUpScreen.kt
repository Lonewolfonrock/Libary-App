package com.example.lib.screen

import BooksViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginSignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    booksViewModel: BooksViewModel
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val loginResult by booksViewModel.loginResult.observeAsState()
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(loginResult) {
        loginResult?.let {
            if (it.isSuccess) {
                navController.navigate("home")
            }
            else{
                showError=true
            }
        }
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Spacer(modifier = modifier.height(60.dp))

        Text(
            text = "Login your Account",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.W500),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(25.dp),
            color = Color(0, 0, 128)
        )

        CustomTextField(
            FieldData = email,
            onValueChange = { email = it },
            labelField = "Email address",
            placeholder = "Enter your e-mail",
            leadingIcon = Icons.Default.Email,
            modifier = Modifier.padding(bottom = 18.dp)
        )

        CustomTextField(
            FieldData = password,
            onValueChange = { password = it },
            labelField = "Password",
            placeholder = "Enter your password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.padding(bottom = 18.dp)

        )

        Button(
            onClick = {
                booksViewModel.login(email.text, password.text)
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Login")
        }


        AnimatedVisibility(
                visible = showError,
                enter = fadeIn(animationSpec = tween(durationMillis = 400)),
                exit = fadeOut(animationSpec = tween(durationMillis = 400))
            ) {
                Text(
                    text = "Login failed. Please try again.",
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp, start = 15.dp)
                )
            }


        ClickableText()
        
        
        
        


        }


    }


@Composable
fun CustomTextField(
    FieldData: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    labelField: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = FieldData,
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = labelField) },
        onValueChange = onValueChange,
        label = { Text(text = labelField) },
        placeholder = { Text(text = placeholder) },
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun ClickableText(){
    var click by remember { mutableStateOf(false) }

    Text(
        text = "Don,t have account !! Click me",
        color = Color(0, 0, 100),
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, top = 25.dp)
            .clickable {
                click = true
            }
    )

    if (click ==true){


    }
    
    
}
