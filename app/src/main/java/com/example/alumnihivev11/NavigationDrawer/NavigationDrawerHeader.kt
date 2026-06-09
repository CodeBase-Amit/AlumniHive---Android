package com.example.alumnihivev11.NavigationDrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.alumnihivev11.R
import com.example.alumnihivev11.data.dummy.DummyDataFactory
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.IndigoDark
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.White

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    val user = DummyDataFactory.getDummyCurrentUser()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(IndigoPrimary, IndigoDark)
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        AsyncImage(
            model = user.avatar,
            contentDescription = "Profile",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(White.copy(alpha = 0.2f)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = user.name,
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = White
        )

        Text(
            text = user.email,
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
            color = White.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
