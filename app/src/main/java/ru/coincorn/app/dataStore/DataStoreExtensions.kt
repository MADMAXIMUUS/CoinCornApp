package ru.coincorn.app.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

suspend fun <T : Any> DataStore<Preferences>.save(
    key: Preferences.Key<T>,
    data: T
) {
    edit { preferences ->
        preferences[key] = data
    }
}

suspend fun <T : Any> DataStore<Preferences>.get(
    key: Preferences.Key<T>
): T? {
    return data.map { preferences ->
        preferences[key]
    }.firstOrNull()
}

suspend fun DataStore<Preferences>.contains(
    key: Preferences.Key<Any>
): Boolean {
    return data.map { preferences ->
        preferences[key]
    }.firstOrNull() != null
}

suspend fun DataStore<Preferences>.remove(
    key: Preferences.Key<Any>
) {
    edit { preferences ->
        preferences.remove(key)
    }
}