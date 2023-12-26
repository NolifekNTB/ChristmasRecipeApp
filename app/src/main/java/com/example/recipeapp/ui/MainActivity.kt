package com.example.recipeapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.recipeapp.ui.Screens.FavoriteScreen
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
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the Compose content for the activity
        setContent {
            // Apply the RecipeApp theme to the entire UI
            RecipeAppTheme {
                // Collect the latest recipes from the ViewModel as a state
                val recipes by mainVM.getAll().collectAsState(emptyList())
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            BottomNavigationItem(
                                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                                label = { Text("Home") },
                                selected = true,
                                onClick = { navController.navigate("recipeList") {} }
                            )
                            BottomNavigationItem(
                                icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                                label = { Text("favorite") },
                                selected = false,
                                onClick = { navController.navigate("favorites") {}  }
                            )
                        }
                    }
                ) {
                        NavHost(navController = navController, startDestination = "recipeList") {
                            // Define the main screen (recipe list)
                            composable("recipeList") {
                                MainScreen(navController, mainVM)
                            }

                            composable("favorites") {
                                FavoriteScreen(mainVM)
                            }

                            // Define the detail screen
                            composable("recipeDetail/{recipeId}") { backStackEntry ->
                                // Extract the recipeId from the navigation arguments
                                val recipeId = backStackEntry.arguments?.getString("recipeId")
                                recipeId?.let { recipeId ->
                                    // Display the RecipeDetailScreen for the selected recipe
                                    RecipeDetailScreen(navController, recipeId, recipes, mainVM)
                                }
                            }
                    }
                }
              }
            }
        }
    }





