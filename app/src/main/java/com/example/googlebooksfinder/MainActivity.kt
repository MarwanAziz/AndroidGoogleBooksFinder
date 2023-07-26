package com.example.googlebooksfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.googlebooksfinder.ui.theme.GoogleBooksFinderTheme
import dataServices.DataServices
import dataServices.DataServicesImp

class MainActivity : ComponentActivity() {
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainActivityViewModel()
        setContent {
            GoogleBooksFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TabScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun TabScreen(viewModel: MainActivityViewModel) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Home", "Favourites")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when (index) {
                            0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            1 -> Icon(imageVector = Icons.Default.Star, contentDescription = null)
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> HomeView(viewModel)
            1 -> FavouriteBooksView(viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GoogleBooksFinderTheme {
        TabScreen(MainActivityViewModel())
    }
}