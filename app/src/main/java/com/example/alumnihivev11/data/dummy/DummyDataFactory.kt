package com.example.alumnihivev11.data.dummy

import com.example.alumnihivev11.data.models.*
import java.text.SimpleDateFormat
import java.util.*

object DummyDataFactory {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    private fun getRandomUser(index: Int): User {
        val names = listOf(
            "John Doe", "Jane Smith", "Amit Kumar", "Priya Singh", "Rahul Gupta",
            "Sneha Sharma", "Arjun Patel", "Divya Reddy", "Vikram Joshi", "Neha Verma"
        )
        val avatars = listOf(
            "https://api.dicebear.com/7.x/avataaars/svg?seed=john",
            "https://api.dicebear.com/7.x/avataaars/svg?seed=jane",
            "https://api.dicebear.com/7.x/avataaars/svg?seed=amit",
            "https://api.dicebear.com/7.x/avataaars/svg?seed=priya",
            "https://api.dicebear.com/7.x/avataaars/svg?seed=rahul"
        )
        val roles = listOf("student", "alumni")
        val colleges = listOf("IIT Delhi", "BITS Pilani", "NIT Bombay", "Delhi University", "Manipal University")
        val departments = listOf("Computer Science", "Electronics", "Mechanical", "Civil", "Chemical")

        return User(
            id = "user_$index",
            name = names[index % names.size],
            email = "user${index}@college.edu",
            avatar = avatars[index % avatars.size],
            bio = "Passionate developer and technologist",
            role = roles[index % roles.size],
            college = colleges[index % colleges.size],
            department = departments[index % departments.size],
            batch = 2020 + (index % 4),
            skills = listOf("Kotlin", "Android", "Jetpack Compose", "Problem Solving"),
            interests = listOf("Technology", "Education", "Networking"),
            isOnline = index % 3 == 0
        )
    }

    fun getDummyUsers(count: Int = 15): List<User> {
        return (0 until count).map { getRandomUser(it) }
    }

    fun getDummyCurrentUser(): User {
        return User(
            id = "current_user",
            name = "Your Name",
            email = "you@college.edu",
            avatar = "https://api.dicebear.com/7.x/avataaars/svg?seed=you",
            bio = "Passionate developer and lifelong learner",
            role = "student",
            college = "IIT Delhi",
            department = "Computer Science",
            batch = 2024,
            skills = listOf("Kotlin", "Android", "Jetpack Compose", "FlutterFlow", "Web Development"),
            interests = listOf("Technology", "Entrepreneurship", "AI/ML", "Open Source")
        )
    }

    fun getDummyCommunities(count: Int = 12): List<Community> {
        val names = listOf(
            "Web Development", "Mobile Development", "Data Science", "UI/UX Design",
            "DevOps & Cloud", "Machine Learning", "Competitive Programming", "Gaming Enthusiasts",
            "Photography Club", "Startup Ideas", "Music Lovers", "Book Club"
        )
        val categories = listOf("technology", "career", "hobby", "academic", "sports", "arts")
        val avatars = listOf(
            "https://api.dicebear.com/7.x/icons/svg?seed=web",
            "https://api.dicebear.com/7.x/icons/svg?seed=mobile",
            "https://api.dicebear.com/7.x/icons/svg?seed=data",
            "https://api.dicebear.com/7.x/icons/svg?seed=design",
            "https://api.dicebear.com/7.x/icons/svg?seed=devops",
            "https://api.dicebear.com/7.x/icons/svg?seed=ml"
        )

        return (0 until count).map { index ->
            Community(
                id = "community_$index",
                name = names[index % names.size],
                description = "Join our community to connect with like-minded people interested in ${names[index % names.size].lowercase()}",
                avatar = avatars[index % avatars.size],
                category = categories[index % categories.size],
                totalMembers = 100 + (index * 50),
                isJoined = index % 2 == 0,
                isPrivate = index % 5 == 0,
                createdBy = "user_${index % 10}"
            )
        }
    }

    fun getDummyBlogs(count: Int = 15): List<Blog> {
        val titles = listOf(
            "Getting Started with Jetpack Compose",
            "A Deep Dive into Kotlin Coroutines",
            "Modern Android Development Best Practices",
            "Building Scalable Mobile Apps",
            "The Future of Mobile Development",
            "Clean Architecture in Android",
            "State Management in Jetpack Compose",
            "Performance Optimization Tips",
            "Cloud Integration for Mobile Apps",
            "Testing Strategies for Android Apps"
        )
        val categories = listOf("technology", "career", "education", "lifestyle")

        return (0 until count).map { index ->
            Blog(
                id = "blog_$index",
                title = titles[index % titles.size],
                excerpt = "Learn the essentials and best practices of modern Android development with Jetpack Compose",
                content = "This comprehensive guide covers everything you need to know about building beautiful and efficient Android apps...",
                author = getRandomUser(index % 10),
                coverImage = "https://picsum.photos/400/250?random=$index",
                category = categories[index % categories.size],
                readTime = 5 + (index % 15),
                likes = 50 + (index * 10),
                comments = 8 + (index % 20),
                createdAt = dateFormat.format(Date(System.currentTimeMillis() - index * 86400000)),
                slug = "blog-${index}"
            )
        }
    }

    fun getDummyEvents(count: Int = 10): List<Event> {
        val titles = listOf(
            "Tech Talk: AI/ML Fundamentals",
            "Android Development Workshop",
            "Networking Event 2024",
            "Hackathon Competition",
            "Web Development Masterclass",
            "Career Fair 2024",
            "Alumni Meetup",
            "Product Demo Day",
            "Design Workshop",
            "Startup Pitch Event"
        )
        val statuses = listOf("upcoming", "upcoming", "upcoming", "ongoing", "completed")

        return (0 until count).map { index ->
            val daysFromNow = -2 + (index % 7)
            val startTime = System.currentTimeMillis() + (daysFromNow * 86400000)

            Event(
                id = "event_$index",
                title = titles[index % titles.size],
                description = "Join us for an exciting session on ${titles[index % titles.size].lowercase()}. Network with like-minded professionals!",
                startDate = dateFormat.format(Date(startTime)),
                endDate = dateFormat.format(Date(startTime + 3600000)),
                location = if (index % 2 == 0) "Virtual" else "Delhi Campus",
                locationType = if (index % 2 == 0) "online" else "offline",
                image = "https://picsum.photos/500/300?random=${index}",
                attendees = 50 + (index * 20),
                isRegistered = index % 3 == 0,
                organizer = getRandomUser(index % 10),
                status = statuses[index % statuses.size]
            )
        }
    }

    fun getDummyQuestions(count: Int = 12): List<Question> {
        val titles = listOf(
            "How to implement MVVM in Jetpack Compose?",
            "Best practices for handling state in Compose",
            "How to optimize Compose performance?",
            "How to handle navigation in Compose?",
            "What is the best way to structure a Compose app?",
            "How to implement custom themes in Compose?",
            "Tips for debugging Compose apps",
            "How to integrate with existing XML layouts?",
            "What are Composable functions?",
            "How to handle user input in Compose?"
        )
        val categories = listOf("android", "compose", "kotlin", "architecture", "ui")

        return (0 until count).map { index ->
            Question(
                id = "question_$index",
                title = titles[index % titles.size],
                description = "I'm trying to implement this feature but facing some challenges. Can someone help?",
                author = getRandomUser(index % 10),
                category = categories[index % categories.size],
                tags = listOf("android", "compose", "kotlin"),
                answers = 2 + (index % 10),
                views = 100 + (index * 50),
                upvotes = 5 + (index % 20),
                createdAt = dateFormat.format(Date(System.currentTimeMillis() - index * 3600000)),
                isAnswered = index % 2 == 0
            )
        }
    }

    fun getDummyChatUsers(count: Int = 12): List<ChatUser> {
        val names = listOf(
            "John Doe", "Jane Smith", "Amit Kumar", "Priya Singh", "Rahul Gupta",
            "Sneha Sharma", "Arjun Patel", "Divya Reddy", "Vikram Joshi", "Neha Verma"
        )
        val messages = listOf(
            "Hey! How are you?",
            "Did you complete the assignment?",
            "Let's catch up soon!",
            "Thanks for your help!",
            "See you at the meetup"
        )

        return (0 until count).map { index ->
            ChatUser(
                id = "user_$index",
                name = names[index % names.size],
                avatar = "https://api.dicebear.com/7.x/avataaars/svg?seed=${names[index % names.size]}",
                lastMessage = messages[index % messages.size],
                lastMessageTime = "${index + 1}h ago",
                unreadCount = if (index % 3 == 0) index % 5 else 0,
                isOnline = index % 2 == 0
            )
        }
    }

    fun getDummyNotifications(count: Int = 10): List<Notification> {
        val titles = listOf(
            "New Message from John",
            "You joined a community",
            "Event registration confirmed",
            "New comment on your blog",
            "Mentorship request from Priya",
            "Someone replied to your question",
            "New event in Web Development community",
            "Your event is starting soon"
        )
        val types = listOf("message", "event", "community", "mention", "blog")

        return (0 until count).map { index ->
            Notification(
                id = "notification_$index",
                type = types[index % types.size],
                title = titles[index % titles.size],
                message = "You have a new notification. Tap to view details.",
                actor = if (index % 2 == 0) getRandomUser(index % 10) else null,
                timestamp = "${index + 1}m ago",
                isRead = index > 5
            )
        }
    }
}

