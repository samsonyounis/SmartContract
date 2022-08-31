package view.packag.ReuableFunctions

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.R
import view.packag.SessionManager

@Composable
fun bottomNavigation(navController: NavController, currentScreen:String){
    val obj = LocalContext.current // holds the current application context
    val  sessionManager = SessionManager(obj) // instance of session Manager
    var accountType by remember { mutableStateOf(sessionManager.getAccountType().toString()) }
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.bottom_nav_color),
        elevation = 15.dp
    ) {
        if (currentScreen == accountType) {
            BottomNavigationItem(
                selected = true, onClick = { /*TODO*/ },
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                selectedContentColor = colorResource(id = R.color.brand_Color)
            )

            BottomNavigationItem(selected = false, onClick = {
                // navigating to the faorite screen
                navController.navigate("favoritesScreen")
            },
                icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") })

            BottomNavigationItem(
                selected = false, onClick = {
                    // navigating to messages screen
                    navController.navigate("messageScreen")
                },
                icon = { Icon(Icons.Filled.Message, contentDescription = "Messages") },
            )

            BottomNavigationItem(selected = false, onClick = {
                // navigating to profile
                navController.navigate("profileScreen")
            },
                icon = { Icon(Icons.Filled.Person, contentDescription = "profile") })
        }
        else if (currentScreen == "favoritesScreen"){
            BottomNavigationItem(
                selected = false, onClick = {
                   if (accountType == "buyer"){
                       navController.navigate("buyerHomeScreen")
                   }
                   else{
                       navController.navigate("sellerHomeScreen")
                   }
                },
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            )

            BottomNavigationItem(selected = true, onClick = {/* do not navigate*/},
                icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
                selectedContentColor = colorResource(id = R.color.brand_Color)
            )

            BottomNavigationItem(
                selected = false, onClick = {
                    // navigating to messages screen
                    navController.navigate("messageScreen")
                },
                icon = { Icon(Icons.Filled.Message, contentDescription = "Messages") },
            )

            BottomNavigationItem(selected = false, onClick = {
                // navigating to profile
                navController.navigate("profileScreen")
            },
                icon = { Icon(Icons.Filled.Person, contentDescription = "profile") })
        }
        else if (currentScreen == "messageScreen"){
            BottomNavigationItem(
                selected = false, onClick = {
                    if (accountType == "buyer"){
                        navController.navigate("buyerHomeScreen")
                    }
                    else{
                        navController.navigate("sellerHomeScreen")
                    }
                },
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            )

            BottomNavigationItem(selected = false, onClick = {
                    // navigating to favorites screen
                navController.navigate("favoritesScreen")
            },
                icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") }
            )

            BottomNavigationItem(
                selected = true, onClick = {/* do not naviagte*/},
                icon = { Icon(Icons.Filled.Message, contentDescription = "Messages") },
                selectedContentColor = colorResource(id = R.color.brand_Color)
            )

            BottomNavigationItem(selected = false, onClick = {
                // navigating to profile
                navController.navigate("profileScreen")
            },
                icon = { Icon(Icons.Filled.Person, contentDescription = "profile") })
        }

        else if (currentScreen == "profileScreen"){
            BottomNavigationItem(
                selected = false, onClick = {
                    if (accountType == "buyer"){
                        navController.navigate("buyerHomeScreen")
                    }
                    else{
                        navController.navigate("sellerHomeScreen")
                    }
                },
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            )

            BottomNavigationItem(selected = false, onClick = {
                // navigating to favorites screen
                navController.navigate("favoritesScreen")
            },
                icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") }
            )

            BottomNavigationItem(
                selected = false, onClick = {
                   // navigate to message screen
                    navController.navigate("messageScreen")
                },
                icon = { Icon(Icons.Filled.Message, contentDescription = "Messages") }
            )

            BottomNavigationItem(selected = false, onClick = {
                // do not navigate.
            },
                icon = { Icon(Icons.Filled.Person, contentDescription = "profile") },
                        selectedContentColor = colorResource(id = R.color.brand_Color)
            )
        }

        else{
            BottomNavigationItem(
                selected = false, onClick = {
                    if (accountType == "buyer"){
                        navController.navigate("buyerHomeScreen")
                    }
                    else{
                        navController.navigate("sellerHomeScreen")
                    }
                },
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            )

            BottomNavigationItem(selected = false, onClick = {
                // navigating to favorites screen
                navController.navigate("favoritesScreen")
            },
                icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") }
            )

            BottomNavigationItem(
                selected = false, onClick = {
                 // navigating to the message screen
                    navController.navigate("messageScreen")
                    },
                icon = { Icon(Icons.Filled.Message, contentDescription = "Messages") }
            )

            BottomNavigationItem(selected = false, onClick = {
                // navigating to profile
                navController.navigate("profileScreen")
            },
                icon = { Icon(Icons.Filled.Person, contentDescription = "profile") })
        }
    }
}