package ru.coincorn.app.featureAuth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import ru.coincorn.app.core.dataStore.Constants.SESSION_TOKEN
import ru.coincorn.app.core.dataStore.get
import ru.coincorn.app.core.dataStore.save
import ru.coincorn.app.featureAuth.domain.repository.CredentialsRepository
import javax.inject.Inject

class CredentialsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : CredentialsRepository {

    override suspend fun getSessionId(): String? {
        return dataStore.get(SESSION_TOKEN)
    }

    override suspend fun isLogin(): Boolean {
        val token = getSessionId()
        return token != null
    }

    override suspend fun saveSessionId(token: String) {
        dataStore.save(SESSION_TOKEN, token)
    }

    override suspend fun deleteSessionId() {
        dataStore.edit { preferences ->
            preferences.remove(SESSION_TOKEN)
        }
    }
}