package com.smkcoding.myapplication.startup

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Preferences {
    private const val KEY_USER_REGISTERED = "user"
    private const val KEY_PASS_REGISTERED = "pass"
    private const val KEY_USER_IS_LOGIN = "Username_logged_in"
    private const val KEY_STATUS_IS_LOGIN = "Status_logged_in"

    private fun getSharedPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setRegisterUser(context: Context, username: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_USER_REGISTERED, username)

        editor.apply()
    }

    fun getRegisterdUser(context: Context): String? {
        return getSharedPreference(context)
            .getString(KEY_USER_REGISTERED, "")
    }

    fun setRegisteredPass(context: Context, password: String?) {
        val editor =
            getSharedPreference(context).edit()
        editor.putString(
            KEY_PASS_REGISTERED,
            password
        )
        editor.apply()
    }

    fun getRegisteredPass(context: Context): String? {
        return getSharedPreference(context)
            .getString(KEY_PASS_REGISTERED, "")
    }

    fun setLoggedInUser(context: Context, status: Boolean): Boolean {
        return getSharedPreference(context)
            .getBoolean(KEY_STATUS_IS_LOGIN, false)
    }

    fun clearLoggedInUser(context: Context) {
        val editor = getSharedPreference(context).edit()
        editor.remove(KEY_USER_IS_LOGIN)
        editor.remove(KEY_STATUS_IS_LOGIN)
        editor.apply()
    }
}