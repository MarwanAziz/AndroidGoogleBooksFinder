package apiServices

import android.util.Log
import org.chromium.net.CronetException
import org.chromium.net.UrlRequest
import org.chromium.net.UrlResponseInfo
import java.nio.ByteBuffer

private const val TAG = "MyUrlRequestCallback"

internal class ApiServiceCallBack(val completionHandler: (ByteBuffer?, String?) -> Unit) : UrlRequest.Callback() {
    private val myBuffer: ByteBuffer = ByteBuffer.allocateDirect(102400)
    override fun onRedirectReceived(request: UrlRequest?, info: UrlResponseInfo?, newLocationUrl: String?) {
        Log.i(TAG, "onRedirectReceived method called.")
        // You should call the request.followRedirect() method to continue
        // processing the request.
        request?.followRedirect()
    }

    override fun onResponseStarted(request: UrlRequest?, info: UrlResponseInfo?) {
        val httpStatusCode = info?.httpStatusCode
        if (httpStatusCode == 200) {
            // The request was fulfilled. Start reading the response.
            request?.read(myBuffer)
        } else if (httpStatusCode == 503) {
            // The service is unavailable. You should still check if the request
            // contains some data.
            request?.read(ByteBuffer.allocateDirect(102400))
        } else if (httpStatusCode == 404) {
            completionHandler.invoke(null, "Not Found")
        }
    }

    override fun onReadCompleted(request: UrlRequest?, info: UrlResponseInfo?, byteBuffer: ByteBuffer?) {
        Log.i(TAG, "onReadCompleted method called.")
        // You should keep reading the request until there's no more data.
        byteBuffer?.clear()
        request?.read(byteBuffer)
    }

    override fun onSucceeded(request: UrlRequest?, info: UrlResponseInfo?) {
        Log.i(TAG, "onSucceeded method called.")
        completionHandler.invoke(myBuffer, null)
    }

    override fun onFailed(request: UrlRequest?, info: UrlResponseInfo?, error: CronetException?) {
        completionHandler.invoke(null, error?.message ?: "Data not found")
    }
}