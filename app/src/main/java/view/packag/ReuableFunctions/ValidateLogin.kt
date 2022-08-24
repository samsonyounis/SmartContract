package view.packag.ReuableFunctions

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController


@Composable
fun validateLogin(email:String, emailIsError:Boolean, password:String, passwordIsError:Boolean,
                showProgress:Boolean, selectedAccount:String,lifeCycleOwner:LifecycleOwner,
                  navController: NavController, errorMessage:String,){
    val obj = LocalContext.current
    

}