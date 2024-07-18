package com.example.lib.them

import BooksViewModel
import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lib.screen.BookDetails
import com.example.lib.screen.HomeScreen
import com.example.lib.screen.SearchScreen
import com.example.lib.utils.BackButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun bookApp(navController: NavHostController){

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val bookViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
                        BookAppBar(scrollBehavior = scrollBehavior, navController = navController)
        }
    ){paddingValues ->
        val bookViewModel:BooksViewModel = viewModel(factory = BooksViewModel.Factory)
        Column(modifier = Modifier.padding(top = 20.dp)) {
            NavHost(navController = navController, startDestination = "home") {
                composable("home"){
                    HomeScreen(
                        bookUiState = bookViewModel.bookUiState,
                        retryAction = { bookViewModel.getBookData() },
                        contentPadding = paddingValues,
                        navController = navController,
                        viewModel = bookViewModel

                    )
                }
                composable("bookDetails/{bookID}"){navBackStackEntry ->
                    val bookID = navBackStackEntry.arguments?.getString("bookID")?.toIntOrNull() ?: 0
                    val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
                    BookDetails(bookID = bookID, booksViewModel = booksViewModel)
                }
                //under developement
                composable("searchScreen/{bookName}"){navBackStackEntry ->
                    val bookName = navBackStackEntry.arguments?.getString("bookName")?:""
                    val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
                    SearchScreen(
                        bookName=bookName,
                        booksViewModel = booksViewModel,
                        navController = navController
                    )

                }



            }
        }
    }
        }

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BookAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier,navController: NavHostController ){
    val context = LocalContext.current

    val bookViewModel:BooksViewModel = viewModel(factory = BooksViewModel.Factory)

    CenterAlignedTopAppBar(

        scrollBehavior = scrollBehavior,
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackButton(onClick = {
                        if (! navController.popBackStack()){
                            (context as? Activity)?.finish()
                        }
                    })
                }
                Text(
                    text = "Book App",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },

        modifier = modifier
    )
}



