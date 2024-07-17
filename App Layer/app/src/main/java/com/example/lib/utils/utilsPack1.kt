package com.example.lib.utils

import BookUiState
import BooksViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OutLineTextFieldSample(text:String, onTextChange: (String) -> Unit)  {
    OutlinedTextField(
        value = text,
        label = { Text(text = "Enter Your Books") },
        onValueChange = onTextChange,
        modifier = Modifier.height(60.dp)
    )
}
@Composable
fun SearchIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun BackButton(onClick:() -> Unit){

        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(30.dp)


        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Search",
                modifier = Modifier.size(30.dp),
                )
        }

    }


@Composable
fun SearchBarWithButton(
    booksViewModel: BooksViewModel,
) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center

    ) {
        OutLineTextFieldSample(
            text = text,
            onTextChange = { text = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        SearchIconButton(onClick = {
            booksViewModel.searchBooks(text)


        })
    }
}
