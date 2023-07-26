package com.example.googlebooksfinder

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

@Composable
fun BookImageView(url: String, modifier: Modifier) {
    AsyncImage(
        model = url,
        placeholder = painterResource(R.drawable.ic_baseline_menu_book_300),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
}