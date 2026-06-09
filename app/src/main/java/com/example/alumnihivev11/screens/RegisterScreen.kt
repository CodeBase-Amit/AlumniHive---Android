package com.example.alumnihivev11.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alumnihivev11.Navigation.Routes
import com.example.alumnihivev11.network.BackendException
import com.example.alumnihivev11.network.BackendRepository
import com.example.alumnihivev11.ui.theme.Gray100
import com.example.alumnihivev11.ui.theme.Gray300
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.Gray700
import com.example.alumnihivev11.ui.theme.Gray800
import com.example.alumnihivev11.ui.theme.Gray900
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.White
import android.util.Log
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember(context) { BackendRepository.getInstance(context.applicationContext) }
    val scope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var college by remember { mutableStateOf("") }
    var graduationYear by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("student") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(IndigoPrimary.copy(alpha = 0.06f), White)
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Join the Alumni Hive community",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray500
                )

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Full Name", color = Gray400) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Name",
                            tint = IndigoPrimary
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IndigoPrimary,
                        unfocusedBorderColor = Gray300,
                        cursorColor = IndigoPrimary,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("your.email@college.edu", color = Gray400) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email",
                            tint = IndigoPrimary
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IndigoPrimary,
                        unfocusedBorderColor = Gray300,
                        cursorColor = IndigoPrimary,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Create a strong password", color = Gray400) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password",
                            tint = IndigoPrimary
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = Gray400
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IndigoPrimary,
                        unfocusedBorderColor = Gray300,
                        cursorColor = IndigoPrimary,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = college,
                    onValueChange = { college = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("College / University", color = Gray400) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "College",
                            tint = IndigoPrimary
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IndigoPrimary,
                        unfocusedBorderColor = Gray300,
                        cursorColor = IndigoPrimary,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = graduationYear,
                    onValueChange = { graduationYear = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Graduation Year", color = Gray400) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Year",
                            tint = IndigoPrimary
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IndigoPrimary,
                        unfocusedBorderColor = Gray300,
                        cursorColor = IndigoPrimary,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { role = "student" },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (role == "student") IndigoPrimary else Gray100,
                            contentColor = if (role == "student") White else Gray800
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Student",
                            fontWeight = if (role == "student") FontWeight.Bold else FontWeight.Normal
                        )
                    }
                    Button(
                        onClick = { role = "alumni" },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (role == "alumni") IndigoPrimary else Gray100,
                            contentColor = if (role == "alumni") White else Gray800
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Alumni",
                            fontWeight = if (role == "alumni") FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        scope.launch {
                            isLoading = true
                            errorMessage = null
                            try {
                                repository.register(
                                    name = name.trim(),
                                    email = email.trim(),
                                    password = password,
                                    college = college.trim(),
                                    role = role,
                                    department = null,
                                    graduationYear = graduationYear.trim().toIntOrNull()
                                )
                                navController.navigate(Routes.Home)
                            } catch (e: BackendException) {
                                Log.e("RegisterScreen", "Registration backend error", e)
                                errorMessage = e.message
                            } catch (e: Exception) {
                                Log.e("RegisterScreen", "Registration failed", e)
                                errorMessage = e.message ?: "Registration failed"
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = IndigoPrimary,
                        contentColor = White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    enabled = !isLoading
                ) {
                    Text(
                        text = if (isLoading) "Creating Account..." else "Create Account",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (errorMessage != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Already have an account? ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray500
                    )
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = IndigoPrimary,
                        modifier = Modifier.clickable { navController.navigate(Routes.Login) }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
