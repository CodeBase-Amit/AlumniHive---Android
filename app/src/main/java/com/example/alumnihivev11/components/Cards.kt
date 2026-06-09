package com.example.alumnihivev11.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.alumnihivev11.data.models.Blog
import com.example.alumnihivev11.data.models.Community
import com.example.alumnihivev11.data.models.Event
import com.example.alumnihivev11.ui.theme.Gray200
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray600
import com.example.alumnihivev11.ui.theme.Gray700
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.Green
import com.example.alumnihivev11.ui.theme.Green100
import com.example.alumnihivev11.ui.theme.IndigoLightest
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.White
import com.example.alumnihivev11.ui.theme.Yellow100
import com.example.alumnihivev11.ui.theme.Yellow700

@Composable
fun UserAvatar(
    imageUrl: String? = null,
    userName: String = "",
    size: Int = 48,
    isOnline: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(size.dp)) {
        if (!imageUrl.isNullOrEmpty()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = userName,
                modifier = Modifier
                    .size(size.dp)
                    .clip(CircleShape)
                    .background(IndigoLightest),
                contentScale = ContentScale.Crop
            )
        } else {
            val initials = userName.split(" ").map { it.firstOrNull()?.uppercaseChar() }
                .filterNotNull()
                .joinToString("")
                .take(2)
            Box(
                modifier = Modifier
                    .size(size.dp)
                    .clip(CircleShape)
                    .background(IndigoPrimary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        if (isOnline) {
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(Green)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun CommunityCard(
    community: Community,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200)
    ) {
        Column {
            if (!community.avatar.isNullOrEmpty()) {
                AsyncImage(
                    model = community.avatar,
                    contentDescription = community.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(IndigoPrimary, IndigoPrimary.copy(alpha = 0.7f))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.People,
                        contentDescription = null,
                        tint = White.copy(alpha = 0.6f),
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = community.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Gray800,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = community.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray500,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = "Members",
                            tint = Gray400,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "${community.totalMembers} members",
                            style = MaterialTheme.typography.labelSmall,
                            color = Gray500
                        )
                    }
                    Text(
                        text = community.category.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.labelSmall,
                        color = IndigoPrimary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .background(IndigoLightest, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BlogCard(
    blog: Blog,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            if (!blog.coverImage.isNullOrEmpty()) {
                AsyncImage(
                    model = blog.coverImage,
                    contentDescription = blog.title,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = blog.category.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelSmall,
                    color = IndigoPrimary,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .background(IndigoLightest, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = blog.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Gray800,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = blog.excerpt,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray500,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        UserAvatar(
                            imageUrl = blog.author.avatar,
                            userName = blog.author.name,
                            size = 24
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text(
                                text = blog.author.name,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Gray700
                            )
                            Text(
                                text = "${blog.readTime} min read",
                                style = MaterialTheme.typography.labelSmall,
                                color = Gray400
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Likes",
                            tint = Gray400,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "${blog.likes}",
                            style = MaterialTheme.typography.labelSmall,
                            color = Gray400
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EventCard(
    event: Event,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val statusBg = when (event.status) {
        "upcoming" -> Green100
        "ongoing" -> Yellow100
        else -> Gray200
    }
    val statusText = when (event.status) {
        "upcoming" -> Green
        "ongoing" -> Yellow700
        else -> Gray600
    }

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200)
    ) {
        Column {
            if (!event.image.isNullOrEmpty()) {
                AsyncImage(
                    model = event.image,
                    contentDescription = event.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(IndigoLightest),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = IndigoPrimary.copy(alpha = 0.4f),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Gray800,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = event.status.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.labelSmall,
                        color = statusText,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .background(statusBg, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = event.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray500,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Date",
                            tint = IndigoPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = event.startDate,
                            style = MaterialTheme.typography.labelSmall,
                            color = Gray500
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = IndigoPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = event.location,
                            style = MaterialTheme.typography.labelSmall,
                            color = Gray500
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = "Attendees",
                            tint = IndigoPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "${event.attendees}",
                            style = MaterialTheme.typography.labelSmall,
                            color = Gray500
                        )
                    }
                }
            }
        }
    }
}
