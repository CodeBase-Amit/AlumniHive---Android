package com.example.alumnihivev11.network

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.alumnihivev11.data.models.User
import com.example.alumnihivev11.data.dummy.DummyDataFactory
import com.example.alumnihivev11.network.dto.ApiUserDto
import com.example.alumnihivev11.network.dto.toUiUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.sessionDataStore by preferencesDataStore("session")

class SessionManager(private val context: Context) {
    
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_KEY = stringPreferencesKey("user_data")
        private val IS_TEST_USER_KEY = booleanPreferencesKey("is_test_user")
    }
    
    val tokenFlow: Flow<String?> = context.sessionDataStore.data
        .map { preferences ->
            preferences[TOKEN_KEY]
        }
    
    val userFlow: Flow<User?> = context.sessionDataStore.data
        .map { preferences ->
            preferences[USER_KEY]?.let { userJson ->
                try {
                    if (preferences[IS_TEST_USER_KEY] == true) {
                        DummyDataFactory.getDummyCurrentUser()
                    } else {
                        val userDto = Json.decodeFromString<ApiUserDto>(userJson)
                        userDto.toUiUser()
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }
    
    val isTestUserFlow: Flow<Boolean> = context.sessionDataStore.data
        .map { preferences ->
            preferences[IS_TEST_USER_KEY] ?: false
        }
    
    suspend fun saveSession(token: String, user: ApiUserDto) {
        context.sessionDataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[USER_KEY] = Json.encodeToString(ApiUserDto.serializer(), user)
            preferences[IS_TEST_USER_KEY] = false
        }
    }
    
    suspend fun saveTestSession() {
        context.sessionDataStore.edit { preferences ->
            preferences[TOKEN_KEY] = "test_token"
            preferences[USER_KEY] = Json.encodeToString(
                ApiUserDto.serializer(),
                ApiUserDto(
                    id = DummyDataFactory.getDummyCurrentUser().id,
                    name = DummyDataFactory.getDummyCurrentUser().name,
                    email = DummyDataFactory.getDummyCurrentUser().email,
                    avatar = DummyDataFactory.getDummyCurrentUser().avatar,
                    bio = DummyDataFactory.getDummyCurrentUser().bio,
                    role = DummyDataFactory.getDummyCurrentUser().role,
                    college = DummyDataFactory.getDummyCurrentUser().college,
                    department = DummyDataFactory.getDummyCurrentUser().department,
                    graduationYear = DummyDataFactory.getDummyCurrentUser().batch,
                    skills = DummyDataFactory.getDummyCurrentUser().skills,
                    interests = DummyDataFactory.getDummyCurrentUser().interests,
                    isOnline = true,
                    linkedIn = DummyDataFactory.getDummyCurrentUser().linkedIn,
                    github = DummyDataFactory.getDummyCurrentUser().github,
                    portfolio = DummyDataFactory.getDummyCurrentUser().portfolio
                )
            )
            preferences[IS_TEST_USER_KEY] = true
        }
    }
    
    suspend fun clearSession() {
        context.sessionDataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(USER_KEY)
            preferences.remove(IS_TEST_USER_KEY)
        }
    }
    
    suspend fun currentUser(): User? {
        return context.sessionDataStore.data
            .map { preferences ->
                preferences[USER_KEY]?.let { userJson ->
                    try {
                        if (preferences[IS_TEST_USER_KEY] == true) {
                            DummyDataFactory.getDummyCurrentUser()
                        } else {
                            val userDto = Json.decodeFromString<ApiUserDto>(userJson)
                            userDto.toUiUser()
                        }
                    } catch (e: Exception) {
                        null
                    }
                }
            }
            .first()
    }
}
