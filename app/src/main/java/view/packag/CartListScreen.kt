package view.packag

import ViewModel.CartListScreenViewModel
import ViewModel.CartListScreenViewModelFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import model.CartItem
import model.CartItemX
import model.ProductList
import repository.Repository

@Composable
fun cartListScreen(navController: NavController, userToken:String, viewModel: CartListScreenViewModel) {
    //Function variables
    var openDialog by remember { mutableStateOf(false) }
    var noItems by remember { mutableStateOf(0) }
    var totalPrice by remember { mutableStateOf(0) }
    var itemsInCartList:List<CartItemX> = remember { listOf() }
    // innitailizing the lifeCycle owner of this compose screen
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var isCartItemsLoaded by remember { mutableStateOf(false) }
    // calling the getproductsList function defined in the view model
    viewModel.getCartList(userToken)
    // Observing the list of products from the view model
    viewModel.cartItemsList.observe(lifeCycleOwner) { response ->
        if (response != null) {
            itemsInCartList = response
            isCartItemsLoaded = true
        } else {
            // logging the result in log cat
            Log.d("****", "onFailure: ")
            itemsInCartList = listOf()
            isCartItemsLoaded = false
        }
    }
    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = {
                        // navigating the back stack
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "go back"
                        )
                    }
                    Spacer(modifier = Modifier.weight(3f))
                    Column(verticalArrangement = Arrangement.SpaceEvenly,
                       modifier = Modifier.weight(5f)) {
                        Text(
                            text = "Your Chart",
                            style = MaterialTheme.typography.h1
                        )
                        Text(text = "N items")
                    }
                }
                Text(text = "All Items")
            }
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "Total:")
                    Text(
                        text = "$ 300.50",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = { openDialog = true },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.brand_Color),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Check Out")
                }
            }
        }
    ) {
        //showing the circular progress while loading the cart items
        if (isCartItemsLoaded == false) {
            // showing the progress
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Loading Cart items")

                CircularProgressIndicator(
                    color = colorResource(id = R.color.brand_Color),
                    strokeWidth = ProgressIndicatorDefaults.StrokeWidth
                )
            }
        }
        else {
        // Show the dialog box only when pay Now button is clicked
        if (openDialog == true) {
            AlertDialog(
                modifier = Modifier.height(500.dp),
                onDismissRequest = {},
                title = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Sign contract with",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Service provider",
                            color = colorResource(id = R.color.brand_Color),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                },
                text = {
                    Text(
                        text = "signing the contract is legally binding between the" +
                                "buyer and the seller upon purchasing products using our" +
                                "platform\n\n by clicking sign contract, you confirm the following\n\n" +
                                "Rule1: signing the contract is legally binding\n" +
                                "Rule2: signing the contract is legally binding\n" +
                                "Rule3: signing the contract is legally binding\n " +
                                "Rule4: signing the contract is legally binding"
                    )
                },
                buttons = {
                    Row(
                        modifier = Modifier
                            .padding(all = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { openDialog = false },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = colorResource(id = R.color.brand_Color)
                            )
                        ) {
                            Text("Cancel")
                        }

                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                      // finish the contract backend process and
                                      // navigate to the success screen
                                      navController.navigate("contractSignSuccessScreen")
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id = R.color.brand_Color),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Sign contract")
                        }
                    }
                }
            )
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(bottom = 50.dp)
        ) {
            for (i in itemsInCartList) {

                Row(modifier = Modifier.padding(16.dp)) {
                    Card(elevation = 4.dp,
                    backgroundColor = colorResource(id = R.color.card_bgColor),
                    shape = RoundedCornerShape(15.dp)) {
                        Image(
                            painter = rememberImagePainter(data = i.product.imageUrl,
                                builder = {
                                    crossfade(1000)
                                    placeholder(R.drawable.loading_images)
                                    error(R.drawable.errorloading_image)
                                }),
                            contentDescription = "item",
                            modifier = Modifier.size(150.dp)
                        )
                    }
                    Column(verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp)) {
                        Text(
                            text = i.product.name,
                            color = Color.Black
                        )
                        Text(
                            text = i.product.price.toString() + "X" +i.quantity.toString(),
                            color = Color.Red
                        )
                    }
                }

            }
        }
    }
    }

}