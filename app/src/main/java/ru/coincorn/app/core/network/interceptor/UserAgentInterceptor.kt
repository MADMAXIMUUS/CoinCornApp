package ru.coincorn.app.core.network.interceptor

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import ru.coincorn.app.BuildConfig
import java.io.IOException
import javax.inject.Inject

class UserAgentInterceptor @Inject constructor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val userAgentString = getUserAgentString()
        builder.addHeader("User-Agent", userAgentString)
        return chain.proceed(builder.build())
    }

    private fun getUserAgentString(): String = try {
        "CoinCornApp/" +
                BuildConfig.VERSION_NAME +
                " (Linux; Android " +
                Build.VERSION.RELEASE +
                "; " +
                Build.MODEL +
                " Build/" +
                Build.VERSION.CODENAME +
                ")"
    } catch (ignored: Throwable) {
        ""
    }
}