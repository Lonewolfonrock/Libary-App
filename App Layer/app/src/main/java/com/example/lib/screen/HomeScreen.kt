package com.example.lib.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest


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


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = contentPadding,
        modifier = Modifier.padding(5.dp)
    ) {

        items(items = data, key = {it.bookID}){
            book -> BookCard(
            data = book, modifier = Modifier.padding(4.dp)
            )

        }

    }
}
@Composable
fun BookCard(data: bookData, modifier: Modifier) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .widthIn(max = 200.dp)
            .heightIn(max = 270.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.bookName,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data.bigThumbnail)
                    .build(),
                contentDescription = "Image of book ${data.bookName}",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
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