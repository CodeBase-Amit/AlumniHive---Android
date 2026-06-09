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
    val graduationYear: Int? = null
)

// ==================== Sub-DTOs for nested backend structures ====================

@Serializable
data class EventLocationDto(
    val type: String = "online",
    val venue: String = "",
    val address: String = "",
    val city: String = "",
    val meetingLink: String = ""
)

@Serializable
data class CommunityStatsDto(
    val totalMembers: Int = 0,
    val totalMessages: Int = 0,
    val totalEvents: Int = 0
)

@Serializable
data class ApiBlogCommentDto(
    val content: String = ""
)

@Serializable
data class ApiEventAttendeeDto(
    val status: String = "registered"
)

@Serializable
data class ApiAnswerDto(
    val content: String = ""
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
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val avatar: String = "",
    val bio: String? = null,
    val role: String = "",
    val college: String = "",
    val department: String? = null,
    val graduationYear: Int? = null,
    val skills: List<String> = emptyList(),
    val interests: List<String> = emptyList(),
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
    batch = graduationYear,
    skills = skills,
    interests = interests,
    isOnline = isOnline,
    linkedIn = linkedIn,
    github = github,
    portfolio = portfolio
)

@Serializable
data class ApiBlogDto(
    val id: String = "",
    val title: String = "",
    val excerpt: String = "",
    val content: String = "",
    val author: ApiUserDto = ApiUserDto(),
    val coverImage: String? = null,
    val category: String = "",
    val readTime: Int = 0,
    val likes: List<String> = emptyList(),
    val comments: List<ApiBlogCommentDto> = emptyList(),
    val createdAt: String = "",
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
    likes = likes.size,
    comments = comments.size,
    createdAt = createdAt,
    isLiked = false,
    slug = slug
)

@Serializable
data class ApiCommunityDto(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val avatar: String = "",
    val category: String = "",
    val stats: CommunityStatsDto = CommunityStatsDto(),
    val isPrivate: Boolean = false,
    val creator: ApiUserDto = ApiUserDto(),
    val isActive: Boolean = true
)

fun ApiCommunityDto.toUiCommunity(): Community = Community(
    id = id,
    name = name,
    description = description,
    avatar = avatar,
    category = category,
    totalMembers = stats.totalMembers,
    isJoined = false,
    isPrivate = isPrivate,
    createdBy = creator.name,
    recentPosts = stats.totalEvents
)

@Serializable
data class ApiEventDto(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val location: EventLocationDto = EventLocationDto(),
    val image: String? = null,
    val attendees: List<ApiEventAttendeeDto> = emptyList(),
    val creator: ApiUserDto = ApiUserDto(),
    val status: String = "upcoming"
)

fun ApiEventDto.toUiEvent(): Event = Event(
    id = id,
    title = title,
    description = description,
    startDate = startDate,
    endDate = endDate,
    location = buildEventLocationString(location),
    locationType = location.type,
    image = image,
    attendees = attendees.size,
    isRegistered = false,
    organizer = creator.toUiUser(),
    status = status
)

private fun buildEventLocationString(location: EventLocationDto): String {
    return when (location.type) {
        "online" -> location.meetingLink.ifBlank { "Online" }
        "offline", "hybrid" -> listOfNotNull(location.venue, location.address, location.city)
            .filter { it.isNotBlank() }
            .joinToString(", ")
            .ifEmpty { "Offline" }
        else -> ""
    }
}

@Serializable
data class ApiQuestionDto(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val author: ApiUserDto = ApiUserDto(),
    val category: String = "",
    val tags: List<String> = emptyList(),
    val answers: List<ApiAnswerDto> = emptyList(),
    val views: Int = 0,
    val upvotes: List<String> = emptyList(),
    val createdAt: String = "",
    val isSolved: Boolean = false
)

fun ApiQuestionDto.toUiQuestion(): Question = Question(
    id = id,
    title = title,
    description = content,
    author = author.toUiUser(),
    category = category,
    tags = tags,
    answers = answers.size,
    views = views,
    upvotes = upvotes.size,
    createdAt = createdAt,
    isAnswered = isSolved
)

@Serializable
data class ApiNotificationDto(
    val id: String = "",
    val type: String = "",
    val title: String = "",
    val message: String = "",
    val sender: ApiUserDto? = null,
    val createdAt: String = "",
    val isRead: Boolean = false,
    val link: String? = null
)

fun ApiNotificationDto.toUiNotification(): Notification = Notification(
    id = id,
    type = type,
    title = title,
    message = message,
    actor = sender?.toUiUser(),
    timestamp = createdAt,
    isRead = isRead,
    link = link
)

@Serializable
data class ApiMentorshipDto(
    val id: String = "",
    val mentor: ApiUserDto = ApiUserDto(),
    val mentee: ApiUserDto = ApiUserDto(),
    val status: String = "pending",
    val startDate: String = "",
    val requestMessage: String = "",
    val goals: List<String> = emptyList()
)

fun ApiMentorshipDto.toUiMentorship(): Mentorship = Mentorship(
    id = id,
    mentor = mentor.toUiUser(),
    mentee = mentee.toUiUser(),
    status = status,
    startDate = startDate,
    goal = requestMessage,
    messages = 0
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
