package dbServices

import android.content.Context

class DataBaseServicesImp private constructor(): DataBaseServices {

    private var catch: MutableSet<String>

    init {
        catch = mutableSetOf()
    }

    companion object {
        @Volatile
        private var instance: DataBaseServices? = null

        fun getInstance(): DataBaseServices {
            return instance ?: synchronized(this) {

                instance ?: DataBaseServicesImp().also { instance = it }
            }
        }
    }

    override fun addBookToFavourite(bookJosn: String) {
        catch.add(bookJosn)
    }

    override fun removeBookFromFavourite(bookJson: String) {
        if (catch.contains(bookJson)) {
            catch.remove(bookJson)
        }
    }

    override fun removeBooksFromFavourites(books: List<String>) {
        catch.removeAll(books.toSet())
    }

    override fun getFavouriteBooks(): List<String>? {
        return catch.toList()
    }
}