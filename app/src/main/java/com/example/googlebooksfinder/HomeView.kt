package com.example.googlebooksfinder

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
@Composable


private fun SearchBar(onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        onValueChange = {
            text = it
                        },
        label = { Text(text = "Search Google Books") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(text)
            // Hide the keyboard after submitting the search
            keyboardController?.hide()
            //or hide keyboard
            focusManager.clearFocus()

        })
    )
}


@Composable
fun HomeView(viewModel: MainActivityViewModel) {
    val books = viewModel.booksListRowViewModels.value
    Column(
        Modifier.fillMaxSize()) {
        SearchBar() {
            viewModel.search.value = it
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
fun HomeViewComposablePreview() {
    HomeView(MainActivityViewModel())
}