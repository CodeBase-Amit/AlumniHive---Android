package com.example.alumnihivev11.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.navigation.NavController
import com.example.alumnihivev11.components.FilterChip
import com.example.alumnihivev11.components.SearchBar
import com.example.alumnihivev11.components.UserAvatar
import com.example.alumnihivev11.data.models.ChatUser
import com.example.alumnihivev11.data.models.Question
import com.example.alumnihivev11.data.models.User
import com.example.alumnihivev11.network.BackendRepository
import com.example.alumnihivev11.ui.theme.Gray200
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.Green
import com.example.alumnihivev11.ui.theme.Green100
import com.example.alumnihivev11.ui.theme.IndigoLightest
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.SurfaceLight
import com.example.alumnihivev11.ui.theme.White
import com.example.alumnihivev11.ui.theme.Yellow100
import com.example.alumnihivev11.ui.theme.Yellow700

@Composable
fun ChatListScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var allUsers by remember { mutableStateOf<List<ChatUser>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            allUsers = repository.getChatUsers()
        } catch (e: Exception) {
            Log.e("ChatListScreen", "Failed to load chat users", e)
        }
        isLoading = false
    }

    val filteredUsers = allUsers.filter {
        searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize().background(SurfaceLight),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...", color = Gray400)
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
            .padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Messages",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Gray800
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            SearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Search conversations...",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (filteredUsers.isEmpty()) {
            item {
                Text(
                    text = "No conversations found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray400,
                    modifier = Modifier.padding(vertical = 48.dp)
                )
            }
        }

        items(filteredUsers) { chatUser ->
            ChatUserCard(chatUser, onClick = { })
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MentorshipListScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var mentors by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var activeTab by remember { mutableStateOf("find") }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            mentors = repository.getMentors()
        } catch (e: Exception) {
            Log.e("MentorshipListScreen", "Failed to load mentors", e)
        }
        isLoading = false
    }

    val filteredMentors = mentors.filter {
        searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize().background(SurfaceLight),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...", color = Gray400)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Column {
                    Text(
                        text = "Mentorship",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Gray800
                    )
                    Text(
                        text = "Connect with experienced mentors for guidance and learning",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray500
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        label = "Find Mentors",
                        isSelected = activeTab == "find",
                        onClick = { activeTab = "find" }
                    )
                    FilterChip(
                        label = "My Mentors",
                        isSelected = activeTab == "myMentors",
                        onClick = { activeTab = "myMentors" }
                    )
                }
            }

            if (activeTab == "find") {
                item {
                    SearchBar(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = "Search mentors...",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (filteredMentors.isEmpty()) {
                    item {
                        Text(
                            text = "No mentors found",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Gray400,
                            modifier = Modifier.padding(vertical = 48.dp)
                        )
                    }
                }

                items(filteredMentors) { mentor ->
                    MentorCard(mentor, onClick = { })
                }
            } else {
                item {
                    Text(
                        text = "You don't have any active mentorships yet",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray400,
                        modifier = Modifier.padding(vertical = 32.dp)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun QuestionsListScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var allQuestions by remember { mutableStateOf<List<Question>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            allQuestions = repository.getQuestions()
        } catch (e: Exception) {
            Log.e("QuestionsListScreen", "Failed to load questions", e)
        }
        isLoading = false
    }

    val filteredQuestions = allQuestions.filter {
        searchQuery.isEmpty() || it.title.contains(searchQuery, ignoreCase = true)
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize().background(SurfaceLight),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...", color = Gray400)
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
            .padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                Text(
                    text = "Questions",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Gray800
                )
                Text(
                    text = "Ask and answer questions from the community",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray500
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        item {
            SearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Search questions...",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (filteredQuestions.isEmpty()) {
            item {
                Text(
                    text = "No questions found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray400,
                    modifier = Modifier.padding(vertical = 48.dp)
                )
            }
        }

        items(filteredQuestions) { question ->
            QuestionCard(question, onClick = { })
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ChatUserCard(user: com.example.alumnihivev11.data.models.ChatUser, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserAvatar(
                imageUrl = user.avatar,
                userName = user.name,
                size = 52,
                isOnline = user.isOnline
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Gray800
                    )
                    Text(
                        text = user.lastMessageTime,
                        style = MaterialTheme.typography.labelSmall,
                        color = Gray400
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = user.lastMessage,
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray500,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    if (user.unreadCount > 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(IndigoPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = user.unreadCount.toString(),
                                color = White,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MentorCard(mentor: com.example.alumnihivev11.data.models.User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserAvatar(
                    imageUrl = mentor.avatar,
                    userName = mentor.name,
                    size = 56
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = mentor.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Gray800
                    )
                    Text(
                        text = "${mentor.role.uppercase()} \u2022 ${mentor.college}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray500
                    )
                    if (mentor.skills.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            mentor.skills.take(3).forEach { skill ->
                                Text(
                                    text = skill,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = IndigoPrimary,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier
                                        .background(IndigoLightest, RoundedCornerShape(6.dp))
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "View",
                    tint = IndigoPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun QuestionCard(question: com.example.alumnihivev11.data.models.Question, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (question.isAnswered) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Answered",
                        tint = Green,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Answered",
                        style = MaterialTheme.typography.labelSmall,
                        color = Green,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    text = question.category.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelSmall,
                    color = IndigoPrimary,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .background(IndigoLightest, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = question.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Gray800
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = question.description,
                style = MaterialTheme.typography.bodySmall,
                color = Gray500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Forum,
                            contentDescription = "Answers",
                            tint = IndigoPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "${question.answers} answers",
                            style = MaterialTheme.typography.labelSmall,
                            color = Gray400
                        )
                    }
                    Text(
                        text = "${question.views} views",
                        style = MaterialTheme.typography.labelSmall,
                        color = Gray400
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.People,
                        contentDescription = "Upvotes",
                        tint = IndigoPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "${question.upvotes}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Gray400
                    )
                }
            }
        }
    }
}
