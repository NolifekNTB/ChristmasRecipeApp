package com.example.recipeapp.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.ui.theme.RecipeAppTheme


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //An example list
    val list = listOf(
        Recipe(title = "Szarlotka", ingredients = "ingredients Szarlotka", instructions = "instructions Szarlotka", image = R.drawable.szarlotka),
        Recipe(title = "Pizza", ingredients = "ingredients Pizza", instructions = "instructions Pizza", image = R.drawable.pizza),
        Recipe(title = "Pierogi", ingredients = "ingredients Pierogi", instructions = "instructions Pierogi", image = R.drawable.pierogi))
    RecipeAppTheme {
        MainScreen(rememberNavController(), list)
    }
}


@Composable
fun MainScreen(navController: NavHostController, recipes: List<Recipe>) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
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
    }
}

@Composable
fun rowList(recipe1: Recipe, recipe2: Recipe, navController: NavHostController) {
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
fun oneRowList(recipe: Recipe, navController: NavHostController) {
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
fun boxRecipe(recipe: Recipe, navController: NavHostController, modifier: Modifier = Modifier) {
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



