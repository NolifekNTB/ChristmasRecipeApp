package com.example.recipeapp.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.R
import com.example.recipeapp.data.Favorite
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.Repository
import com.example.recipeapp.data.RepositoryFavorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing recipe-related data and interactions.
 *
 * @param app Application instance for accessing Android-specific resources.
 */
class RecipeViewModel(app: Application): AndroidViewModel(app) {
    private val repoFavorite = RepositoryFavorite(app.applicationContext)

    init{
        val list = listOf(Recipe(title = "Szarlotka", ingredients = "mąka - 100g\nwoda-", instructions = "instructions", image = R.drawable.szarlotka),
            Recipe(title = "pizza", ingredients = "ingredients", instructions = "instructions", image = R.drawable.pizza),
            Recipe(title = "pierogi", ingredients = "ingredients", instructions = "instructions", image = R.drawable.pierogi))
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteAll()
            repo.insertAll(list)
            }
        }



    //HANDLE ERROS
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    suspend fun updateFavoriteList(favorite: Favorite){
        repoFavorite.update(favorite)
    }

    suspend fun insertFavorite(favorites: List<Favorite>){
        repoFavorite.insertAll(favorites)
    }

    suspend fun removeFavoriteList(favorite: Favorite){
        repoFavorite.delete(favorite)
    }

    fun getAllFavorite(): Flow<List<Favorite>> {
        return repoFavorite.getAll()
    }

    /**
     * Clears the current error message.
     */
    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    /**
     * Searches for recipes based on the provided query.
     *
     * @param query Search query for filtering recipes.
     * @return Flow emitting a list of recipes matching the search query.
     */
    fun searchRecipes(query: String): Flow<List<Recipe>> {
        return try{
            repo.searchRecipes(query)
        } catch (e: Exception){
            _errorMessage.value = "Error fetching recipes: ${e.message}"
            val emptyList = MutableStateFlow<List<Recipe>>(emptyList())
            emptyList
        }
    }

    //ROOM
    private val repo = Repository(app.applicationContext)

    /**
     * Retrieves all recipes from the repository.
     *
     * @return Flow emitting a list of all recipes.
     */
    fun getAll(): Flow<List<Recipe>> {
        return try{
            repo.getAll()
        } catch (e: Exception){
            _errorMessage.value = "Error fetching recipes: ${e.message}"
            val emptyList = MutableStateFlow<List<Recipe>>(emptyList())
            emptyList
        }
    }

    /**
     * Inserts a list of recipes into the repository.
     *
     * @param list List of recipes to be inserted.
     */
    private fun insertAll(list: List<Recipe>) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.insertAll(list)
        }
    }
}