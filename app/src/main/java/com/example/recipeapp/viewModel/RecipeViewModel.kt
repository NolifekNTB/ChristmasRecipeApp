package com.example.recipeapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RecipeViewModel(app: Application): AndroidViewModel(app) {
    private val repo = Repository(app.applicationContext)
    /*
    private val recipe = Recipe(title = "Szarlotka", ingredients = "ingredient", instructions = "instruction")

    fun getAll(): Flow<List<Recipe>> {
        return repo.getAll()
    }

    init {
        insertAll()
    }

    fun insertAll() {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.insertAll(listOf(recipe))
        }
    }
     */
}