package com.example.keyboardShorcuts.retrofit

import com.example.keyboardShorcuts.model.ShortCuts
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {
        private var instance: RetrofitHelper? = null
        private val LOCK = Job()
        private var shortCutServices: ShortCutServices? = null
        private const val BASE_URL = "https://eugoteams.github.io/"

        operator fun invoke() = instance ?: synchronized(LOCK) {
            instance ?: build().also {
                instance = it
            }
        }

        private fun build(): RetrofitHelper {
            shortCutServices = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ShortCutServices::class.java)
            return RetrofitHelper()
        }
    }

    fun getAllRepos(url:String): Call<ShortCuts>? {
      return  shortCutServices?.listOfRepos(url)
    }
}