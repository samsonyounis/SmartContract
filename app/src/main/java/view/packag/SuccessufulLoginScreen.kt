package view.packag

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.arrowBackTopRow
import view.packag.ReuableFunctions.commonButton

@Composable
fun loginSuccessScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // top backward arrow
        arrowBackTopRow(text = "Login Success", navController = navController)

        Spacer(modifier = Modifier.height(40.dp))
        Image(painter = painterResource(id = R.drawable.brand4),
            contentDescription = null)
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Login Success!", style = MaterialTheme.typography.h1)

        Text(text = "Now you are ready to go shopping")
        Spacer(modifier = Modifier.height(100.dp))
        // button
        commonButton(onClick = {
            // navigating to buyer home screen
            navController.navigate("buyerHomeScreen")
        }, text = "Back to home", navController = navController )
    }
}