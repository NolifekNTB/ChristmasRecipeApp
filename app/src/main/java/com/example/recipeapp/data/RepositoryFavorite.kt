package com.example.recipeapp.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class RepositoryFavorite(context: Context): FavoriteDao {
    private val dao = contactDb.getInstance(context).favoriteDao()

    override suspend fun insertAll(favorite: List<Favorite>) {
        favorite.forEach { favorites ->
            if (!dao.recipeExists(favorites.title)) {
                dao.insertAll(favorite)
            }
        }
    }

    override suspend fun recipeExists(title: String): Boolean {
        return dao.recipeExists(title)
    }

    override suspend fun delete(favorite: Favorite) {
        dao.delete(favorite)
    }

    override suspend fun update(favorite: Favorite) {
        dao.update(favorite)
    }

    override fun getAll(): Flow<List<Favorite>> {
        return dao.getAll()
    }

    override suspend fun dropDatabase() {
        return dao.dropDatabase()
    }



}