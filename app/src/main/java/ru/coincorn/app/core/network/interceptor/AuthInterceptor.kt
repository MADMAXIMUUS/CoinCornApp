package ru.coincorn.app.core.network.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.coincorn.app.featureAuth.domain.repository.CredentialsRepository
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val credentialsRepository: CredentialsRepository,
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        runBlocking {
            if (credentialsRepository.isLogin()) {
                val token = credentialsRepository.getSessionId().orEmpty()
                builder.addHeader("session_id", token)
            }
        }
        builder.addHeader("Accept-Language", Locale.getDefault().language)
        builder.addHeader("Connection", "close")
        return chain.proceed(builder.build())
    }
}