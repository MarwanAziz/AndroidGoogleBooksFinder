package apiServices
data class ResponseData (val searchResult: SearchResult? = null,
                         val error: String? = null)


data class SearchResult (
    val kind: String? = null,
    val totalItems: Long? = null,
    val items: List<Item>? = null
)

data class Item (
    val kind: Kind? = null,
    val id: String? = null,
    val etag: String? = null,
    val selfLink: String? = null,
    val volumeInfo: VolumeInfo? = null,
    val saleInfo: SaleInfo? = null,
    val accessInfo: AccessInfo? = null,
    val searchInfo: SearchInfo? = null
)

data class AccessInfo (
    val country: Country? = null,
    val viewability: Viewability? = null,
    val embeddable: Boolean? = null,
    val publicDomain: Boolean? = null,
    val textToSpeechPermission: TextToSpeechPermission? = null,
    val epub: Epub? = null,
    val pdf: Epub? = null,
    val webReaderLink: String? = null,
    val accessViewStatus: AccessViewStatus? = null,
    val quoteSharingAllowed: Boolean? = null
)

enum class AccessViewStatus {
    None,
    Sample
}

enum class Country {
    GB
}

data class Epub (
    val isAvailable: Boolean? = null,
    val acsTokenLink: String? = null
)

enum class TextToSpeechPermission {
    Allowed
}

enum class Viewability {
    NoPages,
    Partial
}

enum class Kind {
    BooksVolume
}

data class SaleInfo (
    val country: Country? = null,
    val saleability: Saleability? = null,
    val isEbook: Boolean? = null,
    val listPrice: SaleInfoListPrice? = null,
    val retailPrice: SaleInfoListPrice? = null,
    val buyLink: String? = null,
    val offers: List<Offer>? = null
)

data class SaleInfoListPrice (
    val amount: Double? = null,
    val currencyCode: String? = null
)

data class Offer (
    val finskyOfferType: Long? = null,
    val listPrice: OfferListPrice? = null,
    val retailPrice: OfferListPrice? = null,
    val giftable: Boolean? = null
)

data class OfferListPrice (
    val amountInMicros: Long? = null,
    val currencyCode: String? = null
)

enum class Saleability {
    ForSale,
    NotForSale
}

data class SearchInfo (
    val textSnippet: String? = null
)

data class VolumeInfo (
    val title: String? = null,
    val authors: List<String>? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val industryIdentifiers: List<IndustryIdentifier>? = null,
    val pageCount: Long? = null,
    val printType: PrintType? = null,
    val categories: List<String>? = null,
    val averageRating: Double? = null,
    val ratingsCount: Long? = null,
    val contentVersion: String? = null,
    val imageLinks: ImageLinks? = null,
    val language: Language? = null,
    val previewLink: String? = null,
    val infoLink: String? = null,
    val canonicalVolumeLink: String? = null,
    val subtitle: String? = null,
    val publisher: String? = null
)

data class ImageLinks (
    val smallThumbnail: String? = null,
    val thumbnail: String? = null
)

data class IndustryIdentifier (
    val type: Type? = null,
    val identifier: String? = null
)

enum class Type {
    Isbn10,
    Isbn13,
    Other
}

enum class Language {
    En
}

enum class PrintType {
    Book
}