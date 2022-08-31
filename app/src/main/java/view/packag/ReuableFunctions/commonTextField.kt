package view.packag.ReuableFunctions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun commonOutlinedTextField(valueText:String, onValueChange:(String)->Unit,
                            isError:Boolean, labelText:String, placeholderText:String,
                            trailingIcon: ImageVector, iconDescription:String
                            , keyboardType: KeyboardType, imeAction:ImeAction,
                            visualTransFormation:VisualTransformation
){

    OutlinedTextField(value = valueText, onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        label = { Text(text = labelText) },
        placeholder = { Text(text = placeholderText) },
        isError = isError,
        trailingIcon = {
            Icon(imageVector = trailingIcon, contentDescription = iconDescription)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        visualTransformation = visualTransFormation
    )
}