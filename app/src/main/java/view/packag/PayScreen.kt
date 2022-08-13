package view.packag

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun payScreen(navController: NavController){
    var productList:List<Int> = listOf(1,2,3,4,5,6,7,8)
    Scaffold(
        topBar = {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start) {
                // backward arrow
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "move back")
                }
                Spacer(modifier = Modifier.weight(3f))
                Column(modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(5f)) {

                    Text(
                        text = "Check out",
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        text = "N items"
                    )
                }
            }
        },
        bottomBar = {
            BottomAppBar(backgroundColor = Color.White,
            modifier = Modifier.height(150.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                Column() {
                    Text(text = "image here...")
                    Text(text = "Toatl here...")
                    Text(text = "price here...")
                }
                Button(
                    modifier = Modifier
                        .width(150.dp)
                        .height(60.dp),
                    onClick = {
                              // navigating to oder successful screen
                              navController.navigate("orderSuccessScreen")
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.brand_Color),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "pay Now")
                }
            }
            }

        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Your cart",
                style = MaterialTheme.typography.h1)
                Text(text = "see All",
                color = colorResource(id = R.color.text_color),
                modifier = Modifier.clickable { /*TODO*/ })
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow() {
                for (item in productList) {
                    item {
                        Card(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(15.dp),
                            backgroundColor = colorResource(id = R.color.card_bgColor)
                        ) {
                            Image(
                                painter = rememberImagePainter(data = "item.imageUrl",
                                    builder = {
                                        crossfade(1000)
                                        placeholder(R.drawable.loading_images)
                                        error(R.drawable.errorloading_image)
                                    }),
                                contentDescription = "Product",
                                modifier = Modifier.size(150.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Your Address",
                    style = MaterialTheme.typography.h1)
                Text(text = "Edit Address",
                    color = colorResource(id = R.color.text_color),
                    modifier = Modifier.clickable { /*TODO*/ })
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Address goes here.......")
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "shipping options",
                    style = MaterialTheme.typography.h1)
                Text(text = "choose options",
                    color = colorResource(id = R.color.text_color),
                    modifier = Modifier.clickable { /*TODO*/ })
            }
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "payment methods",
                    style = MaterialTheme.typography.h1)
                Text(text = "see All",
                    color = colorResource(id = R.color.text_color),
                    modifier = Modifier.clickable { /*TODO*/ })
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow() {
                for (item in productList) {
                    item {
                        Card(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(15.dp),
                            backgroundColor = colorResource(id = R.color.card_bgColor),
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable { }
                        ) {
                            Image(
                                painter = rememberImagePainter(data = "item.imageUrl",
                                    builder = {
                                        crossfade(1000)
                                        placeholder(R.drawable.loading_images)
                                        error(R.drawable.errorloading_image)
                                    }),
                                contentDescription = "Product",
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}