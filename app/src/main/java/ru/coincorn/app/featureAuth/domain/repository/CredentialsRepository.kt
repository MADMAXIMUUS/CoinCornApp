package ru.coincorn.app.featureAuth.domain.repository

interface CredentialsRepository {

    suspend fun getSessionId(): String?
    suspend fun isLogin(): Boolean
    suspend fun saveSessionId(token: String)
    suspend fun deleteSessionId()
}