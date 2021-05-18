package com.example.keyboardShorcuts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.keyboardShorcuts.Repository
import com.example.keyboardShorcuts.model.ShortCuts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShortKeySFragmentViewModel(application: Application) : BaseViewModel(application) {

    var shortcutData = MutableLiveData<ShortCuts>()
    var errorMsf = MutableLiveData<Boolean>()

    fun onLoad(url: String) {
        Repository.retrofitHelper.getAllRepos(url)?.enqueue(object : Callback<ShortCuts> {
            override fun onFailure(call: Call<ShortCuts>, t: Throwable) {
                errorMsf.value = true
            }

            override fun onResponse(call: Call<ShortCuts>, response: Response<ShortCuts>) {
                response.let {
                    it.let { shortcut ->
                        Log.e("Data","$shortcut")
                        shortcutData.value = shortcut.body()
                    }
                }
            }
        })
    }
}
