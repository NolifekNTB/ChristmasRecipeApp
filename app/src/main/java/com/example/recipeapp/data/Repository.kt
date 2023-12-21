package com.example.recipeapp.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class Repository(context: Context): ContactDao {
    private val dao = contactDb.getInstance(context).contactDao()
    override suspend fun insertAll(contact: List<Recipe>) {
        dao.insertAll(contact)
    }

    override suspend fun delete(contact: List<Recipe>) {
        dao.delete(contact)
    }

    override suspend fun update(contact: Recipe) {
        dao.update(contact)
    }

    override fun getAll(): Flow<List<Recipe>> {
        return dao.getAll()
    }

    override suspend fun dropDatabase() {
        dao.dropDatabase()
    }

    override fun searchRecipes(query: String): Flow<List<Recipe>> {
        return dao.searchRecipes(query)
    }
}