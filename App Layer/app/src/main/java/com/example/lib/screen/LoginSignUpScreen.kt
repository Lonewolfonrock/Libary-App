package com.example.lib.screen

import BooksViewModel
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
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

    LaunchedEffect(loginResult) {
        loginResult?.let {
            if (it.isSuccess) {
                navController.navigate("home")
            } else {
                // Handle login failure (e.g., show a toast or error message)
            }
        }
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        
        Spacer(modifier = modifier.height(120.dp))
        
        CustomTextField(
            FieldData = email,
            onValueChange = { email = it },
            labelField = "Email address",
            placeholder = "Enter your e-mail",
            leadingIcon = Icons.Default.Email,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomTextField(
            FieldData = password,
            onValueChange = { password = it },
            labelField = "Password",
            placeholder = "Enter your password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.padding(bottom = 16.dp)

        )

        Button(
            onClick = {
                booksViewModel.login(email.text, password.text)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Login")
        }

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
