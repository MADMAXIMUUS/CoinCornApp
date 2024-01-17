package ru.coincorn.app.core.dataStore

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

suspend fun <T : Any> DataStore<Preferences>.contains(
    key: Preferences.Key<T>
): Boolean {
    return data.map { preferences ->
        preferences[key]
    }.firstOrNull() != null
}

suspend fun <T : Any> DataStore<Preferences>.remove(
    key: Preferences.Key<T>
) {
    edit { preferences ->
        preferences.remove(key)
    }
}