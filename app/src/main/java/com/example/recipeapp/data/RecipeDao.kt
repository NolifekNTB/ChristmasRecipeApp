package com.example.recipeapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contact: List<Recipe>)

    @Delete
    suspend fun delete(contact: List<Recipe>)

    @Update
    suspend fun update(contact: Recipe)

    @Query("SELECT * FROM recipe_table")
    fun getAll(): Flow<List<Recipe>>

    @Query("DELETE FROM recipe_table")
    suspend fun dropDatabase()

    @Query("SELECT * FROM recipe_table WHERE title LIKE '%' || :query || '%'")
    fun searchRecipes(query: String): Flow<List<Recipe>>
}