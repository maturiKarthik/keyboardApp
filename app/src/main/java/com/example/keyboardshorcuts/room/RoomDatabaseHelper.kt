package com.example.keyboardshorcuts.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.keyboardshorcuts.model.Constants
import com.example.keyboardshorcuts.model.Programs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Programs::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun programsDao(): ProgramsDao
}

class RoomDatabaseHelper {

    companion object {
        private var instance: RoomDatabaseHelper? = null
        private val LOCK = Job()
        private var programsDao: ProgramsDao? = null

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: build(context).apply {
                instance = this
            }
        }


        private fun build(context: Context): RoomDatabaseHelper {
            val db =
                Room.databaseBuilder(context, AppDatabase::class.java, "keyboard-shortcuts")
                    .allowMainThreadQueries().build()
            programsDao = db.programsDao()
            prePopulate()
            return RoomDatabaseHelper()
        }

        private fun prePopulate() {
            GlobalScope.launch {
                programsDao?.let {
                    it.deleteAll()
                    it.insertAll(Constants.listOfPrograms)
                }
            }
        }
    }

    suspend fun getAllPrograms(): List<Programs>? {
        return programsDao?.getAll()
    }

    suspend fun insertAll(data: List<Programs>) {
        programsDao?.insertAll(data)
    }

    suspend fun searchByName(query: String): List<Programs>? {
        return programsDao?.findByName("%$query%")
    }


}