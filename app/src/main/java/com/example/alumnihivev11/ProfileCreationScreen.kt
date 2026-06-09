package com.example.alumnihivev11

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alumnihivev11.Elements.DatePicker
import com.example.alumnihivev11.ui.theme.Gray300
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCreationScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var college by remember { mutableStateOf("") }
    var graduationYear by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }
    val email by remember { mutableStateOf("example@gmail.com") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile Creation",
                        fontWeight = FontWeight.Bold,
                        color = Gray800
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = {},
                    label = { Text("Email") },
                    placeholder = { Text("example@gmail.com", color = Gray400) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IndigoPrimary,
                        unfocusedBorderColor = Gray300,
                        cursorColor = IndigoPrimary,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    trailingIcon = {
                        Icon(Icons.Default.Email, contentDescription = "Email", tint = IndigoPrimary)
                    }
                )
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        painter = painterResource(R.drawable.profile_icon),
                        contentDescription = "Profile Picture"
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            placeholder = { Text("Full Name", color = Gray400) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = IndigoPrimary,
                                unfocusedBorderColor = Gray300,
                                cursorColor = IndigoPrimary,
                                focusedContainerColor = White,
                                unfocusedContainerColor = White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            leadingIcon = {
                                Icon(Icons.Default.Person, contentDescription = "Name", tint = IndigoPrimary)
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = gender,
                            onValueChange = { gender = it },
                            label = { Text("Gender") },
                            placeholder = { Text("Select gender", color = Gray400) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = IndigoPrimary,
                                unfocusedBorderColor = Gray300,
                                cursorColor = IndigoPrimary,
                                focusedContainerColor = White,
                                unfocusedContainerColor = White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )
                    }
                }
            }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = college,
                    onValueChange = { college = it },
                    label = { Text("College Name") },
                    placeholder = { Text("Enter your college", color = Gray400) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IndigoPrimary,
                        unfocusedBorderColor = Gray300,
                        cursorColor = IndigoPrimary,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = graduationYear,
                        onValueChange = { graduationYear = it },
                        label = { Text("Graduation Year") },
                        placeholder = { Text("YYYY", color = Gray400) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = IndigoPrimary,
                            unfocusedBorderColor = Gray300,
                            cursorColor = IndigoPrimary,
                            focusedContainerColor = White,
                            unfocusedContainerColor = White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        trailingIcon = {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Year", tint = IndigoPrimary)
                        }
                    )
                    DatePicker()
                }
            }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().height(160.dp),
                    value = about,
                    onValueChange = { about = it },
                    label = { Text("About") },
                    placeholder = { Text("Tell us about yourself", color = Gray400) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IndigoPrimary,
                        unfocusedBorderColor = Gray300,
                        cursorColor = IndigoPrimary,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 5
                )
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = IndigoPrimary,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "Continue",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
