package com.example.alumnihivev11

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alumnihivev11.Elements.DatePicker


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCreationScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    var name by remember { mutableStateOf("Name...") }
    var gender by remember {mutableStateOf("Gender...")}
    var college by remember { mutableStateOf("College Name...") }
    var graduationYear by remember { mutableStateOf("Graduation Year") }
    var about by remember { mutableStateOf("") }
    val email by remember { mutableStateOf("example@gmail.com") }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.primaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.surface
                ),
                title = { Text("Profile Creation") }
            )
        },


        ) { innerpadding ->

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)

        ) {
            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = {
                    },
                    label = {
                        Text("Email")
                    },
                    placeholder = {Text("example@gmail.com")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    ),
                    singleLine = true,
                    trailingIcon = {
                        Icon(Icons.Default.Email, contentDescription = "Email")
                    }

                )
            }
            item {
                Row(
                    modifier = Modifier
                ) {
                    Image(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(width = 8.dp, color = Color.Black, shape = CircleShape),
                        painter = painterResource(R.drawable.profile_icon),
                        contentDescription = "Profile Picture"
                    )

                    Spacer(modifier = Modifier
                        .size(40.dp))

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = {newName ->
                                name = newName

                            },
                            label = {
                                Text("Name")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                            ),
                            singleLine = true

                        )

                        Spacer(modifier = Modifier
                            .size(20.dp))

                        OutlinedTextField(
                            value = gender,
                            onValueChange = {genderType ->
                                gender = genderType

                            },
                            label = {
                                Text("Gender...")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                            ),
                            singleLine = true

                            )


                    }
                }
            }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = college,
                    onValueChange = {collegeName ->
                        college = collegeName

                    },
                    label = {
                        Text("College Name")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    ),
                    singleLine = true,

                )

            }

            item {
                Row(
                    modifier = Modifier
                ) {
                    OutlinedTextField(
                        modifier = Modifier.width(200.dp),
                        value = graduationYear,
                        onValueChange = {yearOfPassing ->
                            graduationYear = yearOfPassing

                        },
                        label = {
                            Text("Graduation Year")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        ),
                        singleLine = true,
                        trailingIcon = {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "YearOfPassing")
                        }


                    )

                    Spacer(modifier = Modifier
                        .size(20.dp))

                    DatePicker(

                    )

                }
            }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    value = about,
                    onValueChange = {aboutYourself ->
                        about = aboutYourself

                    },
                    label = {
                        Text("About")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    ),
                    maxLines = 6

                )
            }

            item(

            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        modifier = Modifier.width(300.dp)
                            .clip(MaterialTheme.shapes.large)
                            .padding(
                                start = 12.dp,
                                end = 16.dp,
                                top = 12.dp,
                                bottom = 12.dp
                            ),

                        shape = MaterialTheme.shapes.medium,
                        border = BorderStroke(width = 1.dp, color = Color.Gray),
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

                    ) {
                        Text(text = "Continue")
                    }
                }
            }

        }

    }
}
//
//@Preview(showSystemUi = true)
//@Composable
//fun Preview() {
//
//    ProfileCreationScreen()
//
//}