package ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repository.Repository
import retrofit2.Call

// viewModel for the forgot password screen/view
class ForgotPasswordActivityViewModel(private val repository: Repository): ViewModel() {
    //Function to get the password from server
    fun requestPassword(email: String): Call<String> {
        return repository.requestPassword(email)
    }
}

// ViewModelFactory to return the forgotPasswordViewModel
class ForgotPasswordActivityViewModelFactory(private val repository:Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForgotPasswordActivityViewModel(repository) as T
    }
}