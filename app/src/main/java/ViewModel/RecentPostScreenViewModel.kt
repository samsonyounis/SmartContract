package ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import model.ProductList
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// view Model for RecentPostScreen
class RecentPostScreenViewModel(private val repository: Repository): ViewModel() {
    // live data variables to be observed from the view
    var allRecentProducts: MutableLiveData<List<ProductList>> = MutableLiveData()

    //Function to fetch list of all recently posted products
    fun getRecentPostedItems() {
        //viewModelScope.launch {
        // Accessing the getRecentPostedItems function from the repository
        repository.getRecentPostedItems().enqueue(object : Callback<List<ProductList>> {
            // this method will be executed if you have recieved Http response
            // Your status code will decide if the Http response is success or error
            override fun onResponse(
                call: Call<List<ProductList>>,
                response: Response<List<ProductList>>
            ) { //here your reponse  code is in the range of 200 to 299
                // more else block can be used here to determine different actions
                // for different codes in this range(200 - 299)
                if (response.isSuccessful) {
                    // create a variable to hold the list of product categories
                    allRecentProducts.value = response.body()!!
                    //productsAvailable = true
                    // Display the categoryList in lazyRow using for loop
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    allRecentProducts.value = listOf()
                }
            }
            override fun onFailure(call: Call<List<ProductList>>, t: Throwable) {
                allRecentProducts.value = listOf()
            }
        })
    }
}

// ViewModeFactory to return RecentPostScreenViewModel
class RecentPostScreenViewModelFactory(private val repository: Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecentPostScreenViewModel(repository) as T
    }
}