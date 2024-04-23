package ru.coincorn.app.featureProfile.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun updateName(
        name: String
    ): Flow<Unit>

    fun updateToken(
        token: String
    ): Flow<Unit>
}