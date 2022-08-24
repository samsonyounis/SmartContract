package view.packag

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.commonButton

@Composable
fun selectAccountScreen(navController: NavController) {
    //Function Local Variables
    val obj = LocalContext.current
    var radioState by remember { mutableStateOf(false) }
    var radioState2 by remember { mutableStateOf(false) }
    var selectedAccount by remember { mutableStateOf("") }
    //Column Scope
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Image(
            painterResource(id = R.drawable.thumb),
            contentDescription = "Select Account",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, colorResource(id = R.color.brand_Color), CircleShape)
        )

        Text(
            text = "Select Account",
            style = MaterialTheme.typography.h1
        )
        Text(text = "Choose your preferred Account to access \n your profile")

        // a Row of two Accounts Buyer account and seller account
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            // a column of card and radio button
            Column() {
                Card(
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(10.dp,),
                    elevation = 2.dp,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            radioState = !radioState; radioState2 = false
                            if (radioState == true) {
                                selectedAccount = "buyer"

                            } else {
                                selectedAccount = ""
                            }
                        }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painterResource(id = R.drawable.buyer),
                            contentDescription = "Buyer Account",
                            modifier = Modifier
                                .height(150.dp)
                                .width(140.dp)
                        )
                        Text(
                            text = "Buyer",
                            color = Color.Black
                        )
                    }
                }
                RadioButton(selected = radioState,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        radioState = !radioState; radioState2 = false
                        if (radioState == true) {
                            selectedAccount = "buyer"
                        }
                        else{
                            selectedAccount = ""
                        }
                    })
            }

            //another column of card and radio button
            Column() {

                Card(
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(10.dp,),
                    elevation = 2.dp,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            radioState2 = !radioState2; radioState = false
                            if (radioState2 == true) {
                                selectedAccount = "seller"
                            } else {
                                selectedAccount = ""
                            }
                        }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painterResource(id = R.drawable.seller1),
                            contentDescription = "Seller Account",
                            modifier = Modifier
                                .height(150.dp)
                                .width(140.dp)
                        )
                        Text(
                            text = "seller",
                            color = Color.Black
                        )
                    }
                }
                RadioButton(
                    selected = radioState2,
                    onClick = {
                        radioState2 = !radioState2; radioState = false
                        if (radioState2 == true) {
                            selectedAccount = "seller"
                        }
                        else{
                            selectedAccount = ""
                        }
                              },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }
        }
        // Button to Navigate to sign in screen after selecting the account
        Button(onClick = { navController.navigate("uploadProductScreen") }) {
            Text(text = "upload")
        }
        commonButton(onClick = {
            if (selectedAccount.isBlank()){
                Toast.makeText(obj, "please select account", Toast.LENGTH_LONG).show()
            }
            else {
                navController.navigate("loginScreen/"+ selectedAccount)
                selectedAccount = ""
                radioState = false
                radioState2 = false
            }
        }, text = "continue", navController = navController)
    }
}