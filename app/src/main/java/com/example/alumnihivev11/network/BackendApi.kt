package com.example.alumnihivev11.network

import com.example.alumnihivev11.network.dto.*
import retrofit2.http.*

interface BackendApi {

    // ==================== Authentication ====================

    @POST("auth/login")
    suspend fun login(@Body request: ApiAuthRequestDto): ApiAuthResponseDto

    @POST("auth/register")
    suspend fun register(@Body request: ApiRegisterRequestDto): ApiAuthResponseDto

    @GET("auth/me")
    suspend fun getMe(): ApiUserResponseDto

    // ==================== Users ====================

    @GET("users")
    suspend fun getUsers(
        @Query("search") search: String? = null,
        @Query("role") role: String? = null,
        @Query("college") college: String? = null
    ): ApiUsersResponseDto

    @GET("users/mentors")
    suspend fun getMentors(
        @Query("search") search: String? = null,
        @Query("expertise") expertise: String? = null
    ): ApiMentorsResponseDto

    // ==================== Communities ====================

    @GET("communities")
    suspend fun getCommunities(
        @Query("search") search: String? = null,
        @Query("category") category: String? = null
    ): ApiCommunitiesResponseDto

    // ==================== Blogs ====================

    @GET("blogs")
    suspend fun getBlogs(
        @Query("search") search: String? = null,
        @Query("category") category: String? = null,
        @Query("tag") tag: String? = null,
        @Query("limit") limit: Int = 12
    ): ApiBlogsResponseDto

    // ==================== Events ====================

    @GET("events")
    suspend fun getEvents(
        @Query("status") status: String? = null,
        @Query("communityId") communityId: String? = null
    ): ApiEventsResponseDto

    // ==================== Questions ====================

    @GET("qa")
    suspend fun getQuestions(
        @Query("search") search: String? = null,
        @Query("category") category: String? = null,
        @Query("tag") tag: String? = null,
        @Query("sort") sort: String? = null
    ): ApiQuestionsResponseDto

    // ==================== Notifications ====================

    @GET("users/notifications")
    suspend fun getNotifications(
        @Query("unreadOnly") unreadOnly: Boolean = false
    ): ApiNotificationsResponseDto

    // ==================== Mentorships ====================

    @GET("mentorship/my-mentorships")
    suspend fun getMentorships(
        @Query("role") role: String? = null
    ): ApiMentorshipsResponseDto

    @GET("mentorship/requests")
    suspend fun getMentorshipRequests(): ApiMentorshipRequestsResponseDto
}

