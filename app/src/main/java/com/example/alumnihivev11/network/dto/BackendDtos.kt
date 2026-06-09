package com.example.alumnihivev11.network.dto

import com.example.alumnihivev11.data.models.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ==================== Request DTOs ====================

@Serializable
data class ApiAuthRequestDto(
    val email: String,
    val password: String
)

@Serializable
data class ApiRegisterRequestDto(
    val name: String,
    val email: String,
    val password: String,
    val college: String,
    val role: String,
    val department: String? = null,
    @SerialName("graduation_year")
    val graduationYear: Int? = null
)

// ==================== Response DTOs ====================

@Serializable
data class ApiAuthResponseDto(
    val success: Boolean,
    val token: String? = null,
    val user: ApiUserDto? = null,
    val message: String? = null
)

@Serializable
data class ApiUserDto(
    val id: String,
    val name: String,
    val email: String,
    val avatar: String = "",
    val bio: String? = null,
    val role: String,
    val college: String,
    val department: String? = null,
    val batch: Int? = null,
    val skills: List<String> = emptyList(),
    val interests: List<String> = emptyList(),
    @SerialName("is_online")
    val isOnline: Boolean = false,
    @SerialName("linkedin")
    val linkedIn: String? = null,
    val github: String? = null,
    val portfolio: String? = null
)

fun ApiUserDto.toUiUser(): User = User(
    id = id,
    name = name,
    email = email,
    avatar = avatar,
    bio = bio,
    role = role,
    college = college,
    department = department,
    batch = batch,
    skills = skills,
    interests = interests,
    isOnline = isOnline,
    linkedIn = linkedIn,
    github = github,
    portfolio = portfolio
)

@Serializable
data class ApiBlogDto(
    val id: String,
    val title: String,
    val excerpt: String,
    val content: String,
    val author: ApiUserDto,
    @SerialName("cover_image")
    val coverImage: String? = null,
    val category: String,
    @SerialName("read_time")
    val readTime: Int = 5,
    val likes: Int = 0,
    val comments: Int = 0,
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("is_liked")
    val isLiked: Boolean = false,
    val slug: String = ""
)

fun ApiBlogDto.toUiBlog(): Blog = Blog(
    id = id,
    title = title,
    excerpt = excerpt,
    content = content,
    author = author.toUiUser(),
    coverImage = coverImage,
    category = category,
    readTime = readTime,
    likes = likes,
    comments = comments,
    createdAt = createdAt,
    isLiked = isLiked,
    slug = slug
)

@Serializable
data class ApiCommunityDto(
    val id: String,
    val name: String,
    val description: String,
    val avatar: String = "",
    val category: String,
    @SerialName("total_members")
    val totalMembers: Int = 0,
    @SerialName("is_joined")
    val isJoined: Boolean = false,
    @SerialName("is_private")
    val isPrivate: Boolean = false,
    @SerialName("created_by")
    val createdBy: String = "",
    @SerialName("recent_posts")
    val recentPosts: Int = 0
)

fun ApiCommunityDto.toUiCommunity(): Community = Community(
    id = id,
    name = name,
    description = description,
    avatar = avatar,
    category = category,
    totalMembers = totalMembers,
    isJoined = isJoined,
    isPrivate = isPrivate,
    createdBy = createdBy,
    recentPosts = recentPosts
)

@Serializable
data class ApiEventDto(
    val id: String,
    val title: String,
    val description: String,
    @SerialName("start_date")
    val startDate: String,
    @SerialName("end_date")
    val endDate: String,
    val location: String,
    @SerialName("location_type")
    val locationType: String = "online",
    val image: String? = null,
    val attendees: Int = 0,
    @SerialName("is_registered")
    val isRegistered: Boolean = false,
    val organizer: ApiUserDto,
    val status: String = "upcoming"
)

fun ApiEventDto.toUiEvent(): Event = Event(
    id = id,
    title = title,
    description = description,
    startDate = startDate,
    endDate = endDate,
    location = location,
    locationType = locationType,
    image = image,
    attendees = attendees,
    isRegistered = isRegistered,
    organizer = organizer.toUiUser(),
    status = status
)

@Serializable
data class ApiQuestionDto(
    val id: String,
    val title: String,
    val description: String,
    val author: ApiUserDto,
    val category: String,
    val tags: List<String> = emptyList(),
    val answers: Int = 0,
    val views: Int = 0,
    val upvotes: Int = 0,
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("is_answered")
    val isAnswered: Boolean = false
)

fun ApiQuestionDto.toUiQuestion(): Question = Question(
    id = id,
    title = title,
    description = description,
    author = author.toUiUser(),
    category = category,
    tags = tags,
    answers = answers,
    views = views,
    upvotes = upvotes,
    createdAt = createdAt,
    isAnswered = isAnswered
)

@Serializable
data class ApiNotificationDto(
    val id: String,
    val type: String,
    val title: String,
    val message: String,
    val actor: ApiUserDto? = null,
    val timestamp: String = "",
    @SerialName("is_read")
    val isRead: Boolean = false,
    val link: String? = null
)

fun ApiNotificationDto.toUiNotification(): Notification = Notification(
    id = id,
    type = type,
    title = title,
    message = message,
    actor = actor?.toUiUser(),
    timestamp = timestamp,
    isRead = isRead,
    link = link
)

@Serializable
data class ApiMentorshipDto(
    val id: String,
    val mentor: ApiUserDto,
    val mentee: ApiUserDto,
    val status: String,
    @SerialName("start_date")
    val startDate: String = "",
    val goal: String = "",
    val messages: Int = 0
)

fun ApiMentorshipDto.toUiMentorship(): Mentorship = Mentorship(
    id = id,
    mentor = mentor.toUiUser(),
    mentee = mentee.toUiUser(),
    status = status,
    startDate = startDate,
    goal = goal,
    messages = messages
)

// ==================== Response Wrappers ====================

@Serializable
data class ApiUsersResponseDto(
    val success: Boolean = true,
    val users: List<ApiUserDto> = emptyList()
)

@Serializable
data class ApiMentorsResponseDto(
    val success: Boolean = true,
    val mentors: List<ApiUserDto> = emptyList()
)

@Serializable
data class ApiUserResponseDto(
    val success: Boolean = true,
    val user: ApiUserDto? = null
)

@Serializable
data class ApiBlogsResponseDto(
    val success: Boolean = true,
    val blogs: List<ApiBlogDto> = emptyList()
)

@Serializable
data class ApiCommunitiesResponseDto(
    val success: Boolean = true,
    val communities: List<ApiCommunityDto> = emptyList()
)

@Serializable
data class ApiEventsResponseDto(
    val success: Boolean = true,
    val events: List<ApiEventDto> = emptyList()
)

@Serializable
data class ApiQuestionsResponseDto(
    val success: Boolean = true,
    val questions: List<ApiQuestionDto> = emptyList()
)

@Serializable
data class ApiNotificationsResponseDto(
    val success: Boolean = true,
    val notifications: List<ApiNotificationDto> = emptyList()
)

@Serializable
data class ApiMentorshipsResponseDto(
    val success: Boolean = true,
    val mentorships: List<ApiMentorshipDto> = emptyList()
)

@Serializable
data class ApiMentorshipRequestsResponseDto(
    val success: Boolean = true,
    val requests: List<ApiMentorshipDto> = emptyList()
)

