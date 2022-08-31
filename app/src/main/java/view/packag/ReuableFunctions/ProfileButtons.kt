package view.packag.ReuableFunctions

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import view.packag.R

@Composable
fun profileButton(icon:ImageVector,iconDescription:String, text:String,
  onclick:()->Unit){
    Button(onClick = onclick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.profile_button_colors)
        ),
        modifier = Modifier
            .width(300.dp)
            .height(60.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription
        )
        Text(text = text)
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "go to $iconDescription"
        )
    }
}