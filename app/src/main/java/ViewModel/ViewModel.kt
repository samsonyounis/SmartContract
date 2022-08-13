package ViewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.*
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// view Model for BuyerHomeScreen
class BuyerHomeScreenViewModel(private val repository: Repository): ViewModel() {
    // live data variables to be observed from the view
    var allCategories:MutableLiveData<List<CategoryList>> = MutableLiveData()
    var allProducts:MutableLiveData<List<ProductList>> = MutableLiveData()
    // function to get category list
    fun getCategoryList(){
        //viewModelScope.launch {
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

// viewModel for signUp activity/view
class SignUpScreenViewModel(private val repository:Repository):ViewModel() {
    var signUpResponse:MutableLiveData<String> = MutableLiveData()
    //function to add user account
    fun addUserAccount(user:User){
        // Accessing the addUserAccount function from the repository
        repository.addUserAccount(user).enqueue(object :
            Callback<SignUpResponse> {
            // this method will be executed if you have recieved Http response
            // Your status code will decide if the Http response is success or error
            override fun onResponse(
                call: Call<SignUpResponse>, response: Response<SignUpResponse>
            ) {
                //here your reponse  code is in the range of 200 to 299
                // more else block can be used here to determine different actions
                // for different codes in this range(200 - 299)
                if (response.isSuccessful) {
                    if (response.body()!!.status == "success") {
                        // setting signUpResponse variable to success string
                        signUpResponse.value = "success"
                    }
                    // here user login credentials provided are incorrect
                    else {
                        // setting loginResponse variable to this message
                        signUpResponse.value = "sign up failure, user already exists"
                    }
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    // setting loginResponse variable to error code
                signUpResponse.value = response.code().toString()

                }
            }
            // Invoked when a network exception/error occurred or
            // establishing connection with the server.
            // unexpected exception occurred creating the request or processing the response.
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                // setting loginResponse variable to the error message in the t object
                signUpResponse.value = t.message
            }
        })
    }
}

//viewModel for the OTP view/screen
class OtpScreenViewModel(private val repository: Repository):ViewModel(){
    var otpResponse:MutableLiveData<String> = MutableLiveData()
    // function to send back the otp to the server and return the response
    fun  sendBackOtp(otp:String){
        repository.sendBackOtp(otp).enqueue(object:Callback<String>{
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
// viewModel for Login Screen/view
class LoginScreenViewModel(private val repository:Repository):ViewModel() {
    var loginResponse:MutableLiveData<String> = MutableLiveData()
    var token:MutableLiveData<String> = MutableLiveData()
    // function to login the user
    fun loginRequest(loginRequest: LoginRequest){
        // Accessing the loginRequest function from the repository
        repository.loginRequest(loginRequest).enqueue(object :
            Callback<LoginResponse> {
            // this method will be executed if you have recieved Http response
            // Your status code will decide if the Http response is success or error
            override fun onResponse(
                call: Call<LoginResponse>, response: Response<LoginResponse>) {
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
//view Model for the SellerHomeScrren activity
class SellerHomeScreenViewModel(private val repository: Repository):ViewModel(){
    // function to return category list
    fun getCategoryList():Call<List<CategoryList>>{
        return repository.getCategoryList()
    }

    //Function to fetch list of products
    suspend fun getProductList():Call<List<ProductList>>{
        return repository.getProductList()
    }
}

// viewModel for the productsActivity view
class ProductsActivityViewModel(private val repository: Repository):ViewModel() {
    //Function to fetch list of products
    suspend fun getProductList(): Call<List<ProductList>> {
        return repository.getProductList()
    }
}

    // viewModel for the forgot password view
    class ForgotPasswordActivityViewModel(private val repository: Repository):ViewModel() {
        //Function to get the password from server
        fun requestPassword(email: String): Call<String> {
            return repository.requestPassword(email)
        }
    }
        //viewModel for the CartList Screen
        class CartListScreenViewModel(private val repository: Repository):ViewModel(){
            var cartItemsList:MutableLiveData<List<CartItemX>> = MutableLiveData()
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