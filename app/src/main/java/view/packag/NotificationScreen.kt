package view.packag

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.arrowBackTopRow

@Composable
fun  notificationScreen(navController: NavController){
    var notificationsList:List<Int> = listOf(1,2,3,4,5,6,7,8)
    Scaffold(
        topBar = {
            arrowBackTopRow(text = "Notification", navController = navController)
        },
        bottomBar = {
            
        }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)) {
            for (i in notificationsList){
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Filled.ShoppingCart,
                        contentDescription ="icon" )
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(text = "Notifications title",
                        style = MaterialTheme.typography.h1)
                        Text(text = "notifications body here",
                        color = colorResource(id = R.color.text_color))
                        Text(text = "notifications date here")
                    }
                }
            }
        }
    }
}