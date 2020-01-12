package com.ticket.utils

import android.content.Context
import com.ticket.utils.SharedPrefsHelper.Companion.PREF_KEY_ID
import com.ticket.utils.SharedPrefsHelper.Companion.PREF_KEY_LAUNCH
import com.ticket.utils.SharedPrefsHelper.Companion.PREF_KEY_NAME

fun getIsFirstLaunch(context: Context): Boolean{
    return context.getSharedPreferences(SharedPrefsHelper.PREF_KEY_USER, Context.MODE_PRIVATE)
        .getBoolean(PREF_KEY_LAUNCH, true)
}

fun setFirstLaunch(context: Context, isFirstLaunch: Boolean){
    context.getSharedPreferences(SharedPrefsHelper.PREF_KEY_USER, Context.MODE_PRIVATE)
        .edit()
        .putBoolean(PREF_KEY_LAUNCH, isFirstLaunch)
        .apply()
}

fun getUserName(context: Context): String? {
    return context.getSharedPreferences(SharedPrefsHelper.PREF_KEY_USER, Context.MODE_PRIVATE)
        .getString(PREF_KEY_NAME, "Друг")
}

fun setUserName(context: Context, name: String) {
    context.getSharedPreferences(SharedPrefsHelper.PREF_KEY_USER, Context.MODE_PRIVATE)
        .edit()
        .putString(PREF_KEY_NAME, name)
        .apply()
}

fun getUserId(context: Context): String? {
    return context.getSharedPreferences(SharedPrefsHelper.PREF_KEY_USER, Context.MODE_PRIVATE)
        .getString(PREF_KEY_ID, "id")
}

fun setUserId(context: Context, userId: String) {
    context.getSharedPreferences(SharedPrefsHelper.PREF_KEY_USER, Context.MODE_PRIVATE)
        .edit()
        .putString(PREF_KEY_ID, userId)
        .apply()
}

class SharedPrefsHelper{
    companion object {
        const val PREF_KEY_USER = "user_data"
        const val PREF_KEY_LAUNCH = "isFirstLaunch"
        const val PREF_KEY_NAME = "user_name"
        const val PREF_KEY_ID = "id_name"
    }
}