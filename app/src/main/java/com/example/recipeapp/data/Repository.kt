package com.example.recipeapp.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

/**
 * Repository class that acts as a single source of truth for accessing and managing recipe data.
 *
 * This class abstracts the data layer and provides a clean API to interact with the underlying Room database.
 *
 * @param context Application context for initializing the Room database.
 */
class Repository(context: Context): ContactDao {
    // Initialize the Room DAO (Data Access Object)
    private val dao = contactDb.getInstance(context).contactDao()

    /**
     * Inserts a list of recipes into the database.
     *
     * @param contact List of recipes to be inserted.
     */
    override suspend fun insertAll(contact: List<Recipe>) {
        dao.insertAll(contact)
    }

    /**
     * Deletes a list of recipes from the database.
     *
     * @param contact List of recipes to be deleted.
     */
    override suspend fun delete(contact: List<Recipe>) {
        dao.delete(contact)
    }

    /**
     * Updates a recipe in the database.
     *
     * @param contact Recipe to be updated.
     */
    override suspend fun update(contact: Recipe) {
        dao.update(contact)
    }

    /**
     * Retrieves all recipes from the database as a Flow.
     *
     * @return Flow emitting a list of all recipes.
     */
    override fun getAll(): Flow<List<Recipe>> {
        return dao.getAll()
    }

    /**
     * Drops the entire database.
     *
     * **Use with caution, as this action permanently deletes all data.**
     */
    override suspend fun dropDatabase() {
        dao.dropDatabase()
    }

    /**
     * Searches for recipes in the database based on a query.
     *
     * @param query Search query for filtering recipes.
     * @return Flow emitting a list of recipes matching the search query.
     */
    override fun searchRecipes(query: String): Flow<List<Recipe>> {
        return dao.searchRecipes(query)
    }

}