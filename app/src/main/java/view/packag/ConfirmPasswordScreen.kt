package view.packag

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
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
fun confirmPasswordScreen(navController: NavController) {
    var newPassword by remember { mutableStateOf("") }
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
            text = "Confirm password", style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "please Enter the new Password send to your email"
        )
        Spacer(modifier = Modifier.height(100.dp))
        OutlinedTextField(value = newPassword, onValueChange = { newPassword = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            label = { Text("Password") },
            placeholder = { Text(text = "Enter Your password") },
            keyboardActions = KeyboardActions(
                onDone = {
                    // call the login function to login the user with the new password
                }
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "New password"
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(100.dp))

        // reusing the button function
        commonButton(onClick = {
            // then navigate to successful login screen
            navController.navigate("successfulLoginScreen")
        }, text = "continue", navController = navController)
        Spacer(modifier = Modifier.height(100.dp))
        Row() {
            Text(text = "Don't have an account?")
            Text(text = "Sign Up", Modifier.clickable {
                //navigating to sign up screen
                navController.navigate("signUpScreen")
            },
                color = colorResource(id = R.color.brand_Color)
            )
        }
    }
}