package com.example.keyboardShorcuts.model

data class ShortCuts(
    val title: String,
    val meta_title: String,
    val meta_description: String,
    val sections: List<Category>
)

data class Category(val name: String, val shortcuts: List<Keys>)
data class Keys(val description: String, val keys: List<String>)