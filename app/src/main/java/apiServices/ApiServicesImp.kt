package apiServices

import android.content.Context
import com.google.gson.GsonBuilder
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import org.json.JSONObject
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ApiServicesImp(val context: Context): ApiServices {

    private val baseUrl: String = "https://www.googleapis.com/books/v1/volumes?q=intitle:"

    override fun searchBooks(searchTerm: String, callBack: (ResponseData) -> Unit) {
        CompletableFuture.supplyAsync {
            searchGoogleBooks(searchTerm) { searchResult, error ->
                var apiError: String? = null
                if (searchResult == null) {
                    apiError = error ?: "Unable to search Google Books"
                }
                val responseData = ResponseData(searchResult, apiError)
                callBack.invoke(responseData)
            }
        }
    }

    private fun decode(buffer: ByteBuffer): SearchResult {
        val decoder = Charset.defaultCharset().newDecoder()
        val json = decoder.decode(buffer).toString()
        val jsonObject = JSONObject(json)
        var gson = GsonBuilder().setLenient().create()
        var searchResult = gson.fromJson(jsonObject.toString(), SearchResult::class.java)
        return searchResult
    }

    private fun searchBooksCallBack(callBack: (SearchResult?, String?) -> Unit): ApiServiceCallBack {
        val searchCallBack = ApiServiceCallBack() { buffer, error ->
            if (error != null) {
                callBack.invoke(null, error)
            } else if (buffer != null) {
                val result = decode(buffer)
                callBack.invoke(result, null)
            } else {
                callBack.invoke(null, "Unknown error")
            }
        }

        return searchCallBack
    }
    private fun buildSearchRequest(search: String, callBack: (SearchResult?, String?) -> Unit): UrlRequest {
        val myBuilder = CronetEngine.Builder(context)
        val cronetEngine: CronetEngine = myBuilder.build()
        val executor: Executor = Executors.newSingleThreadExecutor()
        val requestBuilder = cronetEngine.newUrlRequestBuilder(
            baseUrl + search,
            searchBooksCallBack(callBack),
            executor
        )

        return requestBuilder.build()
    }
    private fun searchGoogleBooks(search: String, callBack: (SearchResult?, String?) -> Unit) {
        val request: UrlRequest = buildSearchRequest(search, callBack)
        request.start()
    }
}