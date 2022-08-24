package view.packag.ReuableFunctions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import view.packag.R

@Composable
fun productsInRow(imageUrl:String,proName:String,proPrice:Int){
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(10.dp)
            .size(150.dp),
        backgroundColor = colorResource(id = R.color.card_bgColor)
    ) {
        Image(
            painter = rememberImagePainter(data = imageUrl,
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
            Text(text = proName,
                color = Color.White)
            Text(text = "Price${proPrice}",
                color = Color.White)
        }
    }
}

@Composable
fun productImagesInRow(imageUrl: String){
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(10.dp)
            .size(140.dp),
        backgroundColor = Color.LightGray
    ) {
        Image(
            painter = rememberImagePainter(data = imageUrl,
                builder = {
                    crossfade(1000)
                    placeholder(R.drawable.loading_images)
                    error(R.drawable.errorloading_image)
                }),
            contentDescription = "Product"
        )
    }
}