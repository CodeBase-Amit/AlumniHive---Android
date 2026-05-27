package com.example.alumnihivev11.data.models

data class User(
    val id: String,
    val name: String,
    val email: String,
    val avatar: String,
    val bio: String? = null,
    val role: String, // "student" or "alumni"
    val college: String,
    val department: String? = null,
    val batch: Int? = null,
    val skills: List<String> = emptyList(),
    val interests: List<String> = emptyList(),
    val isOnline: Boolean = false,
    val linkedIn: String? = null,
    val github: String? = null,
    val portfolio: String? = null
)

data class Community(
    val id: String,
    val name: String,
    val description: String,
    val avatar: String,
    val category: String,
    val totalMembers: Int,
    val isJoined: Boolean = false,
    val isPrivate: Boolean = false,
    val createdBy: String,
    val recentPosts: Int = 0
)

data class Blog(
    val id: String,
    val title: String,
    val excerpt: String,
    val content: String,
    val author: User,
    val coverImage: String? = null,
    val category: String,
    val readTime: Int,
    val likes: Int = 0,
    val comments: Int = 0,
    val createdAt: String,
    val isLiked: Boolean = false,
    val slug: String
)

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val location: String,
    val locationType: String, // "online" or "offline"
    val image: String? = null,
    val attendees: Int = 0,
    val isRegistered: Boolean = false,
    val organizer: User,
    val status: String // "upcoming", "ongoing", "completed"
)

data class Question(
    val id: String,
    val title: String,
    val description: String,
    val author: User,
    val category: String,
    val tags: List<String>,
    val answers: Int = 0,
    val views: Int = 0,
    val upvotes: Int = 0,
    val createdAt: String,
    val isAnswered: Boolean = false
)

data class ChatMessage(
    val id: String,
    val senderId: String,
    val senderName: String,
    val senderAvatar: String,
    val content: String,
    val timestamp: String,
    val isRead: Boolean = true
)

data class ChatUser(
    val id: String,
    val name: String,
    val avatar: String,
    val lastMessage: String,
    val lastMessageTime: String,
    val unreadCount: Int = 0,
    val isOnline: Boolean = false
)

data class Mentorship(
    val id: String,
    val mentor: User,
    val mentee: User,
    val status: String, // "pending", "active", "completed"
    val startDate: String,
    val goal: String,
    val messages: Int = 0
)

data class Notification(
    val id: String,
    val type: String, // "message", "event", "community", "mention", "blog"
    val title: String,
    val message: String,
    val actor: User?,
    val timestamp: String,
    val isRead: Boolean = false,
    val link: String? = null
)

