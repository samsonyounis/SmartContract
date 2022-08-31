package view.packag

import ViewModel.OtpScreenViewModel
import ViewModel.OtpScreenViewModelFactory
import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.chaos.view.PinView
import com.google.android.gms.auth.api.phone.SmsRetriever
import repository.Repository
import java.util.regex.Pattern

class OTPScreenActivity : AppCompatActivity() {
    // Instance of repository
    val repository by lazy { Repository() }
    // instance of loginScreen ViewModelFactory
    val otpScreenViewModelFactory by lazy { OtpScreenViewModelFactory(repository) }
    val otpScreenViewModel by lazy { ViewModelProvider(this,otpScreenViewModelFactory).get(
        OtpScreenViewModel::class.java)
    }
    private lateinit var navController: NavHostController
    private val REQ_USER_CONSENT by lazy { 20}
    // creating instance of Broadcast reciever
    var smsBroadcastReciever:SmsBroadCastReciever? = null
    // creating the varible to hold the Otp Code
    private  var otpCode = ""
     private lateinit var pinView: PinView;
    lateinit var continueButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = NavHostController(this)
        setContentView(R.layout.activity_otpscreen)
        continueButton = findViewById(R.id.Verify_button)
        pinView = findViewById(R.id.pinview)
        startSmartUserConsent()
        continueButton.setOnClickListener {
            val code = pinView.text.toString()
            // send the code back to the server
            otpScreenViewModel.sendBackOtp(code)
            otpScreenViewModel.otpResponse.observe(this){ serverResponse->
                Toast.makeText(applicationContext,serverResponse,Toast.LENGTH_LONG).show()
            }
        }

    }
    // function to start listening to the incoming message containg OTP from server
    private fun startSmartUserConsent(){
        val client = SmsRetriever.getClient(this)
        client.startSmsUserConsent(null).addOnSuccessListener {
            Log.d("Result" ,"listening success")
        }.addOnFailureListener {
            Log.d("Result","Listening failed")
        }
    }
    //function to register the Broadcasst reciever in the main activity.
    private fun registerBroadCastReciever(){
        //assigning the SmsBroadCastReciever object to the smsBroadcastReciever var
        smsBroadcastReciever = SmsBroadCastReciever()
        smsBroadcastReciever!!.smsBroadcastRecieverListener =
            object : SmsBroadCastReciever.SmsBroadcastRecieverListener{
                override fun onSuccess(intent: Intent?) {
                    startActivityForResult(intent,REQ_USER_CONSENT)
                }

                override fun onFailure() {
                    // through toeast or restart listening
                    Toast.makeText(applicationContext, "Code Not send",Toast.LENGTH_LONG).show()
                }
            }
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsBroadcastReciever, intentFilter)
    }
    override fun onStart() {
        super.onStart()
        registerBroadCastReciever()
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(smsBroadcastReciever)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_USER_CONSENT -> {
                if ((resultCode == Activity.RESULT_OK) && (data != null)) {
                    //That gives all message to us. We need to get the code from inside with regex
                    val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                    getOtpCodeFromMessage(message)
                }
            }
        }
    }
    //Function to extract the Otp code from the incoming message
    private fun getOtpCodeFromMessage(message:String?){
        val otpPattern = Pattern.compile("[0-9]{4}")
        val matcher = otpPattern.matcher(message)
        if(matcher.find()){
            otpCode = matcher.group(0).toString()
            var i:Int = 0
            pinView.setText(otpCode)
        }
        else{
            otpCode = "No code format found in SMS"
            pinView.setText(otpCode)
        }
    }
}