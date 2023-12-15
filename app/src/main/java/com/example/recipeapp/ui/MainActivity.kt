package com.example.recipeapp.ui

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.example.recipeapp.viewModel.RecipeViewModel

class MainActivity : ComponentActivity() {
    private val mainVM by viewModels<RecipeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    main(mainVM)
                }
            }
        }
    }
}

@Preview(showBackground = true) 
@Composable
fun GreetingPreview() {
    RecipeAppTheme {
        main(RecipeViewModel(Application()))
    }
}

@Composable
fun main(mainVM: RecipeViewModel, modifier: Modifier = Modifier) {
    Text("test")
}
