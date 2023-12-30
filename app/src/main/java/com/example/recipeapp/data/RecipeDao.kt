package com.example.recipeapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for recipe-related database operations.
 *
 * This interface defines methods for inserting, updating, deleting, and querying recipe data in the database.
 */
@Dao
interface ContactDao {
    /**
     * Inserts a list of recipes into the database.
     *
     * @param contact List of [Recipe] objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contact: List<Recipe>)

    /**
     * Deletes a list of recipes from the database.
     *
     * @param contact List of [Recipe] objects to be deleted.
     */
    @Delete
    suspend fun delete(contact: List<Recipe>)

    /**
     * Updates a recipe in the database.
     *
     * @param contact [Recipe] object to be updated.
     */

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(contact: Recipe)

    /**
     * Retrieves all recipes from the database.
     *
     * @return [Flow] emitting a list of [Recipe] objects.
     */
    @Query("SELECT * FROM recipe_table")
    fun getAll(): Flow<List<Recipe>>

    /**
     * Deletes all recipes from the database.
     */
    @Query("DELETE FROM recipe_table")
    suspend fun dropDatabase()

    /**
     * Searches for recipes in the database based on a query string.
     *
     * @param query String to search for in recipe titles.
     * @return [Flow] emitting a list of matching [Recipe] objects.
     */
    @Query("SELECT * FROM recipe_table WHERE title LIKE '%' || :query || '%'")
    fun searchRecipes(query: String): Flow<List<Recipe>>
}

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(favorite: List<Favorite>)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Update
    suspend fun update(favorite: Favorite)

    @Query("SELECT * FROM favorite_table")
    fun getAll(): Flow<List<Favorite>>

    @Query("DELETE FROM favorite_table")
    suspend fun dropDatabase()

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_table WHERE title = :title)")
    suspend fun recipeExists(title: String): Boolean
}