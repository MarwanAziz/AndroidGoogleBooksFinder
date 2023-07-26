package com.example.googlebooksfinder

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dataServices.DataServicesBook

class BooksListRowViewModel(val book: DataServicesBook,
                            favourites: Boolean,
                            private val onFavourite: (DataServicesBook) -> Unit): ViewModel() {
    val isFavourite = mutableStateOf(false)
    var favourite: State<Boolean> = isFavourite
    val title: String
    val subtitle: String
    val author: String
    val publishedDate: String
    val rate: String?
    val thumbnailUrl: String

    init {
        isFavourite.value = favourites
        title = book.volumeInfo?.title ?: ""
        subtitle = book.volumeInfo?.subtitle ?: ""
        author = book.volumeInfo?.authors?.joinToString(" ") ?: ""
        publishedDate = book.volumeInfo?.publishedDate ?: ""
        var bookRates: String? = null
        if (book.volumeInfo?.averageRating != null) {
            bookRates = book.volumeInfo.averageRating.toString()
        }

        rate = bookRates
        thumbnailUrl = fixUrl(book.volumeInfo?.imageLinks?.thumbnail) ?: ""
    }

    private fun fixUrl(url: String?): String? {
        if (url == null) return null
        if(!url.contains("https:")) {
           return url.replace("http:", "https:")
        }

        return url
    }

    fun onFavouriteClicked() {
        onFavourite(book)
    }
}