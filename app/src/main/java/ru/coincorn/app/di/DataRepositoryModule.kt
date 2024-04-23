package ru.coincorn.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.coincorn.app.featureAccount.data.repository.AccountRepositoryImpl
import ru.coincorn.app.featureAccount.domain.repository.AccountRepository
import ru.coincorn.app.featureAuth.data.repository.AuthRepositoryImpl
import ru.coincorn.app.featureAuth.data.repository.CredentialsRepositoryImpl
import ru.coincorn.app.featureAuth.domain.repository.AuthRepository
import ru.coincorn.app.featureAuth.domain.repository.CredentialsRepository
import ru.coincorn.app.featureProfile.data.repository.UserRepositoryImpl
import ru.coincorn.app.featureProfile.domain.repository.UserRepository

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

    @Binds
    fun bindAccountRepository(
        repositoryImpl: AccountRepositoryImpl
    ): AccountRepository

    @Binds
    fun bindUserRepository(
        repositoryImpl: UserRepositoryImpl
    ): UserRepository

}