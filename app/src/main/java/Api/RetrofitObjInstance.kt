package Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.Packag.BaseUrlAddress.Companion.Base_Url

object RetrofitObjInstance {
    private val retrofitInstnce by lazy {
        Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    val ApiConnect:BuyerApi = retrofitInstnce.create(BuyerApi::class.java)
}