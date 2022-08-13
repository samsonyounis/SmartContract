package view.packag

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun welcomeScreen(navController: NavController) {
    onboardingUi(navController = navController)
}