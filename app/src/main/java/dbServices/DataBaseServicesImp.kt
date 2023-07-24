package dbServices

import android.content.Context
import android.content.Context.MODE_PRIVATE
import org.json.JSONArray

class DataBaseServicesImp(context: Context): DataBaseServices {

    private val favouriteBooksListKey: String = "FavouriteBooksListKey"
    private  val sharedPreferences = context.getSharedPreferences(context.applicationContext.packageName, MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    private fun saveFavourites(favourites: List<String>) {
        val jsonArray = JSONArray(favourites)
        editor.putString(jsonArray.toString(), favouriteBooksListKey)
        editor.apply()
    }

    override fun addBookToFavourite(bookJosn: String) {
        var savedBooks: MutableList<String>? = getFavouriteBooks() as MutableList<String>
        savedBooks = savedBooks ?: mutableListOf<String>()
        savedBooks.add(bookJosn)
        saveFavourites(savedBooks)
    }

    override fun removeBookFromFavourite(bookJson: String) {
        val savedBooks: MutableList<String> = getFavouriteBooks() as MutableList<String>
        if (savedBooks.contains(bookJson)) {
            savedBooks.remove(bookJson)
        }
        saveFavourites(savedBooks)
    }

    override fun removeBooksFromFavourites(books: List<String>) {
        val savedBooks: MutableList<String> = getFavouriteBooks() as MutableList<String>
        books.forEach { book ->
            if (savedBooks.contains(book)) {
                savedBooks.remove(book)
            }
        }

        saveFavourites(savedBooks)
    }

    override fun getFavouriteBooks(): List<String>? {
        val jsonArray = sharedPreferences.getString(favouriteBooksListKey, null)
        val list = JSONArray(jsonArray)
        return  list.toList()
    }
}

fun JSONArray.toList(): MutableList<String> {
    var mutableList: MutableList<String> = mutableListOf()
    for (i in 0 until this.length()) {
       val value = this.get(i) as? String
        if (value != null) {
            mutableList.add(value)
        }
    }

    return mutableList
}