package view.packag

import android.content.Context
import android.content.SharedPreferences

    /* Session manager to save and fetch data from SharedPreferences */
    class SessionManager (context: Context) {
        private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        private var prefsAccountType: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        companion object {
            const val USER_TOKEN = "user_token"
            const val account_Type = "account_type"
        }

        /* Function to save auth token*/
        fun saveAuthToken(token: String) {
            val editor = prefs.edit()
            editor.putString(USER_TOKEN, token)
            editor.apply()
        }

        /* Function to fetch auth token */
        fun fetchAuthToken(): String? {
            return prefs.getString(USER_TOKEN, null)
        }
        /* Function to save the user selected account */
        fun saveAccountType(accountType:String){
            val editor = prefsAccountType.edit()
            editor.putString(account_Type, accountType)
            editor.apply()
        }

        /* Function to get the selected user account type */
        fun getAccountType():String?{
            return prefsAccountType.getString(account_Type, null)
        }
    }