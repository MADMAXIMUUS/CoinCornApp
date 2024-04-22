package ru.coincorn.app.featureAuth.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.coincorn.app.featureAuth.data.response.AuthStep

interface AuthRepository {

    suspend fun auth(
        email: String,
    ): Flow<Unit>

    suspend fun resendEmail(): Flow<Unit>

    suspend fun verify(
        code: String,
        email: String
    ): Flow<Unit>

    suspend fun fetchAuthStep(): Flow<AuthStep>

    suspend fun signOut()
}