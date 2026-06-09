package com.example.alumnihivev11.network

import android.content.Context
import com.example.alumnihivev11.data.dummy.DummyDataFactory
import com.example.alumnihivev11.data.models.Blog
import com.example.alumnihivev11.data.models.ChatUser
import com.example.alumnihivev11.data.models.Community
import com.example.alumnihivev11.data.models.Event
import com.example.alumnihivev11.data.models.Mentorship
import com.example.alumnihivev11.data.models.Notification
import com.example.alumnihivev11.data.models.Question
import com.example.alumnihivev11.data.models.User
import com.example.alumnihivev11.network.dto.*
import kotlinx.coroutines.flow.first

data class AuthOutcome(
    val token: String,
    val user: User,
    val message: String? = null
)

data class MutationOutcome(
    val message: String,
    val user: User? = null
)

class BackendException(message: String) : Exception(message)

class BackendRepository private constructor(
    private val sessionManager: SessionManager,
    private val api: BackendApi
) {

    suspend fun login(email: String, password: String): AuthOutcome {
        val response = api.login(ApiAuthRequestDto(email = email, password = password))
        return handleAuthResponse(response)
    }

    suspend fun loginAsTestUser(): AuthOutcome {
        sessionManager.saveTestSession()
        return AuthOutcome(
            token = "test_token",
            user = DummyDataFactory.getDummyCurrentUser(),
            message = "Logged in as test user"
        )
    }

    private suspend fun isTestUser(): Boolean {
        return sessionManager.isTestUserFlow.first()
    }

    suspend fun register(
        name: String,
        email: String,
        password: String,
        college: String,
        role: String,
        department: String?,
        graduationYear: Int?
    ): MutationOutcome {
        val response = api.register(
            ApiRegisterRequestDto(
                name = name,
                email = email,
                password = password,
                college = college,
                role = role,
                department = department,
                graduationYear = graduationYear
            )
        )

        if (!response.success) {
            throw BackendException(response.message ?: "Registration failed")
        }

        return MutationOutcome(
            message = response.message ?: "Registration successful",
            user = response.user?.toUiUser()
        )
    }

    suspend fun logout() {
        sessionManager.clearSession()
    }

    suspend fun currentUser(): User {
        if (isTestUser()) return DummyDataFactory.getDummyCurrentUser()
        api.getMe().user?.let { return it.toUiUser() }
        sessionManager.currentUser()?.let { return it }
        throw BackendException("Unable to load current user")
    }

    suspend fun getUsers(search: String? = null, role: String? = null, college: String? = null): List<User> {
        if (isTestUser()) return DummyDataFactory.getDummyUsers(20)
        return api.getUsers(search = search, role = role, college = college).users.map(ApiUserDto::toUiUser)
    }

    suspend fun getMentors(search: String? = null, expertise: String? = null): List<User> {
        if (isTestUser()) return DummyDataFactory.getDummyUsers(12)
        return api.getMentors(search = search, expertise = expertise).mentors.map(ApiUserDto::toUiUser)
    }

    suspend fun getNotifications(unreadOnly: Boolean = false): List<Notification> {
        if (isTestUser()) return DummyDataFactory.getDummyNotifications(10)
        return api.getNotifications(unreadOnly = unreadOnly).notifications.map { it.toUiNotification() }
    }

    suspend fun getCommunities(search: String? = null, category: String? = null): List<Community> {
        if (isTestUser()) return DummyDataFactory.getDummyCommunities(12)
        return api.getCommunities(search = search, category = category).communities.map(ApiCommunityDto::toUiCommunity)
    }

    suspend fun getBlogs(search: String? = null, category: String? = null, tag: String? = null, limit: Int = 12): List<Blog> {
        if (isTestUser()) return DummyDataFactory.getDummyBlogs(15)
        return api.getBlogs(search = search, category = category, tag = tag, limit = limit).blogs.map(ApiBlogDto::toUiBlog)
    }

    suspend fun getEvents(status: String? = null, communityId: String? = null): List<Event> {
        if (isTestUser()) return DummyDataFactory.getDummyEvents(10)
        return api.getEvents(status = status, communityId = communityId).events.map(ApiEventDto::toUiEvent)
    }

    suspend fun getQuestions(search: String? = null, category: String? = null, tag: String? = null, sort: String? = null): List<Question> {
        if (isTestUser()) return DummyDataFactory.getDummyQuestions(12)
        return api.getQuestions(search = search, category = category, tag = tag, sort = sort).questions.map { it.toUiQuestion() }
    }

    suspend fun getMentorships(role: String? = null): List<Mentorship> {
        if (isTestUser()) return emptyList()
        return api.getMentorships(role = role).mentorships.map { it.toUiMentorship() }
    }

    suspend fun getMentorshipRequests(): List<Mentorship> {
        if (isTestUser()) return emptyList()
        return api.getMentorshipRequests().requests.map { it.toUiMentorship() }
    }

    suspend fun getChatUsers(): List<ChatUser> {
        return DummyDataFactory.getDummyChatUsers(12)
    }

    private suspend fun handleAuthResponse(response: ApiAuthResponseDto): AuthOutcome {
        if (!response.success || response.token.isNullOrBlank() || response.user == null) {
            throw BackendException(response.message ?: "Authentication failed")
        }

        sessionManager.saveSession(response.token, response.user)
        return AuthOutcome(
            token = response.token,
            user = response.user.toUiUser(),
            message = response.message
        )
    }

    companion object {
        @Volatile
        private var INSTANCE: BackendRepository? = null

        fun getInstance(context: Context): BackendRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BackendRepository(
                    sessionManager = SessionManager(context.applicationContext),
                    api = BackendClient.create(SessionManager(context.applicationContext))
                ).also { INSTANCE = it }
            }
        }
    }
}
