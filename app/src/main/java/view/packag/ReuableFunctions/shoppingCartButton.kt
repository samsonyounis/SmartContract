package view.packag.ReuableFunctions

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.R

@Composable
fun shoppingCartButton(onClick:()->Unit){
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = Modifier.size(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.home_button_bgcolor)
        )
    ) {
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Shopp now"
        )
    }
}