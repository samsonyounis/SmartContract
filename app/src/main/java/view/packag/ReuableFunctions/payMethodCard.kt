package view.packag.ReuableFunctions

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import view.packag.R

@Composable
fun payCard(logoImage:Int){
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = colorResource(id = R.color.card_bgColor),
        modifier = Modifier
            .padding(10.dp)
            .clickable { }
    ) {
        
        Image(
            painter = painterResource(id = logoImage),
            contentDescription = "Product",
            modifier = Modifier.size(100.dp)
        )
    }
}