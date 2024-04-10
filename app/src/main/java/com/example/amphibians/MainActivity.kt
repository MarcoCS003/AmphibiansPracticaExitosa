package com.example.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.theme.screens.AmphibiansApp
import com.example.amphibians.ui.theme.screens.AmphibiansUiState
import com.example.amphibians.ui.theme.screens.AmphibiansViewModel
import com.example.amphibians.ui.theme.theme.AmphibiansTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiansTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val amphiViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
                    AmphibiansApp(amphiViewModel.amphibiansUiState)
                }
            }
        }
    }
}
