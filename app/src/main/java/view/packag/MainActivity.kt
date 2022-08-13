package view.packag

import ViewModel.*
import android.net.Uri.decode
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import repository.Repository
import view.packag.ui.theme.SmartContractTheme
import view.packag.ui.theme.orderSuccessScreen
import java.lang.Long.decode
import java.net.URLDecoder
import java.net.URLDecoder.decode
import java.net.URLEncoder
import kotlin.math.log

class MainActivity : ComponentActivity() {
    // creating Variable of type navController
    private lateinit var navController:NavHostController
    // Instance of repository
    val repository by lazy { Repository() }
    // instance of loginScreen ViewModelFactory
    val loginViewModelFactory by lazy { LoginScreenViewModelFactory(repository) }
    val loginViewModel by lazy { ViewModelProvider(this,loginViewModelFactory).get(
        LoginScreenViewModel::class.java)
    }
    //instance of Signup view model
    val signUpViewModelFactory by lazy { SignUpScreenViewModelFactory(repository) }
    val signUpViewModel by lazy { ViewModelProvider(this,signUpViewModelFactory).get(
        SignUpScreenViewModel::class.java)
    }
    // Instance of buyer screen viewmodel factory
    val viewModelFactory by lazy { BuyerHomeScreenViewModelFactory(repository) }
    // instance of buyer screen viewmodel
    val buyerviewModel by lazy { ViewModelProvider(this, viewModelFactory).get(
            BuyerHomeScreenViewModel::class.java)
    }
    // instance of productDetailViewModel
    val prodDetailViewModelFactory by lazy { ProductDetailScreenViewModelFactory(repository) }
    // instance of buyer screen viewmodel
    val proDetailviewModel by lazy { ViewModelProvider(this, prodDetailViewModelFactory).get(
        ProductDetailScreenViewModel::class.java)
    }
    //instance of cartListViewModel
    val cartListViewModelFactory by lazy { CartListScreenViewModelFactory(repository) }
    // instance of buyer screen viewmodel
    val cartListScreenviewModel by lazy { ViewModelProvider(this, cartListViewModelFactory).get(
        CartListScreenViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartContractTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    // initializing the navController
                    navController = rememberNavController()
                    // setting up the NavHost
                    NavHost(
                        navController = navController,
                        startDestination = "animateSplashScreen_Screen"
                    ) {
                        composable("welcome_Screen"
                        ) {
                            welcomeScreen(navController)
                        }

                        composable("select_Account") {
                            selectAccountScreen(navController)
                        }

                        composable("animateSplashScreen_Screen") {
                            animateSplashScreen(navController)
                        }
                        composable("loginScreen/{accountType}",
                            arguments = listOf(
                                navArgument("accountType") {
                                    type = NavType.StringType })
                        ){
                            val a = it.arguments?.getString("accountType")
                            loginScreen(navController = navController,a.toString(),loginViewModel)
                        }

                        composable("successfulLoginScreen") {
                            loginSuccessScreen(navController)
                        }


                        composable("forgotPasswordScreen") {
                            forgotPasswordScreen(navController)
                        }

                        composable("uploadProductScreen") {
                            uploadProductscreen(navController)
                        }

                        composable("signUpScreen") {
                            signUpScreen(navController, signUpViewModel)
                        }

                        composable("buyerHomeScreen/{userToken}",
                        arguments = listOf(
                            navArgument("userToken"){type = NavType.StringType}
                        )) {
                            val userToken = it.arguments?.getString("userToken")
                            buyerHomeScreen(navController, buyerviewModel,
                                userToken.toString())
                        }
                        composable("productsScreen/{userToken}",
                        arguments = listOf(
                            navArgument("userToken"){type = NavType.StringType}
                        )) {
                            val userToken = it.arguments?.getString("userToken")
                            productScreen(navController, buyerviewModel,userToken.toString())
                        }

                        composable(
                            "productDetailScreen/{proName}/{proPrice}/{proDes}/{id}/{proId}/{userToken}",
                            arguments = listOf(
                                navArgument("proName"){ type = NavType.StringType },
                                navArgument("proPrice"){type = NavType.IntType},
                                navArgument("proDes"){type = NavType.StringType},
                                navArgument("id"){type = NavType.IntType},
                                navArgument("proId"){type = NavType.IntType},
                                navArgument("userToken"){type = NavType.StringType}
                            )){
                            val proName = it.arguments?.getString("proName")
                            val proPrice = it.arguments?.getInt("proPrice")
                            val proDes = it.arguments?.getString("proDes")
                            val id = it.arguments?.getInt("id")
                            val proId = it.arguments?.getInt("proId")
                            val userToken = it.arguments?.getString("userToken")
                            Log.d("answer", proDes.toString())
                            if (proPrice != null) {
                                if (id != null) {
                                    if (proId != null) {
                                        productDetailScreen(navController,proName.toString(),
                                            proPrice.toInt(),proDes.toString(),id,proId,
                                        proDetailviewModel,userToken.toString())
                                    }
                                }
                            }
                        }

                        composable("cartListScreen/{userToken}",
                        arguments = listOf(
                            navArgument("userToken"){type = NavType.StringType}
                        )) {
                            val userToken = it.arguments?.getString("userToken").toString()
                            cartListScreen(navController,userToken,cartListScreenviewModel)
                        }

                        composable("sellerHomeScreen/{userToken}",
                        arguments = listOf(
                            navArgument("userToken"){type = NavType.StringType}
                        )) {
                            val userToken = it.arguments?.getString("userToken")
                            sellerHomeScreen(navController,userToken.toString())
                        }

                        composable("profileScreen") {
                            profileScreen(navController)
                        }

                        composable("confirmPasswordScreen") {
                            confirmPasswordScreen(navController)
                        }

                        composable("contractSignSuccessScreen") {
                            contractSignSuccess(navController)
                        }

                        composable("signUpSccessScreen") {
                            signupSccessScreen(navController)
                        }

                        composable("errorScreen/{errorMessage}",
                            arguments = listOf(
                                navArgument("errorMessage"){
                                    type = NavType.StringType
                                }
                            )) {
                            val error = it.arguments?.getString("errorMessage")
                            errorScreen(navController,error.toString())
                        }

                        composable("payScreen") {
                            payScreen(navController)
                        }

                        composable("orderSuccessScreen") {
                            orderSuccessScreen(navController)
                        }
                        composable("favoritesScreen") {
                            favoriteScreen(navController, buyerviewModel)
                        }

                        composable("notificationScreen") {
                            notificationScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
