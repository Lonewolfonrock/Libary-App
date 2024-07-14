package com.example.lib.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BookDetails(bookID: Int, booksViewModel: booksViewModel) {

    val bookData by booksViewModel.bookData.observeAsState()
    LaunchedEffect(bookID) {
        booksViewModel.featchBookByID(bookID)
    }

    Column(
        modifier = Modifier
            .padding(top = 70.dp)
            .fillMaxSize()
    ) {
        Row {


            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 10.dp)
            ) {


                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text ="#${bookData?.bookID} on Trending",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),


                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = bookData?.bookName ?: "",
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = bookData?.bookAuthor ?: "",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(Modifier.padding(start = 0.dp)) {
                    Text(
                        text = "category: ${bookData?.catagory}" ?: "",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color(116,91,212)

                    )
                }



            }

            Column(
                modifier = Modifier
                    .weight(0.5f)

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                    .data(bookData?.bigThumbnail)
                    .build(),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(300.dp),
                    contentDescription = bookData?.bookName?:"some thing problem loading")
            }
        }
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 10.dp, top = 20.dp)
        )

        Text(
            text = "${bookData?.bookDescription}" ?: "",
            style = MaterialTheme.typography.bodyMedium,
             modifier = Modifier.padding(10.dp)
        )

    }
}




