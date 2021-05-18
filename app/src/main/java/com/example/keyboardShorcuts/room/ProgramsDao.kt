package com.example.keyboardShorcuts.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.keyboardShorcuts.model.Programs

@Dao
interface ProgramsDao {

    @Query("SELECT * FROM programs ORDER BY name")
    suspend fun getAll(): List<Programs>

    @Insert
    suspend fun insertAll(data: List<Programs>)

    @Query("SELECT * FROM programs WHERE name LIKE :name")
    fun findByName(name: String): List<Programs>

    @Query("DELETE FROM programs")
    fun deleteAll()
}