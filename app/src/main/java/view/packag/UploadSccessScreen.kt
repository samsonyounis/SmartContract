package view.packag

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.commonButton

@Composable
fun uploadSuccessScreen(navController: NavController){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly,
    modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        Text(text = "Item status")

        Image(painter = painterResource(id = R.drawable.done),
            contentDescription = "done")

        Text(text = "Item posted successfully",
        style = MaterialTheme.typography.h1)

        Text(text = "Thank you",
        color = colorResource(id = R.color.brand_Color))

        commonButton(onClick = {
          // navigating to recent posted items screen
         navController.navigate("recentPostScreen")
        }, text = "Continue", navController = navController )
    }
}