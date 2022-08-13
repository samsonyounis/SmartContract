package view.packag.ReuableFunctions

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.navigation.NavController
import model.LoginRequest
import view.packag.R

@Composable
fun arrowBackTopRow(text:String,navController: NavController){
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start) {
        // backward arrow
        IconButton(onClick = {navController.navigateUp()}) {
            Icon(imageVector = Icons.Filled.ArrowBack,
                contentDescription = "move back")
        }
        Spacer(modifier = Modifier.weight(3f))
        Text(text = text,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(5f))
    }
}

@Composable
fun commonButton(onClick: ()-> Unit, text:String, navController: NavController){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.brand_Color),
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }

}
@Composable
fun validateLogin(email:String, emailIsError:Boolean, password:String, passwordIsError:Boolean,
                showProgress:Boolean, selectedAccount:String,lifeCycleOwner:LifecycleOwner,
                  navController: NavController, errorMessage:String,){
    val obj = LocalContext.current
    

}
@Composable
fun commonOutlinedTextField(valueText:String, isError:Boolean, labelText:String, placeHolderText:String,
                      trailingIcon: ImageVector, iconDescription:String
                      , keyboardType:KeyboardType, imeAction: ImeAction, keyboardAction:KeyboardActions){
    var value by remember { mutableStateOf(valueText) }
    var isError by remember { mutableStateOf(isError) }
    var label by remember { mutableStateOf(labelText) }
    var placeholder by remember { mutableStateOf(placeHolderText) }
    var icon by remember { mutableStateOf(trailingIcon) }
    var description by remember { mutableStateOf(iconDescription) }
    var keyBoardType by remember { mutableStateOf(keyboardType) }
    var ime by remember { mutableStateOf(imeAction) }
    var keyBoardAction by remember { mutableStateOf(keyboardAction) }

    OutlinedTextField(value = value, onValueChange = {value = it},
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType,
            imeAction = ime
        ),
        keyboardActions = keyBoardAction,
    label = { Text(text = label)},
    placeholder = { Text(text = placeholder)},
    isError = isError,
    trailingIcon = {
        Icon(imageVector = icon, contentDescription = description)
    },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp))
}