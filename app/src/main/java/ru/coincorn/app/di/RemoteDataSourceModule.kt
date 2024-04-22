package ru.coincorn.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.coincorn.app.featureAccount.data.api.AccountApi
import ru.coincorn.app.featureAccount.data.dataSource.AccountRemoteDataSource
import ru.coincorn.app.featureAuth.data.api.AuthApi
import ru.coincorn.app.featureAuth.data.dataSource.AuthRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(authApi: AuthApi): AuthRemoteDataSource {
        return AuthRemoteDataSource(authApi)
    }

    @Provides
    @Singleton
    fun provideAccountApi(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAccountRemoteDataSource(accountApi: AccountApi): AccountRemoteDataSource {
        return AccountRemoteDataSource(accountApi)
    }

    /*@Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserRemoteDataSource {
        return retrofit.create(UserRemoteDataSource::class.java)
    }*/
}