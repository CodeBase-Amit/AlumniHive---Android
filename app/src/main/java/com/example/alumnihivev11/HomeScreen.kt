package com.example.alumnihivev11

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.alumnihivev11.components.GradientHeaderCard
import com.example.alumnihivev11.components.UserAvatar
import com.example.alumnihivev11.data.models.User
import com.example.alumnihivev11.network.BackendRepository
import com.example.alumnihivev11.ui.theme.Gray200
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.Gray900
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.SurfaceLight
import com.example.alumnihivev11.ui.theme.White

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            users = repository.getUsers(role = "alumni")
        } catch (e: Exception) {
            Log.e("HomeScreen", "Failed to load data", e)
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
            .padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            GradientHeaderCard(
                title = "Alumni Network",
                subtitle = "Connect with fellow alumni and grow your network",
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        item {
            Text(
                text = "Featured Alumni",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Gray900,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(users) { user ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        UserAvatar(
                            imageUrl = user.avatar,
                            userName = user.name,
                            size = 72,
                            isOnline = user.isOnline
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = user.name.split(" ").first(),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = Gray800
                        )
                        Text(
                            text = user.role.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.labelSmall,
                            color = Gray400
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Quick Links",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    val links = listOf(
                        "Find Mentors" to "Connect with experienced alumni",
                        "Browse Events" to "See upcoming gatherings",
                        "Join Communities" to "Find your people",
                        "Read Blogs" to "Stories and insights"
                    )
                    links.forEach { (title, subtitle) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Gray800
                                )
                                Text(
                                    text = subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Gray500
                                )
                            }
                            Text(
                                text = "→",
                                style = MaterialTheme.typography.titleMedium,
                                color = IndigoPrimary
                            )
                        }
                        if (title != links.last().first) {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Gray200)
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
