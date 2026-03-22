package com.example.alumnihivev11


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alumnihivev11.Elements.BlogsCards
import com.example.alumnihivev11.Elements.CommunityCards


data class blogCardItems(
    val titleText: String,
    val text: String,
    val icon: Int
)
@Composable
fun BlogsScreen(navController: NavController){


    val BlogItem = listOf(
        communityCardItems(
            titleText = "Python",
            text = "Author - Amit Sharma",
            icon = R.drawable.python_programming_language_icon,
        ),
        communityCardItems(
            titleText = "Python",
            text = "Author - Amit Sharma",
            icon = R.drawable.python_programming_language_icon,
        ),
        communityCardItems(
            titleText = "Python",
            text = "Author - Amit Sharma",
            icon = R.drawable.python_programming_language_icon,
        ),
        communityCardItems(
            titleText = "Python",
            text = "Author - Amit Sharma",
            icon = R.drawable.python_programming_language_icon,
        )
    )

    var search by remember { mutableStateOf("") }
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(Color.LightGray)

    ) {
        item {

            Spacer(
                modifier = Modifier.fillMaxWidth().height(16.dp)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = search,
                onValueChange = {
                },
                label = {
                    Text("Search Blogs...")
                },
                placeholder = {Text("")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search Box")
                }

            )
        }

        items(BlogItem) { item ->

            BlogsCards(
                text = item.text,
                titleText = item.titleText,
                icon = item.icon
            ) { }

        }


    }


}
//
//@Preview(showSystemUi = true)
//@Composable
//fun GoogleButtonPreview() {
//        CommunitiesScreen()
//}
