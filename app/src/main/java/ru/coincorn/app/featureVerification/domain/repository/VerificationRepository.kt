package ru.coincorn.app.featureVerification.domain.repository

import kotlinx.coroutines.flow.Flow

interface VerificationRepository {

    suspend fun resendEmail(): Flow<Boolean>

    suspend fun verify(
        code: String
    ): Flow<Boolean>
}