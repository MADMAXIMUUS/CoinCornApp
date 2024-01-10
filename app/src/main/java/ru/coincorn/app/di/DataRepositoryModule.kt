package ru.coincorn.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.coincorn.app.featureAuth.data.repository.AuthRepositoryImpl
import ru.coincorn.app.featureAuth.data.repository.CredentialsRepositoryImpl
import ru.coincorn.app.featureAuth.domain.repository.AuthRepository
import ru.coincorn.app.featureAuth.domain.repository.CredentialsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataRepositoryModule {

    @Binds
    fun bindAuthRepository(
        repositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindCredentialsRepository(
        repositoryImpl: CredentialsRepositoryImpl
    ): CredentialsRepository

}