package com.example.alumnihivev11.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alumnihivev11.components.SearchBar
import com.example.alumnihivev11.components.UserAvatar
import com.example.alumnihivev11.data.dummy.DummyDataFactory
import com.example.alumnihivev11.ui.theme.Gray100
import com.example.alumnihivev11.ui.theme.Gray200
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Green
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.White

@Composable
fun ChatListScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val allUsers = DummyDataFactory.getDummyChatUsers(12)
    val filteredUsers = allUsers.filter {
        searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Messages",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
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
    var activeTab by remember { mutableStateOf("find") }
    var searchQuery by remember { mutableStateOf("") }

    val mentors = DummyDataFactory.getDummyUsers(12)
    val filteredMentors = mentors.filter {
        searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(
                        text = "Mentorship",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Connect with experienced mentors for guidance and learning",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray400
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            item {
                // Tab Selection
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    androidx.compose.material3.FilterChip(
                        selected = activeTab == "find",
                        onClick = { activeTab = "find" },
                        label = { Text("Find Mentors") }
                    )
                    androidx.compose.material3.FilterChip(
                        selected = activeTab == "myMentors",
                        onClick = { activeTab = "myMentors" },
                        label = { Text("My Mentors") }
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
    var searchQuery by remember { mutableStateOf("") }
    val allQuestions = DummyDataFactory.getDummyQuestions(12)
    val filteredQuestions = allQuestions.filter {
        searchQuery.isEmpty() || it.title.contains(searchQuery, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    text = "Questions",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Ask and answer questions from the community",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray400
                )
                Spacer(modifier = Modifier.height(16.dp))
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
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BadgedBox(
                    badge = {
                        if (user.isOnline) {
                            Badge(
                                modifier = Modifier
                                    .size(12.dp)
                                    .align(Alignment.TopEnd),
                                containerColor = Green
                            )
                        }
                    }
                ) {
                    UserAvatar(
                        imageUrl = user.avatar,
                        userName = user.name,
                        size = 48
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = user.lastMessage,
                        style = MaterialTheme.typography.labelSmall,
                        color = Gray400,
                        maxLines = 1
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = user.lastMessageTime,
                    style = MaterialTheme.typography.labelSmall,
                    color = Gray400
                )
                if (user.unreadCount > 0) {
                    Badge(
                        containerColor = IndigoPrimary,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = user.unreadCount.toString(),
                            color = White,
                            style = MaterialTheme.typography.labelSmall
                        )
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
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UserAvatar(
                        imageUrl = mentor.avatar,
                        userName = mentor.name,
                        size = 48
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = mentor.name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${mentor.role.uppercase()} • ${mentor.college}",
                            style = MaterialTheme.typography.labelSmall,
                            color = Gray400
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            if (mentor.bio != null) {
                Text(
                    text = mentor.bio,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray400
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            androidx.compose.material3.Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Request Mentorship")
            }
        }
    }
}

@Composable
fun QuestionCard(question: com.example.alumnihivev11.data.models.Question, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = question.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = question.description,
                style = MaterialTheme.typography.bodySmall,
                color = Gray400,
                maxLines = 2
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "Answers",
                            tint = IndigoPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
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
                Text(
                    text = question.category.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelSmall,
                    color = IndigoPrimary
                )
            }
        }
    }
}

