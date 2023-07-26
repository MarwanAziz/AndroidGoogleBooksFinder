package com.example.googlebooksfinder

import dataServices.*
import org.junit.Test
import org.junit.Assert.*
import java.lang.Thread.sleep
import java.util.UUID


class DataServicesUnitTest {
    private val books = dummyBooksData()
    private val services: DataServices = MockedDataServices(books)

    @Test
    fun search_for_books() {
        var booksFound: List<DataServicesBook>? = null
        services.searchForBooks("books"){ result, _ ->
            booksFound = result
        }
        sleep(2000)
        assertEquals(booksFound?.size, books.size)
    }

    @Test
    fun store_book_to_favourites() {
        services.clearAllFavourites()
        val book = books.first()
        services.storeBookToFavourites(book)
        val fetchedBook = services.getFavouriteBooks().firstOrNull()

        assertEquals(book, fetchedBook)
    }

    @Test
    fun remove_book_from_favourites() {
        services.clearAllFavourites()
        val book = books.first()
        books.forEach {
            services.storeBookToFavourites(it)
        }
        var count = services.getFavouriteBooks().size
        assertEquals(books.size, count)
        services.removeBookFromFavourites(book)
        count = services.getFavouriteBooks().size
        assertEquals(books.size - 1, count)
    }

    @Test
    fun remove_books_from_favourites() {
        services.clearAllFavourites()

        books.forEach {
            services.storeBookToFavourites(it)
        }
        var count = services.getFavouriteBooks().size
        assertEquals(books.size, count)
        val book = books.first()
        val book2 = books.last()
        services.removeBooksFromFavourites(listOf(book, book2))
        count = services.getFavouriteBooks().size
        assertEquals(books.size - 2, count)
    }

    @Test
    fun get_favourites() {
        services.clearAllFavourites()
        val book = books.first()
        services.storeBookToFavourites(book)
        val favBook = services.getFavouriteBooks().firstOrNull()
        assertEquals(book, favBook)
    }
}


fun randomBook(): DataServicesBook {
    return DataServicesBook(
        null,
        UUID.randomUUID().toString(),
        null,
        null,
        null,
        null,
        null,
        null)
}

fun dummyBooksData(): MutableList<DataServicesBook> {
    var books: MutableList<DataServicesBook> = mutableListOf()
    repeat(5) {
        books.add(randomBook())
    }

    return books
}