package view.packag

import ViewModel.SellerHomeScreenViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.navigation.NavController
import model.CategoryList
import model.ProductList
import view.packag.ReuableFunctions.*

@Composable
fun sellerHomeScreen(navController: NavController, viewModel: SellerHomeScreenViewModel){
//Function Local Variables
    var query by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("samson osman") }
    // instance of life cycle owner.
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    // variables to hold list of products and product categories.
    var categoryList:List<CategoryList> = remember{listOf()}
    var productList:List<ProductList> = remember { listOf()}

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
            productList = listOf()
            isProductsLoaded = false
        }
    }
    DisposableEffect(lifeCycleOwner){
        val observer = LifecycleEventObserver{source, event ->
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
                    // search field here
                    searchTextField(navController = navController, valueText = query,
                        onValueChange = {query = it}, onSerach = { })
                    // notification badge button
                    notificationBadge(navController = navController, noNotifications = "5")
                }
                Spacer(modifier = Modifier.height(16.dp))
                // implmenting the card with the welcome message and the user name
                userNameCard(userName = userName)
            }
        },
        bottomBar = {
            // implementing the bottom navigation here.
            bottomNavigation(navController = navController, "seller")
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

            if (isCategoryLoaded == true) {
                LazyRow {
                    for (item in categoryList) {
                        item {
                            categoryButton(navController = navController, text = item.categoryName,
                                onClick = {
                                    // navigating to products screen
                                    navController.navigate("productsByCategoryScreen")
                                },)
                        }
                    }
                }
            }

            Text(
                text = "Recent posted by you",
                style = MaterialTheme.typography.h1)
            if (isProductsLoaded == true) {
                LazyRow {
                    for (item in productList) {
                        item {
                            // implementing a scrollable row of recent posted products
                            productsInRow(imageUrl = item.imageUrl, proName = item.name, proPrice =item.price )
                        }
                    }
                }
            }

            Text(
                text = "Popular Products",
                style = MaterialTheme.typography.h1
            )
            if (isProductsLoaded == true){
                LazyRow {
                    for (item in productList) {
                        item {
                            // implementing the images of popular products in a row
                            productImagesInRow(imageUrl = item.imageUrl)
                        }
                    }
                }
            }
        }
    }
}