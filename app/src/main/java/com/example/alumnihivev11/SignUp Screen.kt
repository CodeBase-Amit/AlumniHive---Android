package com.example.alumnihivev11

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.alumnihivev11.Buttons.GoogleButton

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController

) {

    var email by remember {
        mutableStateOf("")
    }

    var clicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .size(30.dp))
        Image(
            painter = painterResource(R.drawable.alumnihivelogo),
            contentDescription = "AlumniHive Logo",
            modifier = Modifier.size(200.dp)
        )

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp))
        {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Create an account")

                Text("Enter your email to sign up for this app")

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .size(30.dp))

                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = {
                        Text("email@college.edu")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    ),
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .size(20.dp)
                )

                Button(
                    modifier = Modifier
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

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp))

                Text("-----or-----")

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp))

                GoogleButton(
                    onClicked = {}
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Already have an account: ")
                    TextButton(
                        onClick = {}
                    ) {
                        Text("Sign In")
                    }
                }
                }
            }
        }
    }

//
//@Preview(showSystemUi = true)
//@Composable
//fun LockScreenPreview() {
//    SignUpScreen()
//}