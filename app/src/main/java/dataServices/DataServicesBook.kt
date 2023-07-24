package dataServices

import apiServices.*

data class DataServicesBook(
    val kind: BookKind? = null,
    val id: String? = null,
    val etag: String? = null,
    val selfLink: String? = null,
    val volumeInfo: BookInfo? = null,
    val saleInfo: BookSaleInfo? = null,
    val accessInfo: BookAccessInfo? = null,
    val searchInfo: BookSearchInfo? = null
)
enum class BookKind {
    BooksVolume
}

data class BookInfo (
    val title: String? = null,
    val authors: List<String>? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: Long? = null,
    val printType: String? = null,
    val categories: List<String>? = null,
    val averageRating: Double? = null,
    val ratingsCount: Long? = null,
    val contentVersion: String? = null,
    val imageLinks: BookImageLinks? = null,
    val language: String? = null,
    val previewLink: String? = null,
    val infoLink: String? = null,
    val canonicalVolumeLink: String? = null,
    val subtitle: String? = null,
    val publisher: String? = null
)

data class BookSaleInfo (
    val country: String? = null,
    val saleability: BookSaleability? = null,
    val isEbook: Boolean? = null,
    val listPrice: BookSaleInfoListPrice? = null,
    val retailPrice: BookSaleInfoListPrice? = null,
    val buyLink: String? = null,
)

data class BookAccessInfo (
    val country: Country? = null,
    val viewability: BookViewability? = null,
    val embeddable: Boolean? = null,
    val publicDomain: Boolean? = null,
    val webReaderLink: String? = null,
    val quoteSharingAllowed: Boolean? = null
)
data class BookSearchInfo (
    val textSnippet: String? = null
)

data class BookImageLinks (
    val smallThumbnail: String? = null,
    val thumbnail: String? = null
)

enum class BookSaleability {
    ForSale,
    NotForSale
}

data class BookSaleInfoListPrice (
    val amount: Double? = null,
    val currencyCode: String? = null
)

enum class BookViewability {
    NoPages,
    Partial
}