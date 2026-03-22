package com.example.alumnihivev11


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alumnihivev11.Elements.CommunityCards


data class communityCardItems(
    val titleText: String,
    val text: String,
    val icon: Int
)
@Composable
fun CommunitiesScreen(navController: NavController){

    val CardItemRowOne = listOf(
        communityCardItems(
            titleText = "Python",
            text = "The Community to Learn and Explore Python with Exceptional Pythoneer's",
            icon = R.drawable.python_programming_language_icon,
        ),
        communityCardItems(
            titleText = "Python",
            text = "The Community to Learn and Explore Python with Exceptional Pythoneer's",
            icon = R.drawable.python_programming_language_icon,
        ),
        communityCardItems(
            titleText = "Python",
            text = "The Community to Learn and Explore Python with Exceptional Pythoneer's",
            icon = R.drawable.python_programming_language_icon,
        ),
        communityCardItems(
            titleText = "Python",
            text = "The Community to Learn and Explore Python with Exceptional Pythoneer's",
            icon = R.drawable.python_programming_language_icon,
        )
        )

    val CardItemRowTwo = listOf(
        communityCardItems(
            titleText = "Android",
            text = "The Community to Learn and Explore Android with Exceptional Android Developer's",
            icon = R.drawable.ic_android_green_logo,
        ),
        communityCardItems(
            titleText = "Android",
            text = "The Community to Learn and Explore Android with Exceptional Android Developer's",
            icon = R.drawable.ic_android_green_logo,
        ),
        communityCardItems(
            titleText = "Android",
            text = "The Community to Learn and Explore Android with Exceptional Android Developer's",
            icon = R.drawable.ic_android_green_logo,
        ),
        communityCardItems(
            titleText = "Android",
            text = "The Community to Learn and Explore Android with Exceptional Android Developer's",
            icon = R.drawable.ic_android_green_logo,
        ),
    )

    val CardItemRowThree = listOf(
        communityCardItems(
            titleText = "Web Development",
            text = "The Community to Learn and Explore Web Development with Exceptional Web Developer's",
            icon = R.drawable.profile_icon,
        ),
        communityCardItems(
            titleText = "Web Development",
            text = "The Community to Learn and Explore Web Development with Exceptional Web Developer's",
            icon = R.drawable.profile_icon,
        ),
        communityCardItems(
            titleText = "Web Development",
            text = "The Community to Learn and Explore Web Development with Exceptional Web Developer's",
            icon = R.drawable.profile_icon,
        ),
        communityCardItems(
            titleText = "Web Development",
            text = "The Community to Learn and Explore Web Development with Exceptional Web Developer's",
            icon = R.drawable.profile_icon,
        ),
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
                    Text("Search Communities...")
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

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(CardItemRowOne) { item ->
                    CommunityCards(
                        text = item.text,
                        titleText = item.titleText,
                        icon = item.icon
                    ) {

                    }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier.fillMaxWidth().height(16.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(CardItemRowTwo) { item ->
                    CommunityCards(
                        text = item.text,
                        titleText = item.titleText,
                        icon = item.icon
                    ) {

                    }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier.fillMaxWidth().height(16.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(CardItemRowThree) { item ->
                    CommunityCards(
                        text = item.text,
                        titleText = item.titleText,
                        icon = item.icon
                    ) {

                    }
                }
            }
        }
    }


}
//
//@Preview(showSystemUi = true)
//@Composable
//fun GoogleButtonPreview() {
//        CommunitiesScreen()
//}

