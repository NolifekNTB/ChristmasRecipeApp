package com.example.recipeapp.viewModel

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.R
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeViewModel(app: Application): AndroidViewModel(app) {
    //HANDLE ERROS
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    //SEARCH
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

    fun getAll(): Flow<List<Recipe>> {
        return try{
            repo.getAll()
        } catch (e: Exception){
            _errorMessage.value = "Error fetching recipes: ${e.message}"
            val emptyList = MutableStateFlow<List<Recipe>>(emptyList())
            emptyList
        }
    }

    private fun insertAll(list: List<Recipe>) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.insertAll(list)
        }
    }
}