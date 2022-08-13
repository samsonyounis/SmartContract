package view.packag

import ViewModel.BuyerHomeScreenViewModel
import ViewModel.BuyerHomeScreenViewModelFactory
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import model.ProductList
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun productScreen(navController: NavController, viewModel:BuyerHomeScreenViewModel,userToken:String) {
    //Function Local Variables
    val obj = LocalContext.current
    var query by remember { mutableStateOf("") }
    // variable to hold the user name of the user
    var userName by remember { mutableStateOf("samson osman") }
    // innitailizing the lifeCycle owner of this compose screen
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    // variable to hold list of specific products type
    var productList: List<ProductList> = remember { listOf() }
    var isProductsLoaded by remember { mutableStateOf(false) }

    // calling the getproductsList function defined in the view model
    viewModel.getProductList()
    // Observing the list of products from the view model
    viewModel.allProducts.observe(lifeCycleOwner) { res ->
        if (res != null) {
            productList = res
            isProductsLoaded = true
        } else {
            // logging the result in log cat
            Log.d("****", "onFailure: ")
            productList = listOf()
            isProductsLoaded = false
        }
    }
// Scaffold layout
    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        label = { Text("Search product") },
                        modifier = Modifier.width(190.dp),
                        placeholder = { Text(text = "Search product") },
                        shape = RoundedCornerShape(20.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "search product"
                            )
                        },
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                Toast.makeText(
                                    obj, "search performed",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        ),
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        shape = CircleShape,
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.home_button_bgcolor)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Shopp now"
                        )
                    }

                    Button(
                        onClick = { /*TODO*/ },
                        shape = CircleShape,
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.home_button_bgcolor)
                        )
                    ) {
                        BadgedBox(badge = { Badge { Text("8") } }) {
                            Icon(
                                Icons.Filled.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    backgroundColor = colorResource(id = R.color.brand2_color),
                    shape = RoundedCornerShape(20.dp,),
                    elevation = 5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Welcome back",
                            color = Color.White,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Spacer(modifier = Modifier.height((16.dp)))
                        Text(
                            text = userName,
                            color = Color.White,
                            fontSize = MaterialTheme.typography.h1.fontSize,
                            fontWeight = MaterialTheme.typography.h1.fontWeight,
                            modifier = Modifier.padding(bottom = 18.dp, start = 16.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor =
                colorResource(id = R.color.bottom_nav_color),
                content = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Home, contentDescription = "go to home")
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "go to favorites"
                            )
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                Icons.Filled.Message,
                                contentDescription = "check messages"
                            )
                        }
                        IconButton(onClick = {
                            // navigating to profile screen
                            navController.navigate("profileScreen")
                        }) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = "profile"
                            )
                        }
                    }
                }
            )
        }
    ) {
        if (isProductsLoaded == false) {
            // showing the progress
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Loading products")

                CircularProgressIndicator(
                    color = colorResource(id = R.color.brand_Color),
                    strokeWidth = ProgressIndicatorDefaults.StrokeWidth
                )
            }
        } else {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 120.dp),
                content = {
                    if (isProductsLoaded == true) {
                        for (product in productList) {
                            item() {
                                Card(
                                    shape = RoundedCornerShape(20.dp),
                                    modifier = Modifier
                                        .clickable {
                                            val imageurl =  URLEncoder.encode(product.imageUrl)
                                            val name = product.name
                                            val price = product.price
                                            val des = product.description
                                            val id = product.id
                                            val proId = product.categoryId
                                            // navigate to specific product detail screen
                                            Log.d("question",product.description)
                                            navController.navigate(
                                                "productDetailScreen/$name/$price/$des/$id/$proId/$userToken"
                                            )
                                        }
                                        .size(200.dp),
                                    backgroundColor = colorResource(id = R.color.bottom_nav_color),
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            painter = rememberImagePainter(data = product.imageUrl,
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
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Text(text = product.name)
                                    }
                                }
                            }
                        }
                    }
                },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(10.dp, bottom = 70.dp)
            )
        }
    }
}