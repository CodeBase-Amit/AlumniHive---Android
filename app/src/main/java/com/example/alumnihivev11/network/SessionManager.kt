package com.example.alumnihivev11.network

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.alumnihivev11.data.models.User
import com.example.alumnihivev11.network.dto.ApiUserDto
import com.example.alumnihivev11.network.dto.toUiUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.sessionDataStore by preferencesDataStore("session")

class SessionManager(private val context: Context) {
    
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_KEY = stringPreferencesKey("user_data")
    }
    
    val tokenFlow: Flow<String?> = context.sessionDataStore.data
        .map { preferences ->
            preferences[TOKEN_KEY]
        }
    
    val userFlow: Flow<User?> = context.sessionDataStore.data
        .map { preferences ->
            preferences[USER_KEY]?.let { userJson ->
                try {
                    val userDto = Json.decodeFromString<ApiUserDto>(userJson)
                    userDto.toUiUser()
                } catch (e: Exception) {
                    null
                }
            }
        }
    
    suspend fun saveSession(token: String, user: ApiUserDto) {
        context.sessionDataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[USER_KEY] = Json.encodeToString(ApiUserDto.serializer(), user)
        }
    }
    
    suspend fun clearSession() {
        context.sessionDataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(USER_KEY)
        }
    }
    
    suspend fun currentUser(): User? {
        return context.sessionDataStore.data
            .map { preferences ->
                preferences[USER_KEY]?.let { userJson ->
                    try {
                        val userDto = Json.decodeFromString<ApiUserDto>(userJson)
                        userDto.toUiUser()
                    } catch (e: Exception) {
                        null
                    }
                }
            }
            .let { flow ->
                var user: User? = null
                flow.collect { u ->
                    user = u
                }
                user
            }
    }
}
