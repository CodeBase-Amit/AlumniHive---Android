package com.example.alumnihivev11

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alumnihivev11.ui.theme.customFontFamily

@Composable
fun HomeScreen(navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                bottom = 8.dp,
                start = 6.dp,
                end = 6.dp
            )
    ) {
        item {
            LazyRow(
                modifier = Modifier.padding(8.dp)
            ) {
                items(9) {
                    Image(
                        painter = painterResource(R.drawable.profile_icon),
                        contentDescription = "Profiles",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(width = 1.dp, color = Color.Cyan, shape = CircleShape),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }
        }

        item {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp))
            Divider(thickness = 4.dp, color = Color.Black)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp))
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(600.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Home Screen", fontFamily = customFontFamily,
                    fontSize = 50.sp, fontWeight = FontWeight.Black)
            }
        }


    }

}


//@Preview(showSystemUi = true)
//@Composable
//fun GoogleButtonPreview() {
//    HomeScreen()
//}
