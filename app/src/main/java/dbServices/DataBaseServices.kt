package dbServices

interface DataBaseServices {
    fun addBookToFavourite(bookJosn: String)
    fun removeBookFromFavourite(bookJson: String)
    fun removeBooksFromFavourites(books: List<String>)
    fun getFavouriteBooks(): List<String>?
}