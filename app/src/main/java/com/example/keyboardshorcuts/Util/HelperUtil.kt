package com.example.keyboardshorcuts.Util

import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun TextView.typeWrite(text: String, intervalMs: Long) {
    this@typeWrite.text = ""

    repeat(text.length) {
        GlobalScope.launch {
            delay(intervalMs)
            this@typeWrite.text = text.take(it + 1)
        }
    }
}