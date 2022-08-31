package view.packag

import ViewModel.*
import android.net.Uri.decode
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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
    // instance of seller screen view model
    val sellerViewModelFactory by lazy { SellerHomeScreenViewModelFactory(repository) }
    val sellerViewModel by lazy { ViewModelProvider(this, sellerViewModelFactory).get(
        SellerHomeScreenViewModel::class.java)
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
    // instance of recentPostScreen ViewModel
    val recentPostViewModelFactory by lazy { RecentPostScreenViewModelFactory(repository) }
    // instance of buyer screen viewmodel
    val recentPostViewModel by lazy { ViewModelProvider(this, recentPostViewModelFactory).get(
        RecentPostScreenViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartContractTheme() {
                // instance of systemUiController
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    // setting the status bar colors.
                    systemUiController.setStatusBarColor(
                        color = Color.White,
                        darkIcons = true
                    )
                }
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
                        composable("loginScreen"){
                            loginScreen(navController = navController,loginViewModel)
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

                        composable("buyerHomeScreen") {
                            buyerHomeScreen(navController, buyerviewModel)
                        }
                        composable("productsScreen") {
                            productScreen(navController, buyerviewModel)
                        }

                        composable(
                            "productDetailScreen/{proName}/{proPrice}/{proDes}/{id}/{proId}",
                            arguments = listOf(
                                navArgument("proName"){ type = NavType.StringType },
                                navArgument("proPrice"){type = NavType.IntType},
                                navArgument("proDes"){type = NavType.StringType},
                                navArgument("id"){type = NavType.IntType},
                                navArgument("proId"){type = NavType.IntType}
                            )){
                            val proName = it.arguments?.getString("proName")
                            val proPrice = it.arguments?.getInt("proPrice")
                            val proDes = it.arguments?.getString("proDes")
                            val id = it.arguments?.getInt("id")
                            val proId = it.arguments?.getInt("proId")
                            if (proPrice != null) {
                                if (id != null) {
                                    if (proId != null) {
                                        productDetailScreen(navController,proName.toString(),
                                            proPrice.toInt(),proDes.toString(),id,proId,
                                        proDetailviewModel)
                                    }
                                }
                            }
                        }

                        composable("cartListScreen") {
                            cartListScreen(navController, cartListScreenviewModel)
                        }

                        composable("sellerHomeScreen") {
                            sellerHomeScreen(navController, sellerViewModel)
                        }

                        composable("profileScreen") {
                            profileScreen(navController)
                        }

                        composable("confirmPasswordScreen") {
                            confirmPasswordScreen(navController)
                        }

                        composable("contractSignSuccessScreen/{noItems}/{totalPrice}",
                            arguments = listOf(
                                navArgument("noItems"){type = NavType.IntType},
                                navArgument("totalPrice"){type = NavType.IntType})) {
                            val noItems = it.arguments?.getInt("noItems", 0)
                            val totalPrice = it.arguments?.getInt("totalPrice",0)
                            if (totalPrice != null) {
                                if (noItems != null) {
                                    contractSignSuccess(navController,noItems,totalPrice)
                                }
                            }
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

                        composable("payScreen/{noItems}/{totalPrice}",
                        arguments = listOf(
                            navArgument("noItems"){type = NavType.IntType},
                            navArgument("totalPrice"){type = NavType.IntType}
                        )) {
                            val noItems = it.arguments?.getInt("noItems",0)
                            val totalPrice = it.arguments?.getInt("totalPrice",0)
                            if (noItems != null) {
                                if (totalPrice != null) {
                                    payScreen(navController,noItems,totalPrice, cartListScreenviewModel)
                                }
                            }
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
                        composable("productsByCategoryScreen") {
                            productsByCategoryScreen(navController, sellerViewModel)
                        }
                        composable("uploadSuccessScreen"){
                            uploadSuccessScreen(navController = navController)
                        }
                        composable("recentPostScreen"){
                            recentPostScreen(navController = navController,recentPostViewModel)
                        }
                        composable("messageScreen"){
                            messageScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
