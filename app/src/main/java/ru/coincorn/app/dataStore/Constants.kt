package ru.coincorn.app.dataStore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    const val USER_PREFERENCES = "user_preferences"

    val SESSION_TOKEN = stringPreferencesKey("session_id")
    val INTRO = booleanPreferencesKey("is_intro_showed")

}