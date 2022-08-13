package Api

import model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BuyerApi {
/*
    @Field("contact") contact:String,
    @Field("email") email: String,
    @Field("firstName") firstName:String,
    @Field("lastName") lastName:String,
    @Field("password") password:String,
 */
    @POST("codejava/user/singup")
    fun addUserAccount(@Body user:User):Call<SignUpResponse>

    @POST("codejava/otp")
    fun sendBackOtp(@Body otp:String):Call<String>

    @POST ("codejava/user/singin")
     fun loginRequest(@Body loginRequest: LoginRequest):Call<LoginResponse>

     @GET("codejava/category/list")
     fun getCategoryList():Call<List<CategoryList>>

     @GET("codejava/product/")
     fun getProductList():Call<List<ProductList>>

     @POST("code/")
     fun requestPassword(@Body email:String):Call<String>

     @POST("codejava/cart/add")
     fun addToCart(
         @Query("token") userToken:String,
         @Body item:AddToCartRequest):Call<AddToCartResponse>

     @GET("codejava/cart/list")
     fun getCartList(@Query("token") userToken: String):Call<GetCartResponse>
}