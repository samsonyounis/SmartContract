package view.packag

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

@Composable
fun contractSignSuccess(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Transaction Status")
        Image(painter = painterResource(id = R.drawable.done),
            contentDescription = "done")

        Text(text = "Contract Successfully\n Submitted",
            style = MaterialTheme.typography.h1)
        // on clicking this text, then start the download service in the background
        // while at the same time proceed to the next screen
        Text(text = "Download & Continue",
            color = colorResource(id = R.color.brand_Color),
            modifier = Modifier.clickable { /*TODO*/ })

        Button(onClick = {
                         // navigating to the pay screen
                         navController.navigate("payScreen")
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.brand_Color),
                contentColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Continue")
        }
    }
}