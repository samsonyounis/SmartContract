package ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import model.CartItemX
import model.GetCartResponse
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//viewModel for the CartList Screen
class CartListScreenViewModel(private val repository: Repository): ViewModel(){
    var cartItemsList: MutableLiveData<List<CartItemX>> = MutableLiveData()
    //function to get the list of cart items
    fun getCartList(userToken: String){
        //viewModelScope.launch {
        // Accessing the getProductsList function from the repository
        repository.getCartList(userToken).enqueue(object : Callback<GetCartResponse> {
            // this method will be executed if you have recieved Http response
            // Your status code will decide if the Http response is success or error
            override fun onResponse(
                call: Call<GetCartResponse>,
                response: Response<GetCartResponse>
            ) { //here your reponse  code is in the range of 200 to 299
                // more else block can be used here to determine different actions
                // for different codes in this range(200 - 299)
                if (response.isSuccessful) {
                    // create a variable to hold the list of product categories
                    cartItemsList.value = response.body()!!.cartItems
                    //productsAvailable = true
                    // Display the categoryList in lazyRow using for loop
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    cartItemsList.value = listOf()
                    //productsAvailable = false
                }
            }
            override fun onFailure(call: Call<GetCartResponse>, t: Throwable) {
                cartItemsList.value = listOf()
                //productsAvailable = false
            }
        })
    }
}

//viewModelFactory to return the CartListViewModel
class CartListScreenViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
    override fun <T:ViewModel?> create(modelClass: Class<T>):T{
        return CartListScreenViewModel(repository) as T
    }
}