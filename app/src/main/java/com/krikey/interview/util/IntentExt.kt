package com.krikey.interview.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

fun Intent.maybeOpen(context: Context, errorMessage: String, bundle: Bundle? = null) {
    if (resolveActivity(context.packageManager) != null) {
        startActivity(context, this, bundle)
    } else {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
}