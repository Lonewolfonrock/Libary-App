package com.example.lib.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lib.R
import com.example.lib.network.bookData
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card


@Composable
fun HomeScreen(
    bookUiState: BookUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
){
    when(bookUiState){
        is BookUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is BookUiState.Success -> BookData(bookUiState.Data,modifier)
        else -> ErrorScreen(
            retryAction,
            modifier = modifier
                .padding(contentPadding)
                .fillMaxSize()
        )
    }

}

@Composable
fun BookData(
    data: List<bookData>,
    modifier: Modifier = Modifier
        .padding(0.dp)
        .fillMaxSize(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
){

    LazyColumn(
        contentPadding = contentPadding,
        modifier = Modifier.padding(5.dp)
    ) {

        items(items = data, key = {it.bookID}){
            book -> BookCard(data = book, modifier = Modifier)

        }


    }
}

@Composable
fun BookCard(data: bookData, modifier: Modifier) {
    Log.d("BookCard", "Displaying book with ID: ${data.bookID}, Name: ${data.bookName}")
    Text(text = "Book Id: ${data.bookID}")
}



@Composable
fun LoadingScreen(modifier: Modifier){

    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "Loading",
        modifier = modifier.size(200.dp)
    )
}

@Composable
fun ErrorScreen(retryAction:()-> Unit, modifier: Modifier=Modifier){
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(R.drawable.ic_connection_error),
                contentDescription ="Error",
                modifier = modifier.size(200.dp)
        )
        Text(
            text = "Failed to load",
            modifier=Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(text = "Retry")
        }



    }

}