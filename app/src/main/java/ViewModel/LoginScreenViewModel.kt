package ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import model.LoginRequest
import model.LoginResponse
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// viewModel for Login Screen/view
class LoginScreenViewModel(private val repository: Repository): ViewModel() {
    var loginResponse: MutableLiveData<String> = MutableLiveData()
    var token: MutableLiveData<String> = MutableLiveData()
    // function to login the user
    fun loginRequest(loginRequest: LoginRequest){
        // Accessing the loginRequest function from the repository
        repository.loginRequest(loginRequest).enqueue(object :
            Callback<LoginResponse> {
            // this method will be executed if you have recieved Http response
            // Your status code will decide if the Http response is success or error
            override fun onResponse(
                call: Call<LoginResponse>, response: Response<LoginResponse>
            ) {
                //here your reponse  code is in the range of 200 to 299
                // more else block can be used here to determine different actions
                // for different codes in this range(200 - 299)
                if (response.isSuccessful) {
                    if (response.body()!!.status == "success") {
                        // setting loginResponse variable to success string
                        loginResponse.value = response.body()!!.status
                        token.value = response.body()!!.token
                    }
                    // here user login credentials provided are incorrect
                    else {
                        // setting loginResponse variable to this message
                        loginResponse.value = response.body()!!.status
                    }
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    // setting loginResponse variable to error code
                    loginResponse.value = response.code().toString()

                }
            }
            // Invoked when a network exception/error occurred or
            // establishing connection with the server.
            // unexpected exception occurred creating the request or processing the response.
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // setting loginResponse variable to the error message in the t object
                loginResponse.value = t.message
            }
        })

    }
}

// returns the LoginScreenViewModel
class LoginScreenViewModelFactory(private val repository:Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginScreenViewModel(repository) as T
    }
}