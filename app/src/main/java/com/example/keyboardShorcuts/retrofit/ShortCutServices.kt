package com.example.keyboardShorcuts.retrofit

import com.example.keyboardShorcuts.model.ShortCuts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShortCutServices {

    @GET("/{urlPath}")
    fun listOfRepos(@Path("urlPath") url: String): Call<ShortCuts>
}