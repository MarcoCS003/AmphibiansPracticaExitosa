package com.example.amphibians.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibiansData


@Composable
fun AmphibiansApp(amphibiansUiState: AmphibiansUiState) {
    Column(Modifier.padding(8.dp)) {

        Text(
            text = stringResource(R.string.amphibians),
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        when (amphibiansUiState){
            is AmphibiansUiState.Error -> ErrorScreen()
            is AmphibiansUiState.Success -> DataAmphibiansScreen(amphibiansUiState.data)
            is AmphibiansUiState.Loading -> LoadingScreen()

            else -> ErrorScreen()
        }
    }
}

@Composable
fun DataAmphibiansScreen(
    amphibians: List<AmphibiansData>
){
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(items = amphibians, key = { amphibian -> amphibian.name   }){
            amphibian -> CardAmphibians(amphibian = amphibian)
        }
    }
}

@Composable
fun ErrorScreen(){
    Text(text = "NO carga xd")
}

@Composable
fun LoadingScreen(){
    Text(text = "Aguanta las carnes")
}

@Composable
fun CardAmphibians(amphibian: AmphibiansData) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RectangleShape) {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription =null,
                contentScale = ContentScale.FillBounds
            )
            Text("${amphibian.description}")
        }
    }
}