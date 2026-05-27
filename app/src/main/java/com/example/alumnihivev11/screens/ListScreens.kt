package com.example.alumnihivev11.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.alumnihivev11.components.CommunityCard
import com.example.alumnihivev11.components.SearchBar
import com.example.alumnihivev11.data.dummy.DummyDataFactory
import com.example.alumnihivev11.ui.theme.Gray100
import com.example.alumnihivev11.ui.theme.Gray400

@Composable
fun CommunitiesListScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    val allCommunities = DummyDataFactory.getDummyCommunities(12)
    val filteredCommunities = allCommunities.filter {
        (searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true)) &&
        (selectedCategory.isEmpty() || it.category == selectedCategory)
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
            }

            item {
                Column {
                    Text(
                        text = "Communities",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Join communities to connect with like-minded people",
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
                    placeholder = "Search communities...",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
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
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    val allBlogs = DummyDataFactory.getDummyBlogs(15)
    val filteredBlogs = allBlogs.filter {
        (searchQuery.isEmpty() || it.title.contains(searchQuery, ignoreCase = true)) &&
        (selectedCategory.isEmpty() || it.category == selectedCategory)
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
            }

            item {
                Column {
                    Text(
                        text = "Blogs",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Read and share knowledge from the community",
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
                    placeholder = "Search blogs...",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(filteredBlogs) { blog ->
                com.example.alumnihivev11.components.BlogCard(
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
    var selectedStatus by remember { mutableStateOf("upcoming") }

    val allEvents = DummyDataFactory.getDummyEvents(10)
    val filteredEvents = allEvents.filter { it.status == selectedStatus }

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
            }

            item {
                Column {
                    Text(
                        text = "Events",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Discover and join exciting events happening in your community",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray400
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            item {
                // Status Filter Tabs
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("upcoming", "ongoing", "completed").forEach { status ->
                        androidx.compose.material3.FilterChip(
                            selected = selectedStatus == status,
                            onClick = { selectedStatus = status },
                            label = {
                                Text(status.replaceFirstChar { it.uppercase() })
                            }
                        )
                    }
                }
            }

            items(filteredEvents) { event ->
                com.example.alumnihivev11.components.EventCard(
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

