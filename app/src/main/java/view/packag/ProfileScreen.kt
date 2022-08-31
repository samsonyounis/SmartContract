package view.packag

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.arrowBackTopRow
import view.packag.ReuableFunctions.bottomNavigation
import view.packag.ReuableFunctions.profileButton

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
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()) {

            profileButton(icon = Icons.Filled.Person, iconDescription = "profile", text ="My Account"){

            }
            // notifications button
            profileButton(icon = Icons.Filled.Notifications, iconDescription = "Notifications",
                text ="Notifications"){
            }

            // settings button
            profileButton(icon = Icons.Filled.Settings, iconDescription = "app settings",
                text ="Settings"){
            }
            // help button
            profileButton(icon = Icons.Filled.Help, iconDescription = "Help center",
                text ="Help"){
            }
            // notifications button
            profileButton(icon = Icons.Filled.Logout, iconDescription = "logout",
                text ="Logout"){
            }
        }
    }

}