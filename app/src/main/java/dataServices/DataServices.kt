package dataServices

interface DataServices {
    fun searchForBooks(search: String, callBack: (List<DataServicesBook>?, String?) -> Unit)
    fun storeBookToFavourites(book: DataServicesBook)
    fun removeBookFromFavourites(book: DataServicesBook)
    fun removeBooksFromFavourites(books: List<DataServicesBook>)
    fun getFavouriteBooks(): List<DataServicesBook>
    fun clearAllFavourites()
}