package com.ticket.base

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.ticket.R

abstract class BaseActivity: AppCompatActivity() {
    fun showMessage(messageText: String) {
        findViewById<View>(android.R.id.content)?.let {
            Snackbar.make(it, messageText, Snackbar.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, messageText, Toast.LENGTH_SHORT).show()
    }
}