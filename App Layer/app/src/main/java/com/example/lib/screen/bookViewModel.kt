
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lib.data.BookRepo
import com.example.lib.network.LoginResponse
import com.example.lib.network.bookData
import com.example.lib.them.BookDataApplication
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class BooksViewModel(private val bookRepo: BookRepo) : ViewModel() {

    var bookUiState by mutableStateOf<BookUiState>(BookUiState.Loading)
        private set

    private val _bookData = MutableLiveData<bookData?>()

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> get() = _loginResponse


    val bookData: LiveData<bookData?> = _bookData

    init {
        getBookData()
    }

    fun getBookData() {
        viewModelScope.launch {
            bookUiState = try {
                BookUiState.Success(bookRepo.getBookdata())
            } catch (e: IOException) {
                Log.e("BooksViewModel", "IOException: ${e.message}")
                BookUiState.Error
            } catch (e: HttpException) {
                Log.e("BooksViewModel", "HttpException: ${e.message}")
                BookUiState.Error
            }
        }
    }

    fun fetchBookByID(bookID: Int) {
        viewModelScope.launch {
            try {
                val book = bookRepo.getBookByID(bookID)
                _bookData.value = book
            } catch (e: IOException) {
                Log.e("BooksViewModel", "IOException: ${e.message}")
                BookUiState.Error
            } catch (e: HttpException) {
                Log.e("BooksViewModel", "HttpException: ${e.message}")
                BookUiState.Error
            }
        }
    }

    fun searchBooks(bookName: String) {
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            delay(1000)
            bookUiState = try {
                BookUiState.Success(bookRepo.searchBooks(bookName))
            } catch (e: IOException) {
                Log.e("BooksViewModel", "IOException: ${e.message}")
                BookUiState.Error
            } catch (e: HttpException) {
                Log.e("BooksViewModel", "HttpException: ${e.message}")
                BookUiState.Error
            }
        }
    }
    fun login(username: String,password: String){
        viewModelScope.launch {
            val response: Response<LoginResponse> = bookRepo.login(username,password)
            if (response.isSuccessful) {
                _loginResponse.value = response.body()
            } else {
                _loginResponse.value = LoginResponse(sucess = false, token = "")
            }

        }
    }

    fun resetBookUiState(){
        bookUiState = BookUiState.Loading

    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as? BookDataApplication
                    ?: throw IllegalStateException("Expected BookDataApplication")

                val bookRepo = application.container.bookDataRepo
                BooksViewModel(bookRepo)
            }
        }
    }
}

sealed interface BookUiState {
    data class Success(val Data: List<bookData>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}
