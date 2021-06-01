package com.example.keyboardShorcuts.model

import com.google.gson.annotations.SerializedName

data class ShortCuts(
    val title: String,
    val meta_title: String,
    val meta_description: String,
    val sections: List<Category>,
    @SerializedName("mac_only")
    val macOnly: Boolean
)

data class Category(val name: String, val shortcuts: List<Keys>)
data class Keys(val description: String, val keys: List<String>, val mac_keys: List<String>)