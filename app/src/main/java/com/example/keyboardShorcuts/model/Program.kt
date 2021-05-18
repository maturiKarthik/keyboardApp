package com.example.keyboardShorcuts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "programs")
data class Programs(val name: String, val fileName: String) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}