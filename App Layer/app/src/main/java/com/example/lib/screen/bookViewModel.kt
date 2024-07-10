package com.example.lib.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lib.data.BookRepo
import com.example.lib.network.bookData
import com.example.lib.them.BookDataApplication
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class booksViewModel(private val bookRepo: BookRepo): ViewModel() {

    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    init {
        getBookData()
    }


    fun getBookData() {
        viewModelScope.launch {
            bookUiState = try {
                BookUiState.Success(bookRepo.getBookdata())
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Loading
            }
        }

    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as? BookDataApplication
                    ?: throw IllegalStateException("Expected BookData Application")

                val bookRepo = application.container.bookDataRepo
                booksViewModel(bookRepo = bookRepo)
            }

        }

    }
}

    sealed interface BookUiState {
        data class Success(val Data: List<bookData>) : BookUiState
        object Error : BookUiState
        object Loading : BookUiState
    }

