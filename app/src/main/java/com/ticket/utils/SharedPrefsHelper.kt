package com.ticket.utils

import android.content.Context

class SharedPrefsHelper(context: Context) {

    private val launchPreference = "Launch"
    private val launchPreferenceKey = "isFirstLaunch"
    private val usernamePreference = "Username"
    private val usernamePreferenceKey = "SaveUserName"

    private val launch = context.getSharedPreferences(launchPreference, Context.MODE_PRIVATE)
    private val username = context.getSharedPreferences(launchPreference, Context.MODE_PRIVATE)

    fun getFirstLaunchValue(): Boolean {
        return launch.getBoolean(launchPreferenceKey, false)
    }

    fun setLaunchValue(value: Boolean) {
        val editor = launch.edit()
        editor.putBoolean(launchPreferenceKey, value)
        editor.apply()
    }

    fun getUserName(): String? {
        return username.getString(usernamePreferenceKey, "Друг")
    }

    fun setUserName(name: String){
        val editor = username.edit()
        editor.putString(usernamePreference, name)
        editor.apply()
    }
}