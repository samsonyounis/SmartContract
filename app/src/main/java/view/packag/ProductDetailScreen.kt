package view.packag

import ViewModel.ProductDetailScreenViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import model.AddToCartRequest
import view.packag.ReuableFunctions.circularProgress

@Composable
fun productDetailScreen(navController: NavController,proName:String, proPrice:Int, proDes:String,
            id:Int, proId:Int,viewModel: ProductDetailScreenViewModel) {
    // Function local variables
    val obj = LocalContext.current  // instance of session Manager
    val sessionManager = SessionManager(obj)  // variable to hold the user token
    var userToken:String by remember{ mutableStateOf(sessionManager.fetchAuthToken().toString()) }
    var rating_value by remember { mutableStateOf("5") }
    var quantatityOfItems by remember { mutableStateOf(0) }
    var openDialog by remember { mutableStateOf(false) }
    var showProgress by remember { mutableStateOf(false) }
    val lifeCycleOwner:LifecycleOwner = LocalLifecycleOwner.current
    //Scaffold layout
    Scaffold(backgroundColor = colorResource(id = R.color.profile_button_colors),
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.Start) {
                // back button
                Button(onClick = {navController.navigateUp() /*navigating back stack */},
                    shape = CircleShape,
                    modifier = Modifier.size(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White))
                {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "go back")
                }
                Spacer(modifier = Modifier.weight(3f))
                //rating button here
                Button(onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ),
                    modifier = Modifier
                        .width(100.dp)
                        .weight(3f)
                        .height(40.dp)) {
                    Text(text = rating_value)
                    Icon(imageVector = Icons.Filled.Star,
                        contentDescription ="Rating star")
                }
            }
        }
    ) {
        // Show the dialog box only when pay Now button is clicked
        if (openDialog== true) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(text = "Adding item to cart",
                        style = MaterialTheme.typography.h1)
                },
                text = { // showing circular progress here
                       circularProgress(showProgress = showProgress)
                },
                buttons = { }
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()) {
            //put the image of the product here
            Image(
                painter = rememberImagePainter(data = "imageUrl no image",
                    builder = {
                        crossfade(1000)
                        placeholder(R.drawable.loading_images)
                        error(R.drawable.errorloading_image)
                    }),
                contentDescription = "Product",
                Modifier.background(
                    color = colorResource(id = R.color.home_button_bgcolor),
                    shape = RoundedCornerShape(15.dp)
                )
            )
            // next put the same 4 images here in a row in different rotation angles
            // Card view here
            Card(elevation = 5.dp,
                backgroundColor = colorResource(id = R.color.white),
                shape = RoundedCornerShape(15.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize()) {
                    // display product name here
                        Text(text = proName,
                            modifier = Modifier.padding(16.dp))

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        // display product price here
                        Text(text = proPrice.toString(),
                            color = Color.Red,
                            fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(100.dp))
                        Button(onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id =
                                R.color.redish_color),
                                contentColor = Color.Red
                            ),
                            modifier = Modifier
                                .width(100.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Icon(imageVector = Icons.Filled.Favorite,
                                contentDescription = "favorites")
                        }
                    }
                    // product description here
                    Text(text = proDes,
                        modifier = Modifier.padding(16.dp))

                    Card(backgroundColor = colorResource(id = R.color.profile_button_colors),
                        elevation = 5.dp,
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.fillMaxSize()) {
                        Column() {
                            // Row of buttons here - and + buttons
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.End){
                                Button(onClick = {
                                    //decrease the no of items in cart list
                                    if (quantatityOfItems>0){
                                        quantatityOfItems--
                                    }
                                },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.White,
                                        contentColor = colorResource(id = R.color.text_color2)),
                                    shape = CircleShape,
                                    modifier = Modifier.size(50.dp)) {
                                    Icon(imageVector = Icons.Filled.Minimize,
                                        contentDescription ="reduce quantity" )
                                }
                                Text(text = quantatityOfItems.toString(),
                                    color = Color.Black,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(end = 8.dp, start = 8.dp))
                                Button(onClick = {
                                    // increase the number of cart items
                                        quantatityOfItems++
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White,
                                    contentColor = colorResource(id = R.color.text_color2)),
                                shape = CircleShape,
                                modifier = Modifier.size(50.dp)) {
                                    Icon(imageVector = Icons.Filled.Add,
                                        contentDescription ="reduce quantity" )
                                }
                            }
                            Text(text = "product",
                                modifier = Modifier.padding(16.dp))

                            Card(backgroundColor = Color.White,
                                elevation = 5.dp,
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier.fillMaxSize()) {
                                Column(verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally) {
                                    // creating the button
                                    Button(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(60.dp)
                                            .padding(start = 16.dp, end = 16.dp),
                                        onClick = {
                                            //add item to cart
                                            if (quantatityOfItems <= 0) {
                                                Toast.makeText(obj.applicationContext, "please specify quantity", Toast.LENGTH_LONG).show()
                                        }
                                            else{
                                                openDialog = true
                                                showProgress = true
                                                val item =
                                                    AddToCartRequest(id, proId, quantatityOfItems)
                                                viewModel.addToCart(userToken, item)
                                                viewModel.isAddToCartSuccess.observe(lifeCycleOwner) { response ->
                                                    if (response == true) {
                                                        openDialog = false
                                                        showProgress = false
                                                        //navigate to the cartlist screen
                                                        navController.navigate("cartListScreen")
                                                    } else {
                                                        openDialog = false
                                                    }
                                                }
                                            }
                                        },
                                        shape = RoundedCornerShape(20.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = colorResource(id = R.color.brand_Color),
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text(text = "Add to Cart")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}