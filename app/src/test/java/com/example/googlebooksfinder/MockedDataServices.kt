package com.example.googlebooksfinder

import dataServices.DataServices
import dataServices.DataServicesBook
import dataServices.DataServicesImp

class MockedDataServices(var mockedApiData: List<DataServicesBook>?): DataServices {
    private val dataServices: DataServices = DataServicesImp()

    override fun searchForBooks(
        search: String,
        callBack: (List<DataServicesBook>?, String?) -> Unit
    ) {
        callBack.invoke(mockedApiData, null)
    }

    override fun storeBookToFavourites(book: DataServicesBook) {
        dataServices.storeBookToFavourites(book)
    }

    override fun removeBookFromFavourites(book: DataServicesBook) {
        dataServices.removeBookFromFavourites(book)
    }

    override fun removeBooksFromFavourites(books: List<DataServicesBook>) {
        dataServices.removeBooksFromFavourites(books)
    }

    override fun getFavouriteBooks(): List<DataServicesBook> {
        return dataServices.getFavouriteBooks()
    }

    override fun clearAllFavourites() {
        dataServices.clearAllFavourites()
    }
}