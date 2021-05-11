package com.example.keyboardshorcuts

import android.content.Context
import com.example.keyboardshorcuts.room.RoomDatabaseHelper

object Repository {
    lateinit var roomDatabaseHelper: RoomDatabaseHelper

    fun plan(context: Context){
        roomDatabaseHelper = RoomDatabaseHelper.invoke(context)
    }
}