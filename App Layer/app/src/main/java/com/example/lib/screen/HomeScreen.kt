package com.example.lib.screen

import BookUiState
import BooksViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lib.utils.SearchBarWithButton


@Composable
fun HomeScreen(
    navController: NavController,
    bookUiState: BookUiState,
    retryAction: () -> Unit,
    viewModel: BooksViewModel,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    LaunchedEffect(Unit) {
        viewModel.resetBookUiState()
        retryAction()
    }

        when (bookUiState) {
            is BookUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is BookUiState.Success -> BookData(
                data = bookUiState.Data,
                modifier = modifier,
                contentPadding = contentPadding,
                booksViewModel = viewModel,
                navController = navController,
                navigateToDetails = { book ->
                    navController.navigate("bookDetails/${book.bookID}")
                },
            )
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
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    navigateToDetails: (bookData) -> Unit,
    booksViewModel: BooksViewModel,
    navController: NavController
){
    Column(modifier = modifier.padding(contentPadding)){
        SearchBarWithButton(
            booksViewModel = booksViewModel,
            navController = navController
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.padding(5.dp)
        ) {
            items(items = data, key = { it.bookID }) { book ->
                BookCard(
                    data = book,
                    modifier = Modifier.padding(4.dp),
                    navigateToDetails = navigateToDetails
                )
            }
        }
    }

    }


@Composable
fun BookCard(
    data: bookData,
    modifier: Modifier,
    navigateToDetails: (bookData) -> Unit
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .widthIn(min = 200.dp, max = 300.dp)
            .heightIn(max = 250.dp)
            .clickable { navigateToDetails(data) }
        ,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = MaterialTheme.shapes.medium,

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = data.bookName,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
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
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = "Error",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Failed to load Data",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = retryAction,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Retry")
        }
    }
}
