package view.packag

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import view.packag.ReuableFunctions.commonButton

data class Page(val title:String, val subtitle:String, @DrawableRes val image:Int)

    val onboardPages = listOf(
        Page("Smart Contracts",
            "Welcome to Smart Contract, Let's Shop!",
            R.drawable.brand1),
        Page("Smart Contract",
            "We help people connect with trusted \n\nsuppliers around Your state",
            R.drawable.brand2),
        Page("Smart Contract",
            "we show easy way to shop\n\nJust stay at Home",
            R.drawable.brand3)
    )

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun onboardingUi(navController: NavController){
        val pagerState = rememberPagerState(pageCount = 3)
        Column {

            HorizontalPager(state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)){
                    page ->
                singlePage(page = onboardPages[page])
            }
            HorizontalPagerIndicator(pagerState = pagerState, modifier = Modifier
                .align
                    (Alignment.CenterHorizontally)
                .padding(16.dp), activeColor = Color.Blue)

            AnimatedVisibility(visible = pagerState.currentPage==2) {
                commonButton(onClick = {

                    navController.popBackStack()
                    navController.navigate("select_Account")
                }, text = "continue", navController =navController )

            }
        }
    }


