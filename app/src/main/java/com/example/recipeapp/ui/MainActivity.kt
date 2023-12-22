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

/**
 * The main activity of the RecipeApp application.
 * Responsible for setting up the Compose UI and navigation.
 */
class MainActivity : ComponentActivity() {
    // ViewModel instance for managing recipe-related data
    private val mainVM by viewModels<RecipeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the Compose content for the activity
        setContent {
            // Apply the RecipeApp theme to the entire UI
            RecipeAppTheme {
                // Collect the latest recipes from the ViewModel as a state
                val recipes by mainVM.getAll().collectAsState(emptyList())

                // Set up the Compose UI within a Surface
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Create a navigation controller for managing navigation within the app
                    val navController = rememberNavController()

                    // Set up the navigation host using Jetpack Compose Navigation
                    NavHost(navController = navController, startDestination = "recipeList") {
                        // Define the main screen (recipe list)
                        composable("recipeList") {
                            MainScreen(navController, mainVM)
                        }

                        // Define the detail screen
                        composable("recipeDetail/{recipeId}") { backStackEntry ->
                            // Extract the recipeId from the navigation arguments
                            val recipeId = backStackEntry.arguments?.getString("recipeId")
                            recipeId?.let { recipeId ->
                                // Display the RecipeDetailScreen for the selected recipe
                                RecipeDetailScreen(navController, recipeId, recipes)
                            }
                        }
                    }
                  }
                }
            }
        }
    }





