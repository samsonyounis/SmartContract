package ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import model.*
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// view model for productDetailScreen
class ProductDetailScreenViewModel(private val repository: Repository):ViewModel(){
    var isAddToCartSuccess:MutableLiveData<Boolean> = MutableLiveData()
    // function to add item to cart
    fun addToCart(userToken:String, item:AddToCartRequest){
        repository.addToCart(userToken, item).enqueue(object: Callback<AddToCartResponse>{
            override fun onResponse(
                call: Call<AddToCartResponse>,
                response: Response<AddToCartResponse>
            ) {
                //here your reponse  code is in the range of 200 to 299
                // more else block can be used here to determine different actions
                // for different codes in this range(200 - 299)
                if (response.isSuccessful) {
                    if (response.body()!!.success == true) {
                        // setting isAddToCart variable to true
                        isAddToCartSuccess.value = true
                    }
                    else {
                        // setting isAddtoCartsuccess to false
                        isAddToCartSuccess.value = false
                    }
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    // setting the isAddtoCartsuccess to false
                    isAddToCartSuccess.value = false
                }
            }

            override fun onFailure(call: Call<AddToCartResponse>, t: Throwable) {
                isAddToCartSuccess.value = false
            }

        } )
    }
}

//viewModelFactory to return the productDetailScreen View Model
class ProductDetailScreenViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
    override fun <T:ViewModel?> create(modelClass: Class<T>):T{
        return ProductDetailScreenViewModel(repository) as T
    }
}