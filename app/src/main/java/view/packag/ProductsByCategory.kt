package view.packag

import ViewModel.SellerHomeScreenViewModel
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import model.ProductList
import view.packag.ReuableFunctions.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun productsByCategoryScreen(navController: NavController, viewModel: SellerHomeScreenViewModel) {
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
                    // implementing search field
                    searchTextField(navController = navController, valueText = query, onValueChange ={query = it} ) {
                        Toast.makeText(obj, "search performed", Toast.LENGTH_LONG).show()
                    }
                    //implementing the shopping cart button
                    shoppingCartButton(onClick = {/*TODO*/})

                    // implementing the notification badge counter
                    notificationBadge(navController = navController, noNotifications = "6")
                }
                Spacer(modifier = Modifier.height(16.dp))

                // implmenting the card with the welcome message and the user name
                userNameCard(userName = userName)
            }
        },
        bottomBar = {
            //implementing the bottom navigation here
            bottomNavigation(navController = navController, currentScreen = "productsByCategoryScreen")
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
                                            // navigating to the upload product screen
                                            navController.navigate("uploadProductScreen")
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