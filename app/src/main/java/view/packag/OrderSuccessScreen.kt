package view.packag.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.R
import view.packag.ReuableFunctions.commonButton
import java.security.interfaces.RSAMultiPrimePrivateCrtKey

@Composable
fun orderSuccessScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Transaction Status")
        Image(painter = painterResource(id = view.packag.R.drawable.done),
            contentDescription = "done")

        Text(text = "Order Success",
            style = MaterialTheme.typography.h1)
        // on clicking this text, then start the download service in the background
        // while at the same time proceed to the next screen
        Text(text = "Your package will be send to your address\n thank you for order",
            color = colorResource(id = view.packag.R.color.text_color))

        commonButton(onClick = {
            // navigating to the select account screen
            navController.navigate("select_Account")
        }, text ="Back to home" , navController = navController)
    }
}