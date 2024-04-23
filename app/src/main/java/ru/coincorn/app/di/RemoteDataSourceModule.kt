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
import ru.coincorn.app.featureProfile.data.api.UserApi
import ru.coincorn.app.featureProfile.data.dataSource.UserRemoteDataSource
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

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(userApi: UserApi): UserRemoteDataSource {
        return UserRemoteDataSource(userApi)
    }
}