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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alumnihivev11.components.BlogCard
import com.example.alumnihivev11.components.GradientHeaderCard
import com.example.alumnihivev11.components.StatCard
import com.example.alumnihivev11.data.dummy.DummyDataFactory
import com.example.alumnihivev11.ui.theme.Blue
import com.example.alumnihivev11.ui.theme.Gray100
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Green
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.Orange
import com.example.alumnihivev11.ui.theme.White

@Composable
fun DashboardScreen(navController: NavController) {
    val currentUser = DummyDataFactory.getDummyCurrentUser()
    val recentBlogs = DummyDataFactory.getDummyBlogs(3)
    val upcomingEvents = DummyDataFactory.getDummyEvents(3)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Welcome Header
        item {
            GradientHeaderCard(
                title = "Welcome back, ${currentUser.name.split(" ").firstOrNull()}! 👋",
                subtitle = "Explore communities, connect with mentors, and grow your network.",
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        // Stats Grid
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCardSmall(
                    icon = Icons.Default.People,
                    title = "Communities",
                    value = "5",
                    color = Blue,
                    modifier = Modifier.weight(1f)
                )
                StatCardSmall(
                    icon = Icons.Default.Book,
                    title = "Blogs",
                    value = "12",
                    color = Green,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCardSmall(
                    icon = Icons.Default.CalendarToday,
                    title = "Events",
                    value = "8",
                    color = Orange,
                    modifier = Modifier.weight(1f)
                )
                StatCardSmall(
                    icon = Icons.Default.School,
                    title = "Mentors",
                    value = "2",
                    color = IndigoPrimary,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Recent Blogs Section
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
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "View All →",
                    style = MaterialTheme.typography.labelSmall,
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
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Upcoming Events Section
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
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "View All →",
                    style = MaterialTheme.typography.labelSmall,
                    color = IndigoPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        items(upcomingEvents) { event ->
            EventSmallCard(
                title = event.title,
                date = event.startDate,
                location = event.location,
                attendees = event.attendees,
                onClick = { }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun StatCardSmall(
    icon: ImageVector,
    title: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = Gray400
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EventSmallCard(
    title: String,
    date: String,
    location: String,
    attendees: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = date,
                style = MaterialTheme.typography.labelSmall,
                color = Gray400
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = location,
                    style = MaterialTheme.typography.labelSmall,
                    color = Gray400
                )
                Text(
                    text = "$attendees attending",
                    style = MaterialTheme.typography.labelSmall,
                    color = IndigoPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

