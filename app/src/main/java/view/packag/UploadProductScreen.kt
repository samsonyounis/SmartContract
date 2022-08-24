package view.packag

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.packag.ReuableFunctions.arrowBackTopRow


@Composable
fun uploadProductscreen(navController: NavController) {
    val obj = LocalContext.current
    // instance of session Manager
    val sessionManager = SessionManager(obj)
    // variable to hold the user token
    var userToken:String by
    remember{ mutableStateOf(sessionManager.fetchAuthToken().toString()) }
    var proName by remember { mutableStateOf("") }
    var proID by remember { mutableStateOf("") }
    var proDescription by remember { mutableStateOf("") }
    var getproductDetails by remember { mutableStateOf(false) }
    var categoryID by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var proPrice by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column(verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)) {
                arrowBackTopRow(text = "sport", navController = navController)

            }
        }
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {

            Text(text = "Upload Product",
                style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Take picture of the product and provide more details of the" +
                    "product such as the price e.t.c")

            Spacer(modifier = Modifier.height(16.dp))
// a row of two texts here
            Row() {
                Text(text = "Take Photo",
                    modifier = Modifier.clickable {  },
                    textDecoration = TextDecoration.Underline)

                Spacer(modifier = Modifier.width(50.dp))

                Text(text = "Add product details",
                    modifier = Modifier.clickable {
                 // display the text fields for entering product details
                 getproductDetails = true
                    },
                    textDecoration = TextDecoration.Underline)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .border(
                        width = 1.dp, color = Color.LightGray,
                        shape = RoundedCornerShape(15.dp)
                    )) {
                // launch camera app and open take the picture
                // variable to hold the returned picture
                val result = remember { mutableStateOf<Bitmap?>(null) }
                val imageData = remember { mutableStateOf(null) }
                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.
                TakePicturePreview()) {
                    result.value = it
                }

                if (result.value == null){
                    Column(horizontalAlignment = Alignment.Start) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "null"
                        )
                    }
                    // camera icon here
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(100.dp)
                            .padding(16.dp)) {
                        Icon(imageVector = Icons.Filled.AddAPhoto,
                            contentDescription = "Camera",
                            Modifier.size(100.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        // launching the camera app
                        launcher.launch()
                    },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.brand_Color,),
                            contentColor = colorResource(id = R.color.black),
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(70.dp)
                            .padding(16.dp)) {
                        Text(text = "Take photo")

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    // button to select image from gallery
                    Button(onClick = {
                        // launching the camera app
                    },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.brand_Color,),
                            contentColor = colorResource(id = R.color.black),
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(70.dp)
                            .padding(16.dp)) {
                        Text(text = "Gallery")

                    }
                }
                else{
                        result.value?.let { image ->
                            Image(
                                image.asImageBitmap(),
                                null, modifier = Modifier
                                    .size(300.dp)
                                    .padding(16.dp)
                            )
                        }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (getproductDetails == true){

                    productDetailsTextFields(
                        valueText = proName, onValueChane = { proName = it },
                        label = "product name",
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                    productDetailsTextFields(
                        valueText = proID, onValueChane = { proID = it },
                        label = "product ID",
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    )
                    productDetailsTextFields(
                        valueText = proDescription, onValueChane = { proDescription = it },
                        label = "product description",
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                    productDetailsTextFields(
                        valueText = categoryID,
                        onValueChane = { categoryID = it },
                        label = "category ID",
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    )
                    productDetailsTextFields(
                        valueText = imageUrl,
                        onValueChane = { imageUrl = it },
                        label = "image URL",
                        keyboardType = KeyboardType.Uri, imeAction = ImeAction.Next
                    )
                    productDetailsTextFields(
                        valueText = proPrice,
                        onValueChane = { proPrice = it },
                        label = "product price",
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    )
                    Spacer(modifier = Modifier.height(16.dp))
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom) {
                Button(onClick = {
                    // navigate to previous screen
                    navController.navigateUp()
                },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = colorResource(id = R.color.brand_Color)
                    ),
                    modifier = Modifier
                        .width(90.dp)
                        .height(50.dp)) {
                    Text(text = "Back")
                }
                Spacer(modifier = Modifier.width(140.dp))
                Button(onClick = {
                  // add the product to the server/upload product and
                  // navigate to upload success screen
                 navController.navigate("uploadSuccessScreen")
                },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.brand_Color),
                        contentColor = colorResource(id = R.color.white)
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .width(90.dp)) {
                    Text(text = "Upload")
                }
            }
        }

    }

}
@Composable
fun productDetailsTextFields(valueText:String, onValueChane:(String)->Unit, label:String,
  keyboardType:KeyboardType, imeAction:ImeAction){
    OutlinedTextField(value = valueText, onValueChange = onValueChane,
    keyboardOptions = KeyboardOptions(
        keyboardType = keyboardType,
        imeAction = imeAction,
    ),
        label = { Text(text = label)},
       singleLine = false
    )
}