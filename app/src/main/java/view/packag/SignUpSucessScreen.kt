package view.packag

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.commonButton
import view.packag.ui.theme.SmartContractTheme

@Composable
fun signupSccessScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Your account has been created successfully")
        commonButton(onClick = {
                               // navigating to the sign in screen
                               navController.navigate("select_Account")
        }, text ="Back to your account" , navController = navController)
    }
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SmartContractTheme {
        Greeting()
    }
}
 */