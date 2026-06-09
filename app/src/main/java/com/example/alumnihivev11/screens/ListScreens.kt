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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.navigation.NavController
import com.example.alumnihivev11.components.CommunityCard
import com.example.alumnihivev11.components.EventCard
import com.example.alumnihivev11.components.FilterChip
import com.example.alumnihivev11.components.SearchBar
import com.example.alumnihivev11.data.models.Blog
import com.example.alumnihivev11.data.models.Community
import com.example.alumnihivev11.data.models.Event
import com.example.alumnihivev11.network.BackendRepository
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.Gray900
import com.example.alumnihivev11.ui.theme.SurfaceLight
import com.example.alumnihivev11.components.BlogCard as BlogCardComponent

@Composable
fun CommunitiesListScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var allCommunities by remember { mutableStateOf<List<Community>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            allCommunities = repository.getCommunities()
        } catch (e: Exception) {
            Log.e("CommunitiesListScreen", "Failed to load communities", e)
        }
        isLoading = false
    }

    val filteredCommunities = allCommunities.filter {
        (searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)) &&
        (selectedCategory.isEmpty() || it.category == selectedCategory)
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
            }

            item {
                Column {
                    Text(
                        text = "Communities",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                    Text(
                        text = "Join communities to connect with like-minded people",
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
                    placeholder = "Search communities...",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("", "technology", "career", "hobby").forEach { category ->
                        FilterChip(
                            label = if (category.isEmpty()) "All" else category.replaceFirstChar { it.uppercase() },
                            isSelected = selectedCategory == category,
                            onClick = { selectedCategory = if (selectedCategory == category) "" else category }
                        )
                    }
                }
            }

            if (filteredCommunities.isEmpty()) {
                item {
                    Text(
                        text = "No communities found",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray400,
                        modifier = Modifier.padding(vertical = 48.dp)
                    )
                }
            }

            items(filteredCommunities) { community ->
                CommunityCard(
                    community = community,
                    onClick = { },
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun BlogsListScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var allBlogs by remember { mutableStateOf<List<Blog>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            allBlogs = repository.getBlogs()
        } catch (e: Exception) {
            Log.e("BlogsListScreen", "Failed to load blogs", e)
        }
        isLoading = false
    }

    val filteredBlogs = allBlogs.filter {
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
            }

            item {
                Column {
                    Text(
                        text = "Blogs",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                    Text(
                        text = "Read and share knowledge from the community",
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
                    placeholder = "Search blogs...",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (filteredBlogs.isEmpty()) {
                item {
                    Text(
                        text = "No blogs found",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray400,
                        modifier = Modifier.padding(vertical = 48.dp)
                    )
                }
            }

            items(filteredBlogs) { blog ->
                BlogCardComponent(
                    blog = blog,
                    onClick = { },
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun EventsListScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var allEvents by remember { mutableStateOf<List<Event>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedStatus by remember { mutableStateOf("upcoming") }

    LaunchedEffect(Unit) {
        try {
            allEvents = repository.getEvents()
        } catch (e: Exception) {
            Log.e("EventsListScreen", "Failed to load events", e)
        }
        isLoading = false
    }

    val filteredEvents = allEvents.filter { it.status == selectedStatus }

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
            }

            item {
                Column {
                    Text(
                        text = "Events",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                    Text(
                        text = "Discover and join exciting events in your community",
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
                    listOf("upcoming", "ongoing", "completed").forEach { status ->
                        FilterChip(
                            label = status.replaceFirstChar { it.uppercase() },
                            isSelected = selectedStatus == status,
                            onClick = { selectedStatus = status }
                        )
                    }
                }
            }

            if (filteredEvents.isEmpty()) {
                item {
                    Text(
                        text = "No events found",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray400,
                        modifier = Modifier.padding(vertical = 48.dp)
                    )
                }
            }

            items(filteredEvents) { event ->
                EventCard(
                    event = event,
                    onClick = { },
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
