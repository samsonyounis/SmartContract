package view.packag

import ViewModel.BuyerHomeScreenViewModel

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.lifecycle.*
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import model.CategoryList
import model.ProductList
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun buyerHomeScreen(navController: NavController,viewModel:BuyerHomeScreenViewModel, userToken:String) {
    //Function Local Variables
    var query by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("User Name") }
    // innitailizing the lifeCycle owner of this compose screen
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    // variables to hold list of the products and list of returned Catrgories
    var productList:List<ProductList> = remember { listOf() }
    var categoryList:List<CategoryList> = remember { listOf() }

    // state variables to determine if category list and the products have been loaded from the server
    var isCategoryLoaded by remember { mutableStateOf(false) }
    var isProductsLoaded by remember { mutableStateOf(false) }

    // calling the getproductsList and the getCategoryList functions defined in the view model
    viewModel.getProductList()
    viewModel.getCategoryList()

    // observing the the list of Categories variable from the view model
    viewModel.allCategories.observe(lifeCycleOwner) { catResponse ->
        if (catResponse != null) {
            categoryList = catResponse
            isCategoryLoaded = true
        } else {
            // logging the result in log cat
            Log.d("****", "onFailure: ")
            categoryList = listOf()
            isCategoryLoaded = false
        }
    }
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
    // Creating instance of event lifecyle owner
    DisposableEffect(lifeCycleOwner){
        val observer = LifecycleEventObserver{source, event ->
            if (event == Lifecycle.Event.ON_RESUME){
                // do something here
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
// Scaffold layout
    Scaffold (
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
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
                                //search the products list
                            }),
                    )

                    Button(
                        onClick = { /*TODO*/ },
                        shape = CircleShape,
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =
                            colorResource(id = R.color.home_button_bgcolor)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Shopp now"
                        )
                    }

                    Button(
                        onClick = {
                                  // navigating to notifications screen
                                  navController.navigate("notificationScreen")
                        },
                        shape = CircleShape,
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =
                            colorResource(id = R.color.home_button_bgcolor)
                        )
                    ) {
                        BadgedBox(badge = { Badge { Text("3") } }) {
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
                        .height(100.dp)
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Welcome back",
                            color = Color.White,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Spacer(modifier = Modifier.height((16.dp)))
                        Text(
                            text = userName, // user name of the user
                            color = Color.White,
                            fontSize = MaterialTheme.typography.h1.fontSize,
                            fontWeight = MaterialTheme.typography.h1.fontWeight,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = colorResource(id = R.color.bottom_nav_color),
                elevation = 15.dp
            ) {
                BottomNavigationItem(selected = false, onClick = { /*TODO*/ },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                  selectedContentColor = colorResource(id = R.color.brand_Color))

                BottomNavigationItem(selected = false, onClick = {
                             //navigating to favorites screen
                             navController.navigate("favoritesScreen")
                },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") })

                BottomNavigationItem(selected = false, onClick = { /*TODO*/ },
                    icon = { Icon(Icons.Filled.Message, contentDescription = "Messages") },
                    selectedContentColor = colorResource(id = R.color.brand_Color)
                )

                BottomNavigationItem(selected = false, onClick = {
                                // navigating to profile
                                navController.navigate("profileScreen")
                },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "profile") })

            }

        }
    ){
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row(horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            ) {
                Text(text = "Category")
                Spacer(modifier = Modifier.weight(2f))
                Text(text = "See All",
                    modifier = Modifier.clickable {  })
            }

            if (isCategoryLoaded == true) {
                LazyRow {
                    for (item in categoryList) {
                        item {
                            Button(
                                onClick = {
                                    //navigating to products Screen
                                    navController.navigate("productsScreen/$userToken")
                                    Log.d("userToken", userToken)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.LightGray,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier.padding(10.dp),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Text(text = item.categoryName)
                            }
                        }
                    }
                }
            }
            //special product images goes here
            Text(
                text = "Special for you",
                style = MaterialTheme.typography.h1)
            //if (productsAvailable == true) {}
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                LazyRow() {
                    for (item in productList) {
                        item {
                            Card(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(150.dp),
                                backgroundColor = colorResource(id = R.color.card_bgColor)
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = item.imageUrl,
                                        builder = {
                                            crossfade(1000)
                                            placeholder(R.drawable.loading_images)
                                            error(R.drawable.errorloading_image)
                                        }),
                                    contentDescription = "Product",
                                    modifier = Modifier.fillMaxSize()
                                )
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(text = item.name,
                                        color = Color.White)
                                    Text(text = "Price${item.price}",
                                        color = Color.White)
                                }
                            }
                        }
                    }
                }

            // Popular product images goes here
            Text(text = "Popular Products",
                style = MaterialTheme.typography.h1)
            if (isProductsLoaded == true) {
                LazyRow(horizontalArrangement = Arrangement.SpaceBetween) {
                    for (item in productList) {
                        item {
                            Card(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(140.dp),
                                backgroundColor = Color.LightGray
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = item.imageUrl,
                                        builder = {
                                            crossfade(1000)
                                            placeholder(R.drawable.loading_images)
                                            error(R.drawable.errorloading_image)
                                        }),
                                    contentDescription = "Product"
                                )
                            }
                        }
                    }
                }
            }
            }
        }
    }
}