package com.example.alumnihivev11.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.People
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
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.navigation.NavController
import com.example.alumnihivev11.components.SearchBar
import com.example.alumnihivev11.components.UserAvatar
import com.example.alumnihivev11.data.models.Blog
import com.example.alumnihivev11.data.models.Community
import com.example.alumnihivev11.data.models.Event
import com.example.alumnihivev11.data.models.User
import com.example.alumnihivev11.network.BackendRepository
import com.example.alumnihivev11.ui.theme.Blue
import com.example.alumnihivev11.ui.theme.Gray200
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.Gray900
import com.example.alumnihivev11.ui.theme.Green
import com.example.alumnihivev11.ui.theme.IndigoLightest
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.Orange
import com.example.alumnihivev11.ui.theme.SurfaceLight
import com.example.alumnihivev11.ui.theme.White

@Composable
fun AlumniDirectoryScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var allAlumni by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            allAlumni = repository.getUsers(role = "alumni")
        } catch (e: Exception) {
            Log.e("AlumniDirectoryScreen", "Failed to load alumni", e)
        }
        isLoading = false
    }

    val filteredAlumni = allAlumni.filter {
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
            Column {
                Text(
                    text = "Alumni Directory",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
                Text(
                    text = "Find and connect with alumni from your college",
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
                placeholder = "Search by name...",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (filteredAlumni.isEmpty()) {
            item {
                Text(
                    text = "No alumni found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray400,
                    modifier = Modifier.padding(vertical = 48.dp)
                )
            }
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
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var allBlogs by remember { mutableStateOf<List<Blog>>(emptyList()) }
    var allCommunities by remember { mutableStateOf<List<Community>>(emptyList()) }
    var allUsers by remember { mutableStateOf<List<User>>(emptyList()) }
    var allEvents by remember { mutableStateOf<List<Event>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            allBlogs = repository.getBlogs()
            allCommunities = repository.getCommunities()
            allUsers = repository.getUsers()
            allEvents = repository.getEvents()
        } catch (e: Exception) {
            Log.e("SearchScreen", "Failed to load search data", e)
        }
        isLoading = false
    }

    val blogs = allBlogs
    val communities = allCommunities
    val users = allUsers
    val events = allEvents

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
            .background(SurfaceLight)
            .padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Search",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Gray900
            )
            Spacer(modifier = Modifier.height(20.dp))
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
            if (filteredBlogs.isNotEmpty()) {
                item {
                    Text(
                        text = "Blogs",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = IndigoPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                items(filteredBlogs) { blog ->
                    SearchResultCard(
                        title = blog.title,
                        subtitle = blog.author.name,
                        category = blog.category,
                        icon = Icons.Default.Book,
                        color = Blue
                    )
                }
                item { Spacer(modifier = Modifier.height(12.dp)) }
            }

            if (filteredCommunities.isNotEmpty()) {
                item {
                    Text(
                        text = "Communities",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = IndigoPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                items(filteredCommunities) { community ->
                    SearchResultCard(
                        title = community.name,
                        subtitle = "${community.totalMembers} members",
                        category = community.category,
                        icon = Icons.Default.People,
                        color = Green
                    )
                }
                item { Spacer(modifier = Modifier.height(12.dp)) }
            }

            if (filteredUsers.isNotEmpty()) {
                item {
                    Text(
                        text = "People",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = IndigoPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                items(filteredUsers) { user ->
                    SearchResultCard(
                        title = user.name,
                        subtitle = user.college,
                        category = user.role,
                        icon = Icons.Default.People,
                        color = Orange
                    )
                }
                item { Spacer(modifier = Modifier.height(12.dp)) }
            }

            if (filteredEvents.isNotEmpty()) {
                item {
                    Text(
                        text = "Events",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = IndigoPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                items(filteredEvents) { event ->
                    SearchResultCard(
                        title = event.title,
                        subtitle = event.location,
                        category = event.status,
                        icon = Icons.Default.CalendarToday,
                        color = Orange
                    )
                }
                item { Spacer(modifier = Modifier.height(12.dp)) }
            }

            if (filteredBlogs.isEmpty() && filteredCommunities.isEmpty() &&
                filteredUsers.isEmpty() && filteredEvents.isEmpty()) {
                item {
                    Text(
                        text = "No results found",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray400,
                        modifier = Modifier.padding(vertical = 32.dp)
                    )
                }
            }
        } else {
            item {
                Text(
                    text = "Start typing to search across blogs, communities, people, and events",
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
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserAvatar(
                imageUrl = alumnus.avatar,
                userName = alumnus.name,
                size = 52,
                isOnline = alumnus.isOnline
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = alumnus.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Gray800
                )
                Text(
                    text = alumnus.college,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray500
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Text(
                        text = "Batch: ${alumnus.batch}",
                        style = MaterialTheme.typography.labelSmall,
                        color = IndigoPrimary
                    )
                    Text(
                        text = "\u2022",
                        style = MaterialTheme.typography.labelSmall,
                        color = Gray400
                    )
                    Text(
                        text = alumnus.department ?: "",
                        style = MaterialTheme.typography.labelSmall,
                        color = Gray400
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "View",
                tint = Gray400,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun SearchResultCard(
    title: String,
    subtitle: String,
    category: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Gray800,
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
                color = IndigoPrimary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
