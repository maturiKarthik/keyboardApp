package com.example.keyboardshorcuts.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.keyboardshorcuts.Repository
import com.example.keyboardshorcuts.model.Programs
import kotlinx.coroutines.launch

class ListFragmentViewModel(application: Application) : BaseViewModel(application) {
    var listOfPrograms = MutableLiveData<List<Programs>>()

    fun loadPrograms() {
        launch {
            Repository.roomDatabaseHelper.getAllPrograms()?.let {
                listOfPrograms.value = it
            }
        }
    }

    fun searchPrograms(query: String){
        launch {
            Repository.roomDatabaseHelper.searchByName(query).let {
                listOfPrograms.value = it
            }
        }

    }

}
