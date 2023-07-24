package dataServices

import android.content.Context
import apiServices.*
import com.google.gson.GsonBuilder
import dbServices.DataBaseServices
import dbServices.DataBaseServicesImp

class DataServicesImp(context: Context): DataServices {
    private val apiServices: ApiServices = ApiServicesImp(context)
    private val dataBaseServices: DataBaseServices = DataBaseServicesImp.getInstance()

    private fun transform(item: Item): DataServicesBook {
        var gson = GsonBuilder().setLenient().create()
        val jsonObject = gson.toJson(item)
        var result = gson.fromJson(jsonObject, DataServicesBook::class.java)
        return result
    }

    private fun transformToJson(book: DataServicesBook): String {
        var gson = GsonBuilder().setLenient().create()
        val jsonObject = gson.toJson(book)
        return  jsonObject
    }

    private fun transform(json: String): DataServicesBook {
        var gson = GsonBuilder().setLenient().create()
        var result = gson.fromJson(json, DataServicesBook::class.java)
        return result
    }

    private fun getBooksFromApiResponse(response: SearchResult): List<DataServicesBook> {
        return response.items?.map { transform(it) } ?: emptyList()
    }

    override fun searchForBooks(
        search: String,
        callBack: (List<DataServicesBook>?, String?) -> Unit
    ) {
        apiServices.searchBooks(search) {
            if (it.error != null) {
                callBack.invoke(null, it.error)
            } else if (it.searchResult != null) {
                val books = getBooksFromApiResponse(it.searchResult)
                callBack.invoke(books, null)
            } else {
                callBack.invoke(null, "Unable to search Google Books")
            }
        }
    }

    override fun storeBookToFavourites(book: DataServicesBook) {
        val jsonBook = transformToJson(book)
        dataBaseServices.addBookToFavourite(jsonBook)
    }

    override fun removeBookFromFavourites(book: DataServicesBook) {
        dataBaseServices.removeBookFromFavourite(transformToJson(book))
    }

    override fun removeBooksFromFavourites(books: List<DataServicesBook>) {
        val jsonBooks = books.map { transformToJson(it) }
        dataBaseServices.removeBooksFromFavourites(jsonBooks)
    }

    override fun getFavouriteBooks(): List<DataServicesBook> {
        return dataBaseServices.getFavouriteBooks()?.map { transform(it) } ?: emptyList()
    }
}