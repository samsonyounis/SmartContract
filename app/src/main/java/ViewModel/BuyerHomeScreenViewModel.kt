package ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import model.CategoryList
import model.ProductList
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// view Model for BuyerHomeScreen
class BuyerHomeScreenViewModel(private val repository: Repository): ViewModel() {
    // live data variables to be observed from the view
    var allCategories: MutableLiveData<List<CategoryList>> = MutableLiveData()
    var allProducts: MutableLiveData<List<ProductList>> = MutableLiveData()
    // function to get category list
    fun getCategoryList(){
        // Accessing the getProductsList function from the repository
        repository.getCategoryList().enqueue(object : Callback<List<CategoryList>> {
            // this method will be executed if you have recieved Http response
            // Your status code will decide if the Http response is success or error
            override fun onResponse(
                call: Call<List<CategoryList>>,
                response: Response<List<CategoryList>>
            ) {
                //here your reponse  code is in the range of 200 to 299
                // more else block can be used here to determine different actions
                // for different codes in this range(200 - 299)
                if (response.isSuccessful) {
                    // create a variable to hold the list of product categories
                    allCategories.value = response.body()!!
                    //productsAvailable = true
                    // Display the categoryList in lazyRow using for loop
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    allCategories.value = listOf()
                    //productsAvailable = false
                }
            }

            override fun onFailure(call: Call<List<CategoryList>>, t: Throwable) {
                allCategories.value = listOf()
                //productsAvailable = false
            }
        })
    }
    //Function to fetch list of products
    fun getProductList() {
        //viewModelScope.launch {
        // Accessing the getProductsList function from the repository
        repository.getProductList().enqueue(object : Callback<List<ProductList>> {
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
                    allProducts.value = response.body()!!
                    //productsAvailable = true
                    // Display the categoryList in lazyRow using for loop
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    allProducts.value = listOf()
                    //productsAvailable = false
                }
            }
            override fun onFailure(call: Call<List<ProductList>>, t: Throwable) {
                allProducts.value = listOf()
                //productsAvailable = false
            }
        })
    }
}

// ViewModeFactory to return BuyerScreenViewModel
class BuyerHomeScreenViewModelFactory(private val repository:Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BuyerHomeScreenViewModel(repository) as T
    }
}