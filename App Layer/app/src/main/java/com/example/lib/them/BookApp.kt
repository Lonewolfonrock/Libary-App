package com.example.lib.them

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



