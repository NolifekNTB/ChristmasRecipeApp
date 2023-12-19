package com.example.recipeapp.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

// Detail screen composable
@Composable
fun RecipeDetailScreen(navController: NavHostController, recipeId: String, recipes: List<Recipe>) {
    val recipeIndex = recipes.indexOfFirst { it.id == recipeId.toInt() }

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

        // Back button
        Button(
            //TODO onclick
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Back to List")
        }
    }
}

@Preview
@Composable
fun previewDetailScreen(){
    RecipeDetailScreen(rememberNavController(), "0", emptyList())
}