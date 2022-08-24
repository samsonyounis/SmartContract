package view.packag

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.arrowBackTopRow
import view.packag.ReuableFunctions.bottomNavigation

@Composable
fun profileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            // calling the arrowBackTopRow function/ reusing it
            arrowBackTopRow(text = "Profile", navController = navController)
        },
        bottomBar = {
            // implementing the bttom navigation here
            bottomNavigation(navController = navController, "profileScreen")

        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()) {

            Button(onClick = { },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.profile_button_colors)
                ),
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "go to Profile"
                )
                Text(text = "My Account")
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "go to your Account"
                )
            }

            Button(onClick = { },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.profile_button_colors)
                ),
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "go to Notifications"
                )
                Text(text = "Notifications")
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "go to your Notifications"
                )
            }

            Button(onClick = { },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.profile_button_colors)
                ),
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "go to App Settings"
                )
                Text(text = "Settings")
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "go to your settings"
                )
            }

            Button(onClick = { },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.profile_button_colors)
                ),
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)) {
                Icon(
                    imageVector = Icons.Filled.Help,
                    contentDescription = "go to Help center"
                )
                Text(text = "Help")
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "help"
                )
            }

            Button(onClick = { },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.profile_button_colors)
                ),
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "Logout"
                )
                Text(text = "Logout")
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Account Logout"
                )
            }
        }
    }

}