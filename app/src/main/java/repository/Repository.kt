package repository

import Api.RetrofitObjInstance
import model.*
import retrofit2.Call

class Repository {
     fun addUserAccount(user:User): Call<SignUpResponse> {
        return RetrofitObjInstance.ApiConnect.addUserAccount(user)
    }

    fun  sendBackOtp(otp:String):Call<String>{
        return RetrofitObjInstance.ApiConnect.sendBackOtp(otp)
    }

     fun loginRequest(loginRequest: LoginRequest):Call<LoginResponse>{
        return RetrofitObjInstance.ApiConnect.loginRequest(loginRequest)
    }
// function to get the list of all categories frm the server
    fun getCategoryList():Call<List<CategoryList>>{
        return RetrofitObjInstance.ApiConnect.getCategoryList()
    }

    // Function to get/fetch products from the server
     fun getProductList():Call<List<ProductList>>{
        return RetrofitObjInstance.ApiConnect.getProductList()
    }

    // function to request for password from server
    fun requestPassword(email:String):Call<String>{
        return RetrofitObjInstance.ApiConnect.requestPassword(email)
    }

    // function to add item to cart
    fun addToCart(userToken: String, item:AddToCartRequest):Call<AddToCartResponse>{
        return RetrofitObjInstance.ApiConnect.addToCart(userToken,item)
    }
    //function to return the list of cart items
    fun getCartList(userToken:String):Call<GetCartResponse>{
        return RetrofitObjInstance.ApiConnect.getCartList(userToken)
    }
    // function to return list of all recent posted products
    fun getRecentPostedItems():Call<List<ProductList>>{
        return RetrofitObjInstance.ApiConnect.getRecentPostedItems()
    }
}