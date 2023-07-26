package com.example.googlebooksfinder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavouriteBooksView(viewModel: MainActivityViewModel) {
    val books = viewModel.favouriteBooks.value
    Column(
        Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(colorResource(R.color.purple_200)),
        ) {

            Text(
                "Favourites",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
        LazyColumn {
            items(books) {
                BooksListRow(it)
            }
        }
    }
}

@Preview
@Composable
fun FavouriteBooksComposablePreview() {
    FavouriteBooksView(MainActivityViewModel())
}