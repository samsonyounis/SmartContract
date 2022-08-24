package view.packag

import ViewModel.CartListScreenViewModel
import android.util.Log
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import model.CartItemX
import view.packag.ReuableFunctions.arrowBackTopRow
import view.packag.ReuableFunctions.payCard

@Composable
fun payScreen(navController: NavController,noItems:Int, totalPrice:Int, viewModel: CartListScreenViewModel){

    val obj = LocalContext.current
    // instance of session Manager
    val sessionManager = SessionManager(obj)
    // variable to hold the user token
    var userToken:String by
    remember{ mutableStateOf(sessionManager.fetchAuthToken().toString()) }

    var itemsInCartList:List<CartItemX> = remember { listOf()}
        // innitailizing the lifeCycle owner of this compose screen
        val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    // calling the getproductsList function defined in the view model
    viewModel.getCartList(userToken)
    // Observing the list of products from the view model
    viewModel.cartItemsList.observe(lifeCycleOwner) { response ->
        if (response != null) {
            itemsInCartList = response
        } else {
            itemsInCartList = listOf()
        }
    }
    Scaffold(
        topBar = {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start) {
                // backward arrow
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "move back")
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
                        text = "$noItems items"
                    )
                }
            }
        },
        bottomBar = {
            BottomAppBar(backgroundColor = Color.White,
            modifier = Modifier.height(100.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                Column() {
                    Image(painter = painterResource(id = R.drawable.image), contentDescription ="image" )
                    Text(text = "Toatl:")
                    Text(text = totalPrice.toString())
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
                for (item in itemsInCartList) {
                    item {
                        Card(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(15.dp),
                            backgroundColor = colorResource(id = R.color.card_bgColor),
                            modifier = Modifier.padding(end = 16.dp)
                        ) {
                            Image(
                                painter = rememberImagePainter(data = item.product.imageUrl,
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
                    item {
                        payCard(logoImage = R.drawable.mastarcard_logo)
                    }
                item { 
                    payCard(logoImage = R.drawable.visa_logo)
                }
                item {
                    payCard(logoImage = R.drawable.paypal_logo)
                }
                item {
                    payCard(logoImage = R.drawable.googlepay_logo)
                }
            }
        }
    }
}