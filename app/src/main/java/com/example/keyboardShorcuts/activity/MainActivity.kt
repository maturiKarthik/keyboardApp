package com.example.keyboardShorcuts.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.keyboardShorcuts.R
import com.example.keyboardShorcuts.Repository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Repository.plan(applicationContext)
    }


}