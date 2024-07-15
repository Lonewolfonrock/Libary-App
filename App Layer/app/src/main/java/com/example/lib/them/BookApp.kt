package com.example.lib.them

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lib.screen.BookDetails
import com.example.lib.screen.HomeScreen
import com.example.lib.screen.booksViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun bookApp(navController: NavHostController){

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {

                BookAppBar(scrollBehavior = scrollBehavior)


        }
    ){paddingValues ->
        val bookViewModel:booksViewModel = viewModel(factory = booksViewModel.Factory)
        Column(modifier = Modifier.padding(top = 20.dp)) {
            NavHost(navController = navController, startDestination = "home") {
                composable("home"){
                    HomeScreen(
                        bookUiState = bookViewModel.bookUiState,
                        retryAction = { bookViewModel.getBookData() },
                        contentPadding = paddingValues,
                        navController = navController
                    )
                }
                composable("bookDetails/{bookID}"){navBackStackEntry ->
                    val bookID = navBackStackEntry.arguments?.getString("bookID")?.toIntOrNull() ?: 0
                    val booksViewModel: booksViewModel = viewModel(factory = booksViewModel.Factory)
                    BookDetails(bookID = bookID, booksViewModel = booksViewModel)
                }
            }
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



