package view.packag

import ViewModel.SignUpScreenViewModel
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import model.User
import view.packag.ReuableFunctions.arrowBackTopRow
import view.packag.ReuableFunctions.circularProgress
import view.packag.ReuableFunctions.commonButton
import view.packag.ReuableFunctions.commonOutlinedTextField

@Composable
fun signUpScreen(navController: NavController, viewModel:SignUpScreenViewModel) {
    // Function Variable
    var firstName by remember { mutableStateOf("") }
    var firstNameIsError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf("") }
    var lastNameIsError by remember { mutableStateOf(false) }
    var contact by remember { mutableStateOf("") }
    var contactIsError by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var emailIsError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var passwordIsError by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordIsError by remember { mutableStateOf(false) }
    var showProgress by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var obj = LocalContext.current
    // Creating instance of event lifecyle owner
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
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
//Scaffold layout
    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
            ) {
                // calling arrowBackTopRow function
                arrowBackTopRow(text = "Sign up", navController = navController)

                Text(text = "Register Account", style = MaterialTheme.typography.h1)
                Text(text = "Complete your details or continue with social media")
            }
        }
    ) {
        // Show the dialog box only when pay Now button is clicked
        if (openDialog== true) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                        Text(text = "Creating User Account",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold)
                },
                text = {
                    // showing the circular progress indicator
                    circularProgress(showProgress = showProgress)
                },
                buttons = { }
            )
        }
        LazyColumn(verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(10.dp)) {
            item {
                // text field for first name.
                commonOutlinedTextField(
                    valueText = firstName, onValueChange = {firstName = it}, isError = firstNameIsError,
                    labelText = "First name", placeholderText = "Enter your first name",
                    trailingIcon = Icons.Filled.Person, iconDescription = "your first name" ,
                    keyboardType= KeyboardType.Text, imeAction = ImeAction.Next,
                    visualTransFormation = VisualTransformation.None
                )
            }
            item {
                // text field for last name.
                commonOutlinedTextField(
                    valueText = lastName, onValueChange = {lastName = it}, isError = lastNameIsError,
                    labelText = "Last name", placeholderText = "Enter your last name",
                    trailingIcon = Icons.Filled.Person, iconDescription = "your last name" ,
                    keyboardType= KeyboardType.Text, imeAction = ImeAction.Next,
                    visualTransFormation = VisualTransformation.None
                )
            }
            item {
                // text field for contact.
                commonOutlinedTextField(
                    valueText = contact, onValueChange = {contact = it}, isError = contactIsError,
                    labelText = "phone number", placeholderText = "Enter your phone number",
                    trailingIcon = Icons.Filled.Phone, iconDescription = "phone number" ,
                    keyboardType= KeyboardType.Number, imeAction = ImeAction.Next,
                    visualTransFormation = VisualTransformation.None
                )
            }
            item {
                // text field for email address.
                commonOutlinedTextField(
                    valueText = email, onValueChange = {email = it}, isError = emailIsError,
                    labelText = "email address", placeholderText = "Enter your email address",
                    trailingIcon = Icons.Filled.Email, iconDescription = "your email" ,
                    keyboardType= KeyboardType.Email, imeAction = ImeAction.Next,
                    visualTransFormation = VisualTransformation.None
                )
            }
            item {
                // text field for password.
                commonOutlinedTextField(
                    valueText = password, onValueChange = {password = it}, isError = passwordIsError,
                    labelText = "password", placeholderText = "Enter your password",
                    trailingIcon = Icons.Filled.Lock, iconDescription = "your password" ,
                    keyboardType= KeyboardType.Password, imeAction = ImeAction.Next,
                    visualTransFormation = VisualTransformation.None
                )
            }
            item {
                // text field for confirming password
                commonOutlinedTextField(
                    valueText = confirmPassword, onValueChange = {confirmPassword = it}, isError = confirmPasswordIsError,
                    labelText = "confirm password", placeholderText = "confirm your password",
                    trailingIcon = Icons.Filled.Lock, iconDescription = "confirm password" ,
                    keyboardType= KeyboardType.Password, imeAction = ImeAction.Done,
                    visualTransFormation = VisualTransformation.None
                )
            }
            item {
                Text(text = errorMessage,
                    color = Color.Red)
            }
            item {
                commonButton(text = "continue", navController =navController ,onClick = {
                    if (firstName.isBlank()) {
                        firstNameIsError = true
                        lastNameIsError = false
                        contactIsError = false
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = false
                        errorMessage = "* First Name is Empty"
                    }
                    else if (lastName.isBlank()){
                        lastNameIsError = true
                        firstNameIsError = false
                        contactIsError = false
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = false
                        errorMessage = "* Last Name is Empty"
                    }
                    else if (contact.isBlank()){
                        firstNameIsError = false
                        lastNameIsError = false
                        contactIsError = true
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = false
                        errorMessage = "* Contact field is Empty"
                    }
                    else if (email.isBlank()){
                        firstNameIsError = false
                        lastNameIsError = false
                        contactIsError = false
                        emailIsError = true
                        passwordIsError = false
                        confirmPasswordIsError = false
                        errorMessage = "*Email Required"
                    }
                    else if (!email.contains("@")){
                        firstNameIsError = false
                        lastNameIsError = false
                        contactIsError = false
                        emailIsError = true
                        passwordIsError = false
                        confirmPasswordIsError = false
                        errorMessage = "Incorrect email format"
                    }
                    else if (password.isBlank()){
                        firstNameIsError = false
                        lastNameIsError = false
                        contactIsError = false
                        emailIsError = false
                        passwordIsError = true
                        confirmPasswordIsError = false
                        errorMessage = "Password field id black"
                    }
                    else if (password.length <6) {
                        firstNameIsError = false
                        lastNameIsError = false
                        contactIsError = false
                        emailIsError = false
                        passwordIsError = true
                        confirmPasswordIsError = false
                        errorMessage = "Password too Short"
                    }
                    else if (confirmPassword.isBlank()){
                        firstNameIsError = false
                        lastNameIsError = false
                        contactIsError = false
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = true
                        errorMessage = "Please Confirm Your password"
                    }
                    else if (!confirmPassword.contentEquals(password)){
                        firstNameIsError = false
                        lastNameIsError = false
                        contactIsError = false
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = true
                        errorMessage = "Passwords not matching"
                    }
                    else {
                        openDialog = true
                        showProgress = true
                        errorMessage = ""
                        // creating the user object
                       val user = User(contact, email,firstName, lastName,password)
                        // accessing the addUserAccount function from the viewmodel
                        viewModel.addUserAccount(user)
                        //observing the signUpResponse from the view Model
                        viewModel.signUpResponse.observe(lifeCycleOwner) { response ->
                            if (response == "success") {
                                openDialog = false
                                showProgress = false
                                errorMessage = ""
                                //Toast a message
                                Toast.makeText(obj.applicationContext, response, Toast.LENGTH_LONG).show()
                                // Move the user to the OTP screen
                                obj.startActivity(Intent(obj, OTPScreenActivity::class.java))
                                navController.popBackStack()
                            } else if (response == "sign up failure, user already exists") {
                                openDialog = false
                                showProgress = false
                                errorMessage = response
                            } else {
                                //toast the error code or error message
                                Toast.makeText(obj.applicationContext, response, Toast.LENGTH_LONG).show()
                                // Move to the next screen and display the error code
                                navController.popBackStack()
                                navController.navigate("errorScreen/Error Code!!\n\n ${response}")
                                openDialog = false
                                showProgress = false
                                errorMessage = ""
                            }
                        }
                    }
                })
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Filled.Facebook,
                            contentDescription = "sign up with FB"
                        )
                    }
                }
            }

            item {
                Text(text = "By Continuing, you confirm that you agree with " +
                        "our terms and conditions")
            }
        }
    }
}