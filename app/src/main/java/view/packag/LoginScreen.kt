package view.packag

import ViewModel.LoginScreenViewModel
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import androidx.navigation.NavController
import model.LoginRequest
import view.packag.ReuableFunctions.arrowBackTopRow
import view.packag.ReuableFunctions.circularProgress
import view.packag.ReuableFunctions.commonButton
import view.packag.ReuableFunctions.commonOutlinedTextField

@Composable
fun loginScreen(navController: NavController,accountType:String,viewModel:LoginScreenViewModel) {
    // lifeCycle owner
    val lifeCycleOwner:LifecycleOwner = LocalLifecycleOwner.current
    val obj = LocalContext.current // holds the current application context
    val  sessionManager = SessionManager(obj) // instance of session Manager
    var emailIsError by remember { mutableStateOf(false) }
    var passwordIsError by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checkedState by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showProgress by remember { mutableStateOf(false) }
    var selectedAccount by remember { mutableStateOf(accountType) }
    var userToken by remember { mutableStateOf("") }

    DisposableEffect(lifeCycleOwner){
        val observer = LifecycleEventObserver{source, event ->
            if (event == Lifecycle.Event.ON_RESUME){
                showProgress = false
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))
        // calling arrowBackTopRow function
        arrowBackTopRow(text = "Sign in", navController = navController)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 40.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sign in with your Email and password " +
                    "\n or Continue with social media"
        )
        Spacer(modifier = Modifier.height(30.dp))
        commonOutlinedTextField(
            valueText = email, onValueChange = {email = it}, isError = emailIsError,
            labelText = "Email", placeholderText = "Enter your email",
            trailingIcon = Icons.Filled.Email, iconDescription = "Email address" ,
            keyboardType= KeyboardType.Email, imeAction = ImeAction.Next,
            visualTransFormation = VisualTransformation.None
        )
        Spacer(modifier = Modifier.height(10.dp))
        commonOutlinedTextField(
            valueText = password, onValueChange = {password = it}, isError = passwordIsError,
            labelText = "password", placeholderText = "Enter your password",
            trailingIcon = Icons.Filled.Lock, iconDescription = "password" ,
            keyboardType= KeyboardType.Password, imeAction = ImeAction.Done,
            visualTransFormation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = errorMessage,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement =
            Arrangement.SpaceBetween
        ) {
            Checkbox(checked = checkedState, onCheckedChange = { checkedState = it })
            Text(text = "Remember Me",
                modifier = Modifier.align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Forgot Password",
                Modifier
                    .clickable {
                        // navigating to forgot password screen
                        navController.navigate("forgotPasswordScreen")
                    }
                    .align(Alignment.CenterVertically),
                textDecoration = TextDecoration.Underline
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        // circular progress indicator
        circularProgress(showProgress = showProgress)
        Spacer(modifier = Modifier.height(20.dp))
        commonButton(onClick = {
            if(email.isBlank()) {
                emailIsError = true
                errorMessage = "Email required"
            }
            else if (!email.contains("@")){
                emailIsError = true
                errorMessage = "Incorrect Email Format"
            }

            else if (password.isBlank()){
                passwordIsError = true
                emailIsError = false
                errorMessage = "password required"
            }
            else if (password.length < 6 ){
                passwordIsError = true
                emailIsError = false
                errorMessage = "password too short"
            }
            else{
                showProgress = true
                //create loginRequest  object using the Email and password
                val loginRequest = LoginRequest(email, password)
                email = ""
                password= ""
                emailIsError = false
                passwordIsError = false
                errorMessage = ""
                // calling the login request from view model
                viewModel.loginRequest(loginRequest)
                // observing the login response from the view model
                viewModel.loginResponse.observe(lifeCycleOwner){ response->
                    if (response.toString() == "success") {
                        //Toast a message
                        Toast.makeText(obj.applicationContext, response, Toast.LENGTH_LONG).show()
                        // set a shared preerence storage to save the user
                        viewModel.token.observe(lifeCycleOwner) { token ->
                            userToken = token.toString()
                            // saving the user token in the shared preference.
                            sessionManager.saveAuthToken(userToken)
                        // Move the user to thier respective accounts
                        if (selectedAccount == "buyer") {
                            // navigating to buyer home screen
                            navController.popBackStack()
                            navController.navigate("buyerHomeScreen")
                        } else {
                            navController.popBackStack()
                            navController.navigate("sellerHomeScreen")
                        }
                    }
                    }
                    else if (response == "user not found"){
                        errorMessage = response
                        showProgress = false
                    }
                    else if (response == "password not matching"){
                        errorMessage = response.toString()
                        showProgress = false
                    }
                    else{
                        //toast the error code or error message
                        Log.d("loginResponse", response)
                        Toast.makeText(obj.applicationContext, response, Toast.LENGTH_LONG).show()
                        // Move to the next screen and display the error code
                        navController.popBackStack()
                        navController.navigate("errorScreen/Error Code!!\n\n ${response}")
                    }
                }
            }

            }, text = "continue", navController = navController)

        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Facebook, contentDescription = "sign in with FB")
            }
        }
        Row() {
            Text(text = "Don't have an account?")
            Text(text = "Sign Up", Modifier.clickable {
                // Navigating to sign up screen
               navController.navigate("signUpScreen")
            },
                color = colorResource(id = R.color.brand_Color)
            )
        }
    }
}
}