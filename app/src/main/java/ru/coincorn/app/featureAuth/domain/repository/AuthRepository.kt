package ru.coincorn.app.featureAuth.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.coincorn.app.featureAuth.data.response.AuthStep

interface AuthRepository {

    suspend fun signUp(
        name: String,
        email: String,
        password: String,
    ): Flow<Boolean>

    suspend fun signIn(
        email: String,
        password: String
    ): Flow<Boolean>

    suspend fun restorePassword(email: String)

    suspend fun checkCode(code: String)

    suspend fun fetchAuthStep(): Flow<AuthStep>

    suspend fun signOut()
}