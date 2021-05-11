package com.example.keyboardshorcuts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "programs")
data class Programs(val name: String) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}