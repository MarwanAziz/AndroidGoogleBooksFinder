package com.example.googlebooksfinder

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dataServices.DataServices
import dataServices.DataServicesBook
import dataServices.DataServicesImp

class MainActivityViewModel: ViewModel() {
    private val dataServices: DataServices = DataServicesImp()
    private var _booksListRowViewModels = mutableStateOf<MutableList<BooksListRowViewModel>>(
        mutableListOf()
    )
    val booksListRowViewModels: State<MutableList<BooksListRowViewModel>> = _booksListRowViewModels

    private var _favouriteBooks = mutableListOf<DataServicesBook>()
    set(value) {
        field = value
        favouriteBooksRows.value = value.map { transform(it) }.toMutableList()
    }
    private val favouriteBooksRows = mutableStateOf<MutableList<BooksListRowViewModel>>(
        mutableListOf()
    )
    val favouriteBooks: State<MutableList<BooksListRowViewModel>> = favouriteBooksRows
    private var searching: Boolean = false
    var searchError = MutableLiveData<Boolean>(false)

    var search = MutableLiveData<String?>(null)
    private var lastSearch: String? = null

    init {
        search.observeForever {
            searchBooks(it)
        }
        _favouriteBooks = loadFavourites()
    }

    private fun searchBooks(search: String?) {
        if (search != null && !search.isEmpty()) {
            if (searching) return
            searching = true
            lastSearch = search
            val searchKeyWords = search.replace(" ", "+")
            dataServices.searchForBooks(searchKeyWords) { result, error ->
                if(error != null) {
                    searchError.postValue(true)
                } else if (result != null) {
                    val searchResult = result?.map { transform(it) }
                    _booksListRowViewModels.value = searchResult.toMutableList()
                } else {
                    searchError.postValue(true)
                }

                searching = false
                if (lastSearch != this.search.value) {
                    lastSearch = null
                    searchBooks(this.search.value)
                }
            }
        } else {

        }
    }
    private fun isBookFavourite(book: DataServicesBook): Boolean {
       return _favouriteBooks.firstOrNull { it.id == book.id } != null
    }

    private fun updateLists(book: DataServicesBook) {
        _favouriteBooks = loadFavourites()
        val isFavourite = isBookFavourite(book)
        val rowViewModel = _booksListRowViewModels.value?.firstOrNull { it.book.id ==  book.id}
        rowViewModel?.isFavourite?.value = isFavourite
    }

    private fun addRemoveFavouriteBook(book: DataServicesBook) {
        if (isBookFavourite(book)) {
            dataServices.removeBookFromFavourites(book)
        } else {
            dataServices.storeBookToFavourites(book)
        }
    }

    private fun transform(book: DataServicesBook): BooksListRowViewModel {
        return BooksListRowViewModel(book, isBookFavourite(book)) { clickedBook ->
            addRemoveFavouriteBook(clickedBook)
            updateLists(clickedBook)
        }
    }

    private fun loadFavourites(): MutableList<DataServicesBook> {
        return dataServices.getFavouriteBooks().toMutableList()
    }
}