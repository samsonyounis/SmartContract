package view.packag.ReuableFunctions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun userNameCard(userName:String){
    Card(
        backgroundColor = colorResource(id = view.packag.R.color.brand2_color),
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