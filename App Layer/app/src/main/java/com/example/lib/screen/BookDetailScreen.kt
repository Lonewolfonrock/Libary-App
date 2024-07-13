package com.example.lib.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BookDetails(bookID: Int, booksViewModel: booksViewModel){

    val bookData by booksViewModel.bookData.observeAsState()
    LaunchedEffect(bookID) {
        booksViewModel.featchBookByID(bookID)
    }

    Column(modifier = Modifier.padding(top=100.dp)) {
        Text(text = "Book Details for ${bookData?.bookName}")
    }


}