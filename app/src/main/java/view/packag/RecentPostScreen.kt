package view.packag

import ViewModel.RecentPostScreenViewModel
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import model.CartItemX
import model.ProductList
import view.packag.ReuableFunctions.bottomNavigation

@Composable
fun recentPostScreen(navController: NavController,viewModel:RecentPostScreenViewModel){
    //Function variables
    var recentItemsList:List<ProductList> = remember { listOf() }
    var noItems by remember { mutableStateOf(0) }
    // innitailizing the lifeCycle owner of this compose screen
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var isRecentItemsLoaded by remember { mutableStateOf(false) }
    viewModel.getRecentPostedItems()
    viewModel.allRecentProducts.observe(lifeCycleOwner){response->
        if (response != null) {
            recentItemsList = response
            isRecentItemsLoaded = true
            noItems = response.size
        } else {
            recentItemsList = listOf()
            isRecentItemsLoaded = false
        }

    }
    Scaffold(topBar = {
        Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)) {
            IconButton(onClick = {
                // navigating the back stack
                navController.navigateUp()
            },
            modifier = Modifier.align(Alignment.Start)) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "go back"
                )
            }
            Text(
                text = "Recently posted by you",
                style = MaterialTheme.typography.h1
            )
            Text(text = "$noItems items")
        }
    },
    bottomBar = {
        //implementing the bottom navigation here
        bottomNavigation(navController = navController, currentScreen = "recentPostScreen")
    }
        ) {

        Column(modifier = Modifier
            .padding(16.dp, bottom = 58.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())) {
            if (isRecentItemsLoaded == false) {
                // showing the progress
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Loading recent products")

                    CircularProgressIndicator(
                        color = colorResource(id = R.color.brand_Color),
                        strokeWidth = ProgressIndicatorDefaults.StrokeWidth
                    )
                }
            } else {

                for (i in recentItemsList) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Card(elevation = 4.dp,
                            backgroundColor = colorResource(id = R.color.card_bgColor),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Image(
                                painter = rememberImagePainter(data = i.imageUrl,
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
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 8.dp)) {
                            Text(
                                text = i.name,
                                color = Color.Black
                            )
                            Text(
                                text = i.price.toString(),
                                color = Color.Red
                            )
                        }
                    }
                }

            }
        }
    }
}