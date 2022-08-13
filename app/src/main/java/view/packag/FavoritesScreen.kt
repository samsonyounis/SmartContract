package view.packag

import ViewModel.BuyerHomeScreenViewModel
import ViewModel.ProductsActivityViewModel
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import model.ProductList
import view.packag.ReuableFunctions.arrowBackTopRow
import java.net.URLEncoder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun favoriteScreen(navController: NavController, viewModel:BuyerHomeScreenViewModel){
    // function local variables
    // innitailizing the lifeCycle owner of this compose screen
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    // variable to hold list of specific products type
    var favoritesList: List<ProductList> = remember { listOf() }
    var isFavoritesLoaded by remember { mutableStateOf(false) }

    // calling the getproductsList function defined in the view model
    viewModel.getProductList()
    // Observing the list of products from the view model
    viewModel.allProducts.observe(lifeCycleOwner) { res ->
        if (res != null) {
            favoritesList = res
            isFavoritesLoaded = true
        } else {
            // logging the result in log cat
            Log.d("****", "onFailure: ")
            favoritesList = listOf()
            isFavoritesLoaded = false
        }
    }
    //Scaffold
    Scaffold(
        topBar = {
                 arrowBackTopRow(text = "Favorites", navController = navController)
        },
    bottomBar = {
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.bottom_nav_color),
            elevation = 15.dp
        ) {
            BottomNavigationItem(selected = false, onClick = {
                            // navigating to home screen
            },
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                selectedContentColor = colorResource(id = R.color.brand_Color)
            )

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
    }) {
        if (isFavoritesLoaded == false) {
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
                    if (isFavoritesLoaded == true) {
                        for (product in favoritesList) {
                            item() {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Image(
                                        painter = rememberImagePainter(data = product.imageUrl,
                                            builder = {
                                                crossfade(1000)
                                                placeholder(R.drawable.loading_images)
                                                error(R.drawable.errorloading_image)
                                            }),
                                        contentDescription = "Favorite",
                                        Modifier.background(
                                            color = colorResource(id = R.color.home_button_bgcolor),
                                            shape = RoundedCornerShape(15.dp)
                                        ).size(150.dp)
                                    )
                                    Text(text = product.name,
                                    color = Color.Black)
                                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()) {
                                        Text(text = "$" + product.price,
                                        color = Color.Red,
                                        modifier = Modifier.align(Alignment.CenterVertically))

                                        Button(onClick = { /*TODO*/ },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = colorResource(id = R.color.favoriteButton_bg),
                                            contentColor = Color.Red
                                        ),
                                        shape = CircleShape,
                                        modifier = Modifier.size(50.dp)) {
                                            Icon(imageVector = Icons.Filled.Favorite,
                                                contentDescription ="Favorites" )
                                        }
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