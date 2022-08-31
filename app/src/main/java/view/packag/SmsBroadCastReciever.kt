package view.packag

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SmsBroadCastReciever : BroadcastReceiver() {
    //creatinfg instance of smsBroadcastRecieverListener Interface
    var smsBroadcastRecieverListener:SmsBroadcastRecieverListener? = null
    //implementing the member function of the Broadcast class
    override fun onReceive(context: Context, intent: Intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action){

            val extras = intent.extras
            val smsRetriverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when(smsRetriverStatus.statusCode){
                CommonStatusCodes.SUCCESS->{
                    val messageIntent = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                    smsBroadcastRecieverListener?.onSuccess(messageIntent)

                }

                CommonStatusCodes.TIMEOUT->{
                    smsBroadcastRecieverListener?.onFailure()
                }
            }
        }
    }
    //Creating the interface with two methods
    interface SmsBroadcastRecieverListener{
        fun onSuccess(intent: Intent?)
        fun onFailure()
    }
}