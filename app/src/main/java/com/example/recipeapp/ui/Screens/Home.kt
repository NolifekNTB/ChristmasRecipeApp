package com.example.recipeapp.ui.Screens

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.example.recipeapp.viewModel.RecipeViewModel


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    RecipeAppTheme {
        MainScreen(rememberNavController(), RecipeViewModel(Application()))
    }
}

@Composable
fun MainScreen(navController: NavController, mainVM: RecipeViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            //Searcah functionality
            SearchScreen(navController, mainVM)
        }
    }
}

@Composable
fun SearchScreen(navController: NavController, mainVM: RecipeViewModel) {
    var query by remember { mutableStateOf("") }

    Column {
        Row {
            //Searh bar
            TextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search Recipes") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        // Display the search results
        listDisplay(navController, mainVM.searchRecipes(query).collectAsState(emptyList()).value)
    }
}

@Composable
fun listDisplay(navController: NavController, recipes: List<Recipe>){
    var i = 0
    while (i < recipes.size) {
        if(i == recipes.size-1){
            oneRowList(recipes[i], navController)
            i++
        } else {
            rowList(recipes[i], recipes[i + 1], navController)
            i += 2
        }
    }
}





@Composable
fun rowList(recipe1: Recipe, recipe2: Recipe, navController: NavController) {
    Row {
        boxRecipe(recipe1,
            navController,
            Modifier
                .weight(0.5f)
                .padding(10.dp))
        boxRecipe(recipe2,
            navController,
            Modifier
                .weight(0.5f)
                .padding(10.dp))
    }
}

@Composable
fun oneRowList(recipe: Recipe, navController: NavController) {
    Row {
        boxRecipe(recipe,
            navController,
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
fun boxRecipe(recipe: Recipe, navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier
            .clickable {
                // Navigate to the detail screen when clicked
                navController.navigate("recipeDetail/${recipe.id}")

            }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(recipe.image), contentDescription = null, contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp, 150.dp))
            Text(recipe.title, fontSize = 18.sp, modifier = Modifier.padding(top = 10.dp))
        }
    }
}



