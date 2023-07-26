package com.example.googlebooksfinder

import dbServices.DataBaseServices
import dbServices.DataBaseServicesImp
import org.junit.Test

import org.junit.Assert.*

class DataBaseUnitTest {
    private val dataBaseServices: DataBaseServices = DataBaseServicesImp.getInstance()

    @Test
    fun add_favourite() {
        dataBaseServices.clearFavourites()
        val testingJsonString = "Json String"
        dataBaseServices.addBookToFavourite(testingJsonString)

        val fetchedJson = dataBaseServices.getFavouriteBooks()?.first()
        val favouriteBooksCount = dataBaseServices.getFavouriteBooks()?.size
        assertEquals(testingJsonString, fetchedJson)
        assertEquals(1, favouriteBooksCount)
    }

    @Test
    fun remove_favourite() {
        dataBaseServices.clearFavourites()
        val testingJsonString = "Json String to remove"
        dataBaseServices.addBookToFavourite(testingJsonString)

        var fetchedJson = dataBaseServices.getFavouriteBooks()?.first()
        var favouriteBooksCount = dataBaseServices.getFavouriteBooks()?.size
        assertEquals(testingJsonString, fetchedJson)
        assertEquals(1, favouriteBooksCount)

        dataBaseServices.removeBookFromFavourite(testingJsonString)
        fetchedJson = dataBaseServices.getFavouriteBooks()?.firstOrNull()
        favouriteBooksCount = dataBaseServices.getFavouriteBooks()?.size

        assertEquals(null, fetchedJson)
        assertEquals(0, favouriteBooksCount)
    }

    @Test
    fun remove_multiple_favourites() {
        dataBaseServices.clearFavourites()
        val testingJsonString = "Json String to remove"
        val testingJsonStringSeconds = "Second JSON String to remove"
        val testingJsonStringThird = "Third JSON String to remove"
        dataBaseServices.addBookToFavourite(testingJsonString)
        dataBaseServices.addBookToFavourite(testingJsonStringSeconds)
        dataBaseServices.addBookToFavourite(testingJsonStringThird)

        var favouriteBooksCount = dataBaseServices.getFavouriteBooks()?.size

        assertEquals(3, favouriteBooksCount)

        dataBaseServices.removeBooksFromFavourites(listOf(testingJsonString, testingJsonStringSeconds))
        favouriteBooksCount = dataBaseServices.getFavouriteBooks()?.size

        assertEquals(1, favouriteBooksCount)
    }

    @Test
    fun get_favourites() {
        dataBaseServices.clearFavourites()
        val testingJsonString = "Json String to remove"
        val testingJsonStringSeconds = "Second JSON String to remove"
        val testingJsonStringThird = "Third JSON String to remove"
        dataBaseServices.addBookToFavourite(testingJsonString)
        dataBaseServices.addBookToFavourite(testingJsonStringSeconds)
        dataBaseServices.addBookToFavourite(testingJsonStringThird)

        var favouriteBooksCount = dataBaseServices.getFavouriteBooks()?.size

        assertEquals(3, favouriteBooksCount)
    }

    @Test
    fun clear_all_favourites() {
        dataBaseServices.clearFavourites()
        var favouriteBooksCount = dataBaseServices.getFavouriteBooks()?.size
        assertEquals(0, favouriteBooksCount)

        val testingJsonString = "Json String to remove"
        val testingJsonStringSeconds = "Second JSON String to remove"
        val testingJsonStringThird = "Third JSON String to remove"
        dataBaseServices.addBookToFavourite(testingJsonString)
        dataBaseServices.addBookToFavourite(testingJsonStringSeconds)
        dataBaseServices.addBookToFavourite(testingJsonStringThird)

        favouriteBooksCount = dataBaseServices.getFavouriteBooks()?.size

        assertEquals(3, favouriteBooksCount)

        dataBaseServices.clearFavourites()
        favouriteBooksCount = dataBaseServices.getFavouriteBooks()?.size
        assertEquals(0, favouriteBooksCount)
    }
}