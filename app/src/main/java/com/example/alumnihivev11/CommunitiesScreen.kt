package com.example.alumnihivev11

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.alumnihivev11.components.FilterChip
import com.example.alumnihivev11.components.SearchBar
import com.example.alumnihivev11.data.models.Community
import com.example.alumnihivev11.network.BackendRepository
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray900
import com.example.alumnihivev11.ui.theme.SurfaceLight

@Composable
fun CommunitiesScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var communities by remember { mutableStateOf<List<Community>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            communities = repository.getCommunities()
        } catch (e: Exception) {
            Log.e("CommunitiesScreen", "Failed to load communities", e)
        }
        isLoading = false
    }

    val filteredCommunities = communities.filter {
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
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
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Search communities...",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listOf("", "technology", "career", "hobby")) { category ->
                    FilterChip(
                        label = if (category.isEmpty()) "All" else category.replaceFirstChar { it.uppercase() },
                        isSelected = selectedCategory == category,
                        onClick = { selectedCategory = if (selectedCategory == category) "" else category }
                    )
                }
            }
        }

        items(filteredCommunities) { community ->
            CommunityCard(
                community = community,
                onClick = { },
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
