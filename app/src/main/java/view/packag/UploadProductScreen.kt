package view.packag

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun uploadProductscreen(navController: NavController) {
    Scaffold(
        topBar = {
            Column(verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.Center) {
                    IconButton(onClick = {
                        // navigating the back stack
                        navController.navigateUp()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack,
                            contentDescription ="Go back" )
                    }
                    Spacer(modifier = Modifier.width(60.dp))
                    Text(text = "Sports")
                }

            }
        },
        bottomBar = {
            Row(modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Center) {
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = colorResource(id = R.color.brand_Color)
                    ),
                    modifier = Modifier
                        .width(90.dp)
                        .height(50.dp)) {
                    Text(text = "Back")
                }
                Spacer(modifier = Modifier.width(140.dp))
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.brand_Color),
                        contentColor = colorResource(id = R.color.white)
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .width(90.dp)) {
                    Text(text = "Upload")
                }
            }
        }
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(16.dp)) {

            Text(text = "Upload Product",
                color = colorResource(id = R.color.black),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

            Text(text = "Take picture of the product and provide more details of the" +
                    "product such as the price e.t.c")

            Row() {
                Text(text = "Take Photo",
                    modifier = Modifier.clickable {  },
                    textDecoration = TextDecoration.Underline)
                Spacer(modifier = Modifier.width(50.dp))
                Text(text = "Upload product",
                    modifier = Modifier.clickable {  },
                    textDecoration = TextDecoration.Underline)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(elevation = 5.dp,
                shape = RoundedCornerShape(20.dp),
                backgroundColor = colorResource(id = R.color.white),
                modifier = Modifier
                    .width(300.dp)
                    .height(400.dp),

                ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)) {
                    Column(horizontalAlignment = Alignment.Start) {

                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "null"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier.width(100.dp).height(100.dp)) {
                        Icon(imageVector = Icons.Filled.AddAPhoto,
                            contentDescription = "Camera",
                            Modifier.size(100.dp))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.brand_Color,),
                            contentColor = colorResource(id = R.color.black),
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.height(70.dp)) {
                        Text(text = "Take photo or upload")

                    }
                }

            }
        }

    }

}