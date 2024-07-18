package com.example.lib.screen

import BooksViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@Composable
fun SearchScreen(
    bookName: String,
    booksViewModel: BooksViewModel,
    navController: NavController
) {
    val bookData by booksViewModel.bookData.observeAsState()
    LaunchedEffect(bookName) {
        booksViewModel.searchBooks(bookName)
    }
    when (val bookUiState = booksViewModel.bookUiState) {
        is BookUiState.Loading -> {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }

        is BookUiState.Success -> {
            BookData(
                data = bookUiState.Data,
                booksViewModel = booksViewModel,
                navController = navController,
                navigateToDetails = { book ->
                    navController.navigate("bookDetails/${book.bookID}")
                }
            )
        }

        is BookUiState.Error -> {
            Text(text = "Error loading search results.")
        }

        else -> {}
    }
}

