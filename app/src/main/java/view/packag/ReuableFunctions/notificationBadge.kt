package view.packag.ReuableFunctions

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun notificationBadge(navController: NavController, noNotifications:String) {
    Button(
        onClick = {
            // navigating to notifications screen
            navController.navigate("notificationScreen")
        },
        shape = CircleShape,
        modifier = Modifier.size(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =
            colorResource(id = view.packag.R.color.home_button_bgcolor)
        )
    ) {
        BadgedBox(badge = { Badge { Text(noNotifications) } }) {
            Icon(
                Icons.Filled.Notifications,
                contentDescription = "Notifications"
            )
        }
    }
}