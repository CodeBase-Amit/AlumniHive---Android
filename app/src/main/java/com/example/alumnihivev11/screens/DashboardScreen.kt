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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrendingUp
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.navigation.NavController
import com.example.alumnihivev11.components.BlogCard
import com.example.alumnihivev11.components.EventCard
import com.example.alumnihivev11.components.GradientHeaderCard
import com.example.alumnihivev11.data.models.Blog
import com.example.alumnihivev11.data.models.Event
import com.example.alumnihivev11.data.models.User
import com.example.alumnihivev11.network.BackendRepository
import com.example.alumnihivev11.ui.theme.Blue
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.Gray900
import com.example.alumnihivev11.ui.theme.Green
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.Orange
import com.example.alumnihivev11.ui.theme.Pink
import com.example.alumnihivev11.ui.theme.SurfaceLight
import com.example.alumnihivev11.ui.theme.White

@Composable
fun DashboardScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var currentUser by remember { mutableStateOf<User?>(null) }
    var recentBlogs by remember { mutableStateOf<List<Blog>>(emptyList()) }
    var upcomingEvents by remember { mutableStateOf<List<Event>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            currentUser = repository.currentUser()
            recentBlogs = repository.getBlogs(limit = 3)
            upcomingEvents = repository.getEvents(status = "upcoming")
        } catch (e: Exception) {
            Log.e("DashboardScreen", "Failed to load data", e)
        }
        isLoading = false
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize().background(SurfaceLight),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...", color = Gray500)
        }
        return
    }

    val displayUser = currentUser ?: return

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
            .padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            GradientHeaderCard(
                title = "Welcome back, ${displayUser.name.split(" ").firstOrNull()}!",
                subtitle = "Explore communities, connect with mentors, and grow your network.",
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                com.example.alumnihivev11.components.StatCard(
                    icon = Icons.Default.People,
                    title = "Communities",
                    value = "5",
                    color = Blue,
                    modifier = Modifier.weight(1f)
                )
                com.example.alumnihivev11.components.StatCard(
                    icon = Icons.Default.Book,
                    title = "Blogs",
                    value = "12",
                    color = Green,
                    modifier = Modifier.weight(1f)
                )
                com.example.alumnihivev11.components.StatCard(
                    icon = Icons.Default.TrendingUp,
                    title = "Events",
                    value = "8",
                    color = Orange,
                    modifier = Modifier.weight(1f)
                )
                com.example.alumnihivev11.components.StatCard(
                    icon = Icons.Default.School,
                    title = "Mentors",
                    value = "2",
                    color = Pink,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Blogs",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.labelLarge,
                    color = IndigoPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        items(recentBlogs) { blog ->
            BlogCard(
                blog = blog,
                onClick = { },
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Upcoming Events",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.labelLarge,
                    color = IndigoPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        items(upcomingEvents) { event ->
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
