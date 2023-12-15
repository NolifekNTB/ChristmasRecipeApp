package com.example.recipeapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert
    suspend fun insertAll(contact: List<Recipe>)

    @Delete
    suspend fun delete(contact: List<Recipe>)

    @Update
    suspend fun update(contact: Recipe)

    @Query("SELECT * FROM recipe_table")
    fun getAll(): Flow<List<Recipe>>

    @Query("DELETE FROM recipe_table")
    suspend fun dropDatabase()
}