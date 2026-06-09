package com.example.alumnihivev11.Elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.alumnihivev11.ui.theme.Gray200
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.IndigoLightest
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.White

@Composable
fun BlogsCards(
    text: String,
    titleText: String,
    onClicked: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Gray200),
        onClick = onClicked
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(IndigoLightest, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Book,
                    contentDescription = "Blog",
                    tint = IndigoPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Gray800
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray500
                )
            }
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Open",
                tint = IndigoPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
