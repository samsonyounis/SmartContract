package view.packag

import ViewModel.BuyerHomeScreenViewModel
import ViewModel.BuyerHomeScreenViewModelFactory
import ViewModel.SellerHomeScreenViewModel
import ViewModel.SellerHomeScreenViewModelFactory
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.CategoryList
import model.ProductList
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun sellerHomeScreen(navController: NavController,userToken:String){
//Function Local Variables
    val obj = LocalContext.current
    var query by remember { mutableStateOf("") }
    // variable to hold list of product categores
    var categoryList:List<CategoryList> = listOf()
    // variable to hold list of product categores
    var productList:List<ProductList> = listOf()
    var categoryAvailable by remember { mutableStateOf("") }
    var productsAvailable by remember { mutableStateOf(false) }
    var specialAvailable by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("samson osman") }
    // Instance of repository
    val repository by lazy { Repository() }
    // Instance of viewmodel Factory
    val viewModelFactory by lazy { SellerHomeScreenViewModelFactory(repository) }
    // instance of view Model
    val viewModel = SellerHomeScreenViewModel(repository)
    // Creating instance of event lifecyle owner
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
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

    // Accessing the loginRequest function from viewModel using the ViewModel instance
    viewModel.getCategoryList().enqueue(object : Callback<List<CategoryList>> {
        // this method will be executed if you have recieved Http response
        // Your status code will decide if the Http response is success or error
        override fun onResponse(
            call: Call<List<CategoryList>>,
            response: Response<List<CategoryList>>
        ) {
            //here your reponse  code is in the range of 200 to 299
            // more else block can be used here to determine different actions
            // for different codes in this range(200 - 299)
            if (response.isSuccessful){
                // create a variable to hold the list of product categories
                categoryList = response.body()!!
                categoryAvailable = "true"
                // Display the categoryList in lazyRow using for loop
            }
            //here https status code is in the range of 300s, 400s and 500s
            // apllication level failure.
            // you can use different codes in this ranges to determine and display
            // different messages to the user.
            else{
                categoryAvailable = "false"
            }

        }

        override fun onFailure(call: Call<List<CategoryList>>, t: Throwable) {
            categoryAvailable = "false"
        }

    })
    // Accessing the loginRequest function from viewModel using the ViewModel instance
    /*
        viewModel.getProductList().enqueue(object : Callback<List<ProductList>> {
            // this method will be executed if you have recieved Http response
            // Your status code will decide if the Http response is success or error
            override fun onResponse(
                call: Call<List<ProductList>>,
                response: Response<List<ProductList>>
            ) {
                //here your reponse  code is in the range of 200 to 299
                // more else block can be used here to determine different actions
                // for different codes in this range(200 - 299)
                if (response.isSuccessful) {
                    // create a variable to hold the list of product categories
                    productList = response.body()!!
                    productsAvailable = true
                    // Display the categoryList in lazyRow using for loop
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    productsAvailable = false
                }
            }

            override fun onFailure(call: Call<List<ProductList>>, t: Throwable) {
                productsAvailable = false

            }
        })
    */
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
                            backgroundColor = colorResource(id = R.color.home_button_bgcolor)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications")
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
                            text = userName,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
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

                BottomNavigationItem(selected = false, onClick = { /*TODO*/ },
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
            /*
            BottomAppBar(backgroundColor = colorResource(id = R.color.bottom_nav_color),
                cutoutShape = RoundedCornerShape(15.dp),
                content = {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()) {


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
                            //navigating to the profile screen
                            navController.navigate("profileScreen")
                        }) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = "profile"
                            )
                        }
                    }
                })
            */
        }
    ){
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row(horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            ) {
                Text(text = "post by category")
                Spacer(modifier = Modifier.weight(2f))
                Text(text = "See All",
                    modifier = Modifier.clickable {  })
            }


            if (categoryAvailable == "true") {
                LazyRow {
                    for (item in categoryList) {
                        item {
                            Button(
                                onClick = {
                                    // navigating to products screen
                                    navController.navigate("productsScreen")
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

            Text(
                text = "Recent posted by you",
                style = MaterialTheme.typography.h1)
            if (productsAvailable == true) {
                LazyRow {
                    for (item in productList) {
                        item {
                            Card(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .height(140.dp)
                                    .width(250.dp)
                                    .clickable { },
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
            }

            Text(
                text = "Popular Products",
                style = MaterialTheme.typography.h1
            )
            if (productsAvailable == true){
                LazyRow {
                    for (item in productList) {
                        item {
                            Card(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier.padding(10.dp).size(120.dp),
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