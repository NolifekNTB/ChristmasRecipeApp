package com.example.recipeapp.ui.Screens

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.data.Favorite
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.viewModel.RecipeViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FavoriteScreen(mainVM: RecipeViewModel) {
    val savedRecipes by mainVM.getAllFavorite().collectAsState(emptyList())
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Personalized Recipe Box",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // List of saved recipes
        LazyColumn {
            items(savedRecipes) { recipe ->
                RecipeItem(recipe, mainVM, snackbarHostState)
            }
        }
    }
    Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
        // Display the Snackbar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun RecipeItem(recipe: Favorite, mainVM: RecipeViewModel, snackbarHostState: SnackbarHostState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Recipe Image
            Image(
                painter = painterResource(id = recipe.image),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Recipe Title
            Text(
                text = recipe.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Recipe Description
            Text(
                text = recipe.description,
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Remove Recipe Button
            Button(
                onClick = {
                    GlobalScope.launch {
                        mainVM.removeFavoriteList(recipe)
                        snackbarHostState.showSnackbar("Recipe removed from the box!")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Remove Recipe")
            }
        }
    }
}
