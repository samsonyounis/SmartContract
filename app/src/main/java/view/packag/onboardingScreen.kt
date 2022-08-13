package view.packag

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun singlePage(page: Page){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = page.title,
        fontSize = 28.sp, fontWeight = FontWeight.Bold,
            color = Color(255, 127, 80)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = page.subtitle, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Image(painter = painterResource(id = page.image), contentDescription =null,
            modifier = Modifier.fillMaxSize())

    }
}