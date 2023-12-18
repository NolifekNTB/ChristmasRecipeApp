package com.example.recipeapp.viewModel

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(app: Application): AndroidViewModel(app) {
    private val _recipeData = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeData = _recipeData.asStateFlow()

    private val repo = Repository(app.applicationContext)

    init {
        //TODO deleteAll shouldn't be in init
        deleteAll()
        fetchData()
    }

    private fun fetchData(){
        val list = listOf(Recipe(title = "Szarlotka", ingredients = "ingredients", instructions = "instructions"),
            Recipe(title = "Pizza", ingredients = "ingredients", instructions = "instructions"),
            Recipe(title = "Hamburger", ingredients = "ingredients", instructions = "instructions"))
        CoroutineScope(viewModelScope.coroutineContext).launch {
            insertAll(list)
        }
    }

    fun deleteAll(){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.dropDatabase()
        }
    }

    fun getAll(): Flow<List<Recipe>> {
        return repo.getAll()
    }

    private fun insertAll(list: List<Recipe>) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.insertAll(list)
        }
    }

    fun update(recipe: Recipe){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.update(recipe)
        }
    }
}