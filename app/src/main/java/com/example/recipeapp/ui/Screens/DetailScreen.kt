package com.example.recipeapp.ui.Screens

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.viewModel.RecipeViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Composable function representing the detail screen of a recipe in the RecipeApp.
 *
 * @param navController Navigation controller for handling navigation events.
 * @param recipeId String representation of the recipe's ID.
 * @param recipes List of Recipe objects containing recipe details.
 */
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun RecipeDetailScreen(navController: NavHostController, recipeId: String, recipes: List<Recipe>, mainVM: RecipeViewModel) {
    val recipeIndex = recipes.indexOfFirst { it.id == recipeId.toInt() }

    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display the recipe image
        Image(
            painter = painterResource(recipes[recipeIndex].image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )

        // Display recipe details
        Text(
            text = recipes[recipeIndex].title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Ingredients:\n${recipes[recipeIndex].ingredients}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Instructions:\n${recipes[recipeIndex].instructions}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Add to Box button
        Button(
            onClick = {
                GlobalScope.launch {
                    if (mainVM.favoriteList.value.contains(recipes[recipeIndex])) {
                        snackbarHostState.showSnackbar("Recipe already in the box!")
                    } else {
                        mainVM.updateFavoriteList(recipes[recipeIndex])
                        snackbarHostState.showSnackbar("Recipe added to the box!")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Add to Box")
        }

        // Back button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Back to List")
        }
        Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            // Display the Snackbar
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun previewDetailScreen(){
    RecipeDetailScreen(rememberNavController(), "0", emptyList(), RecipeViewModel(Application()))
}