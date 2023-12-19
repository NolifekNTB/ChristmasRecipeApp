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
    private val _recipeData = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeData = _recipeData.asStateFlow()

    private val repo = Repository(app.applicationContext)

    //To test code
    /*init {
        deleteAll()
        fetchData()
    }

    fun fetchData(){
        var list = listOf(
            Recipe(title = "Szarlotka", ingredients = "ingredients Szarlotka", instructions = "instructions Szarlotka", image = R.drawable.szarlotka),
            Recipe(title = "Pizza", ingredients = "ingredients Pizza", instructions = "instructions Pizza", image = R.drawable.pizza),
            Recipe(title = "Pierogi", ingredients = "ingredients Pierogi", instructions = "instructions Pierogi", image = R.drawable.pierogi))
        insertAll(list)
    }
     */

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