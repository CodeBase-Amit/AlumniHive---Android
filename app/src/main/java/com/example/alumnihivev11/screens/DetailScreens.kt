package com.example.alumnihivev11.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.util.Log
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import com.example.alumnihivev11.components.UserAvatar
import com.example.alumnihivev11.data.models.Notification
import com.example.alumnihivev11.data.models.User
import com.example.alumnihivev11.network.BackendRepository
import com.example.alumnihivev11.ui.theme.Gray200
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray700
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.Gray900
import com.example.alumnihivev11.ui.theme.IndigoDark
import com.example.alumnihivev11.ui.theme.IndigoLightest
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.Red
import com.example.alumnihivev11.ui.theme.SurfaceLight
import com.example.alumnihivev11.ui.theme.White

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var user by remember { mutableStateOf<User?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            user = repository.currentUser()
        } catch (e: Exception) {
            Log.e("ProfileScreen", "Failed to load profile", e)
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

    val displayUser = user ?: return

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(IndigoPrimary, IndigoDark)
                        )
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UserAvatar(
                        imageUrl = displayUser.avatar,
                        userName = displayUser.name,
                        size = 96
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = displayUser.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                    Text(
                        text = "${displayUser.role.uppercase()} \u2022 ${displayUser.college}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = White.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(White.copy(alpha = 0.2f))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = White,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Edit Profile",
                                style = MaterialTheme.typography.labelLarge,
                                color = White
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Gray200)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "About",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Gray800
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = displayUser.bio ?: "No bio added yet",
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray500
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Gray200)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Information",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Gray800,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    ProfileInfoRow("Department", displayUser.department ?: "Not specified")
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Gray200)
                    ProfileInfoRow("Batch", displayUser.batch?.toString() ?: "Not specified")
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Gray200)
                    ProfileInfoRow("Email", displayUser.email)
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Gray200)
                    ProfileInfoRow("Skills", displayUser.skills.joinToString(", "))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Gray400
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = Gray700
        )
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
            .padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Gray900
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Text(
                text = "ACCOUNT",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = Gray500,
                modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
            )
        }

        item { SettingsCard(icon = Icons.Default.Lock, title = "Change Password", subtitle = "Update your password") }
        item { SettingsCard(icon = Icons.Default.Notifications, title = "Notifications", subtitle = "Manage notification preferences") }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "PREFERENCES",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = Gray500,
                modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
            )
        }

        item { SettingsCard(icon = Icons.Default.Shield, title = "Privacy", subtitle = "Control what others can see") }
        item { SettingsCard(icon = Icons.Default.Settings, title = "Theme", subtitle = "Light or Dark theme") }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "MORE",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = Gray500,
                modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
            )
        }

        item { SettingsCard(icon = Icons.Default.Person, title = "About", subtitle = "About Alumni Hive") }
        item {
            SettingsCard(
                icon = Icons.Default.Logout,
                title = "Logout",
                subtitle = "Sign out of your account",
                tintColor = Red,
                onClick = {
                    scope.launch {
                        repository.logout()
                    }
                }
            )
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}

@Composable
fun SettingsCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    tintColor: Color = IndigoPrimary,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(tintColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = tintColor,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Gray800
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = Gray400
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "More",
                tint = Gray400,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun NotificationsScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    var notifications by remember { mutableStateOf<List<Notification>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            notifications = repository.getNotifications()
        } catch (e: Exception) {
            Log.e("NotificationsScreen", "Failed to load notifications", e)
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
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Gray900
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        items(notifications) { notification ->
            NotificationCard(notification)
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun NotificationCard(notification: com.example.alumnihivev11.data.models.Notification) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (notification.isRead) White else IndigoLightest
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, if (notification.isRead) Gray200 else Color.Transparent)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            if (notification.actor != null) {
                UserAvatar(
                    imageUrl = notification.actor.avatar,
                    userName = notification.actor.name,
                    size = 44
                )
                Spacer(modifier = Modifier.width(12.dp))
            } else {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(IndigoPrimary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = IndigoPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Gray800
                )
                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray500
                )
                Text(
                    text = notification.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = Gray400,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
