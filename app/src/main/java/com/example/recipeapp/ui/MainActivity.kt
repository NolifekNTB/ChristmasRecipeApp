package com.example.recipeapp.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Sleep
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.recipeapp.R
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.example.recipeapp.viewModel.RecipeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private val mainVM by viewModels<RecipeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main(mainVM)
                }
            }
        }
    }
}

@Preview(showBackground = true) 
@Composable
fun GreetingPreview() {
    RecipeAppTheme {
        Main(RecipeViewModel(Application()))
    }
}

@Composable
fun Main(mainVM: RecipeViewModel) {
    val recipes by mainVM.getAll().collectAsState(emptyList())

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            var i = 0
            while (i < recipes.size) {
                if(i == recipes.size-1){
                    oneRowList(recipes[i])
                    Log.d("tagos", recipes[0].toString())
                    Log.d("tagos", recipes[1].toString())
                    Log.d("tagos", recipes[2].toString())
                    i++
                } else {
                    rowList(recipes[i], recipes[i + 1])
                    i += 2
                }
            }
        }
    }
}

@Composable
fun rowList(recipe1: Recipe, recipe2: Recipe) {
    Row {
        boxRecipe(recipe1,
            Modifier
                .weight(0.5f)
                .padding(10.dp))
        boxRecipe(recipe2,
            Modifier
                .weight(0.5f)
                .padding(10.dp))
    }
}

@Composable
fun oneRowList(recipe: Recipe) {
    Row {
        boxRecipe(recipe,
            Modifier
                .weight(0.5f)
                .padding(10.dp))
        //To fill empty space
        Box(
            Modifier
                .weight(0.5f)
                .padding(10.dp))
    }
}

@Composable
fun boxRecipe(recipe: Recipe, modifier: Modifier = Modifier) {
    Box(
        modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(R.drawable.pizza), contentDescription = null)
            Text(recipe.title, fontSize = 18.sp, modifier = Modifier.padding(top = 10.dp))
        }
    }
}










