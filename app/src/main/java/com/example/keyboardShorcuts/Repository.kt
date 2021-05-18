package com.example.keyboardShorcuts

import android.content.Context
import com.example.keyboardShorcuts.retrofit.RetrofitHelper
import com.example.keyboardShorcuts.room.RoomDatabaseHelper

object Repository {
    lateinit var roomDatabaseHelper: RoomDatabaseHelper
    lateinit var retrofitHelper: RetrofitHelper

    fun plan(context: Context) {
        roomDatabaseHelper = RoomDatabaseHelper.invoke(context)
        retrofitHelper = RetrofitHelper.invoke()
    }
}