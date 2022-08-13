package view.packag

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun loginSuccessScreen(navController: NavController) {
    var newPassword by remember { mutableStateOf("") }
    var obj = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = {
                // navigating the back stack
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription ="navigate back" )
            }
            Text(text = "Login Success", fontSize = 18.sp,
                color = Color.DarkGray)
        }
        Spacer(modifier = Modifier.height(40.dp))
        Image(painter = painterResource(id = R.drawable.brand4),
            contentDescription = null)
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Login Success!", fontSize = 24.sp, color = Color.Black,
            fontWeight = FontWeight.Bold)

        Text(text = "Now you are ready to go shopping")
        Spacer(modifier = Modifier.height(100.dp))

        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                // navigating to buyer home screen
                navController.navigate("buyerHomeScreen")
            }, shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(255, 127, 80),
                contentColor = Color.White
            )
        ) {
            Text(text = "Back to home")
        }
    }
}