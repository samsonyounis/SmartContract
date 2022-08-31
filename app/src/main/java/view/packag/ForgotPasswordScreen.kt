package view.packag

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.arrowBackTopRow
import view.packag.ReuableFunctions.commonButton

@Composable
fun forgotPasswordScreen(navController: NavController) {
    // Function Local Variables
    var email by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // calling the arrowBackTopRow function
        arrowBackTopRow(text = "", navController = navController)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Forgot Password?", style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "please Enter your email and we will send you" +
                    "\n a link to return to your account"
        )
        Spacer(modifier = Modifier.height(100.dp))
        OutlinedTextField(value = email, onValueChange = { email = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            label = { Text("Email") },
            placeholder = { Text(text = "Enter Your password") },
            keyboardActions = KeyboardActions(
                onDone = {
                    // call the request password function here
                }
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email Address"
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(100.dp))
        // reusing the button function
        commonButton(onClick = {
            // call the request password function
            // then navigate to confirm password screen to enter the password send to your email
            navController.navigate("confirmPasswordScreen")
        }, text = "continue", navController = navController)

        Spacer(modifier = Modifier.height(100.dp))
        Row() {
            Text(text = "Don't have an account?")
            Text(text = "Sign Up", Modifier.clickable {
                // navigating to the sign up screen
                navController.navigate("signUpScreen")
            },
                color = colorResource(id = R.color.brand_Color)
            )
        }
    }
}