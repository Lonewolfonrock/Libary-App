package com.example.lib.screen

import BooksViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
){
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val  loginResponse by booksViewModel.loginResponse.observeAsState()

    Column(modifier = modifier.padding(16.dp)) {
        CustomTextField(
            FieldData = username,
            onValueChange = {username = it},
            labelField = "Email address",
            placeholder ="Enter your e-mail",
            leadingIcon =Icons.Default.Email
        )

        CustomTextField(
            FieldData = password,
            onValueChange = {password = it},
            labelField = "Password",
            placeholder ="Enter your password",
            leadingIcon = Icons.Default.Lock
        )

        Button(onClick = { booksViewModel.login(username.text, password.text)}) {
            Text(text ="Login")
        }

        loginResponse?.let { response ->
            if (response.sucess){
                LaunchedEffect(Unit){
                    navController.navigate("home")
                }
            }
            else{
                Text("Login failed. Please try again", color = androidx.compose.ui.graphics.Color.Red)
            }

        }




    }

}

@Composable
fun CustomTextField(
    FieldData:TextFieldValue,
    onValueChange:(TextFieldValue)->Unit,
    labelField:String,
    placeholder:String,
    leadingIcon:ImageVector
) {
    return OutlinedTextField(
        value = FieldData,
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = labelField) },
        //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        onValueChange = onValueChange,
        label = { Text(text = labelField) },
        placeholder = { Text(text = placeholder) },
        modifier = Modifier.padding(bottom = 16.dp)
    )
}