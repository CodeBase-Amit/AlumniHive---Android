package com.example.alumnihivev11.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.White

@Composable
fun AlumniDirectoryScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedBatch by remember { mutableStateOf("") }

    val allAlumni = DummyDataFactory.getDummyUsers(20)
    val filteredAlumni = allAlumni.filter {
        (searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)) &&
        (selectedBatch.isEmpty() || it.batch?.toString() == selectedBatch)
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
                    text = "Alumni Directory",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Find and connect with alumni from your college",
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
                placeholder = "Search by name...",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(filteredAlumni) { alumni ->
            AlumniCard(alumni)
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SearchScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("all") }

    val blogs = DummyDataFactory.getDummyBlogs(5)
    val communities = DummyDataFactory.getDummyCommunities(5)
    val users = DummyDataFactory.getDummyUsers(5)
    val events = DummyDataFactory.getDummyEvents(5)

    val filteredBlogs = if (searchQuery.isNotEmpty()) {
        blogs.filter { it.title.contains(searchQuery, ignoreCase = true) }
    } else emptyList()

    val filteredCommunities = if (searchQuery.isNotEmpty()) {
        communities.filter { it.name.contains(searchQuery, ignoreCase = true) }
    } else emptyList()

    val filteredUsers = if (searchQuery.isNotEmpty()) {
        users.filter { it.name.contains(searchQuery, ignoreCase = true) }
    } else emptyList()

    val filteredEvents = if (searchQuery.isNotEmpty()) {
        events.filter { it.title.contains(searchQuery, ignoreCase = true) }
    } else emptyList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Search",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Search everything...",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (searchQuery.isNotEmpty()) {
            // Blogs Results
            if (filteredBlogs.isNotEmpty()) {
                item {
                    Text(
                        text = "Blogs",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = IndigoPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(filteredBlogs) { blog ->
                    SearchResultCard(
                        title = blog.title,
                        subtitle = blog.author.name,
                        category = blog.category
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Communities Results
            if (filteredCommunities.isNotEmpty()) {
                item {
                    Text(
                        text = "Communities",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = IndigoPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(filteredCommunities) { community ->
                    SearchResultCard(
                        title = community.name,
                        subtitle = "${community.totalMembers} members",
                        category = community.category
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Users Results
            if (filteredUsers.isNotEmpty()) {
                item {
                    Text(
                        text = "People",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = IndigoPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(filteredUsers) { user ->
                    SearchResultCard(
                        title = user.name,
                        subtitle = user.college,
                        category = user.role
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Events Results
            if (filteredEvents.isNotEmpty()) {
                item {
                    Text(
                        text = "Events",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = IndigoPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(filteredEvents) { event ->
                    SearchResultCard(
                        title = event.title,
                        subtitle = event.location,
                        category = event.status
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        } else {
            item {
                Text(
                    text = "Start typing to search...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray400,
                    modifier = Modifier.padding(vertical = 32.dp)
                )
            }
        }
    }
}

@Composable
fun AlumniCard(alumnus: com.example.alumnihivev11.data.models.User) {
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
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserAvatar(
                    imageUrl = alumnus.avatar,
                    userName = alumnus.name,
                    size = 48
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = alumnus.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = alumnus.college,
                        style = MaterialTheme.typography.labelSmall,
                        color = Gray400
                    )
                    Text(
                        text = "Batch: ${alumnus.batch}",
                        style = MaterialTheme.typography.labelSmall,
                        color = IndigoPrimary
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
                tint = Gray400
            )
        }
    }
}

@Composable
fun SearchResultCard(title: String, subtitle: String, category: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
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
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = Gray400
                )
            }
            Text(
                text = category.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.labelSmall,
                color = IndigoPrimary
            )
        }
    }
}

