package ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//viewModel for the OTP view/screen
class OtpScreenViewModel(private val repository: Repository): ViewModel(){
    var otpResponse: MutableLiveData<String> = MutableLiveData()
    // function to send back the otp to the server and return the response
    fun  sendBackOtp(otp:String){
        repository.sendBackOtp(otp).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    otpResponse.value = "authenticated"
                }
                else{
                    otpResponse.value = response.errorBody().toString()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                otpResponse.value = t.message.toString()
            }

        })
    }
}

//viewModelFactory to return the OtpScreen View Model
class OtpScreenViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
    override fun <T:ViewModel?> create(modelClass: Class<T>):T{
        return OtpScreenViewModel(repository) as T
    }
}