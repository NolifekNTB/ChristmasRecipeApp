package com.example.recipeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.ui.Screens.MainScreen
import com.example.recipeapp.ui.Screens.RecipeDetailScreen
import com.example.recipeapp.ui.Screens.SearchScreen
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.example.recipeapp.viewModel.RecipeViewModel


class MainActivity : ComponentActivity() {
    private val mainVM by viewModels<RecipeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                val recipes by mainVM.getAll().collectAsState(emptyList())

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // Set up the navigation host
                    NavHost(navController = navController, startDestination = "recipeList") {
                        // Main screen (recipe list)
                        composable("recipeList") {
                            MainScreen(navController, mainVM)
                        }

                        // Detail screen
                        composable("recipeDetail/{recipeId}") { backStackEntry ->
                            val recipeId = backStackEntry.arguments?.getString("recipeId")
                            recipeId?.let { recipeId ->
                                RecipeDetailScreen(navController, recipeId, recipes)
                            }
                        }
                    }
                  }
                }
            }
        }
    }





