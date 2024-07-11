package com.example.lib.them

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lib.screen.HomeScreen
import com.example.lib.screen.booksViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun bookApp(){

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BookAppBar(scrollBehavior = scrollBehavior)
        }
    ){
        val bookViewModel:booksViewModel = viewModel(factory = booksViewModel.Factory)
        Column(modifier = Modifier.padding(top = 100.dp)) {
            println("step1")
            HomeScreen(bookUiState = bookViewModel.bookUiState,
                retryAction = bookViewModel::getBookData,
                contentPadding = it
                )

        }


    }
        }

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BookAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier ){
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Book App",
                style = MaterialTheme.typography.headlineSmall,

            )
        },
        modifier = modifier
    )
}



