package com.example.alumnihivev11

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.example.alumnihivev11.components.BlogCard
import com.example.alumnihivev11.components.SearchBar
import com.example.alumnihivev11.data.models.Blog
import com.example.alumnihivev11.network.BackendRepository
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray900
import com.example.alumnihivev11.ui.theme.SurfaceLight

@Composable
fun BlogsScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var blogs by remember { mutableStateOf<List<Blog>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            blogs = repository.getBlogs()
        } catch (e: Exception) {
            Log.e("BlogsScreen", "Failed to load blogs", e)
        }
        isLoading = false
    }

    val filteredBlogs = blogs.filter {
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
            Spacer(modifier = Modifier.height(16.dp))
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
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Search blogs...",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(filteredBlogs) { blog ->
            BlogCard(
                blog = blog,
                onClick = { },
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
