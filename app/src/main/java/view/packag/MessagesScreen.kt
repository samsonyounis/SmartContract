package view.packag

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.arrowBackTopRow
import view.packag.ReuableFunctions.bottomNavigation

@Composable
fun messageScreen(navController: NavController){
    // function local variables
    var listOfMessages = remember { listOf(1,2,3,4,5,0,5,7,8,8,5,6)}
    // Scaffold layout.
    Scaffold(topBar = {
        arrowBackTopRow(text = "Messages", navController = navController)
    },
    bottomBar = {
        // implementing the bottom navigation here
        bottomNavigation(navController = navController,"messageScreen")
    }
    ) {
        // scrollable column of messages
        Column(modifier = Modifier
            .padding(16.dp, bottom = 58.dp, end = 16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
          for (message in listOfMessages){
              // display each message
              Row(modifier = Modifier.fillMaxWidth().clickable { /*TODO*/ },
              horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                  Icon(imageVector = Icons.Filled.Message, contentDescription = "sender",
                  modifier = Modifier
                      .size(70.dp)
                      .background(
                          shape = CircleShape,
                          color = Color.LightGray
                      ))

                  Column {
                      Row(modifier = Modifier.fillMaxWidth(),
                          horizontalArrangement = Arrangement.SpaceBetween) {
                          Text(text = "message title",
                              style = MaterialTheme.typography.h1)
                          Text(text = "8:00 a.m", modifier = Modifier.align(Alignment.CenterVertically))
                      }

                      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                          Text(text = "message body")
                          Text(text = message.toString(), modifier = Modifier
                              .align(Alignment.CenterVertically)
                              .background(
                                  color = Color.Red,
                                  shape = CircleShape
                              )
                              .size(30.dp),
                          color = Color.White,
                          textAlign = TextAlign.Center)
                      }
                  }
              }
          }
        }
    }
}