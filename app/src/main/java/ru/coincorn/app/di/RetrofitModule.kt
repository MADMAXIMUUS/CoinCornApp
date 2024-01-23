package ru.coincorn.app.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.coincorn.app.BuildConfig
import ru.coincorn.app.core.network.call.NetworkResponseAdapterFactory
import ru.coincorn.app.core.network.interceptor.AuthInterceptor
import ru.coincorn.app.core.network.interceptor.UserAgentInterceptor
import ru.coincorn.app.featureAuth.domain.repository.CredentialsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        authInterceptor: AuthInterceptor,
        userAgentInterceptor: UserAgentInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(authInterceptor)
            .addInterceptor(userAgentInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(credentialsRepository: CredentialsRepository): AuthInterceptor =
        AuthInterceptor(credentialsRepository)

    @Singleton
    @Provides
    fun provideUserAgentInterceptor(): UserAgentInterceptor = UserAgentInterceptor()
}