package apiServices

interface ApiServices {
    fun searchBooks(searchTerm: String, callBack: (ResponseData) -> Unit)
}