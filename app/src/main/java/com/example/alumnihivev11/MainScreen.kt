package com.example.alumnihivev11

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.alumnihivev11.Navigation.Navigation
import com.example.alumnihivev11.Navigation.Routes
import com.example.alumnihivev11.NavigationDrawer.DrawerHeader
import com.example.alumnihivev11.ui.theme.customFontFamily
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import kotlinx.coroutines.launch

data class BottomNavigationItem(
    val name: String,
    val icon: ImageVector,
    val unselectedIcon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    var selectedItem by remember { mutableStateOf(0) }

    val navController = rememberNavController()

    val BottomNavItem = listOf(
        BottomNavigationItem(
            name = "Home",
            icon = Icons.Default.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            name = "Communities",
            icon = Icons.Default.People,
            unselectedIcon = Icons.Outlined.People
        ),
        BottomNavigationItem(
            name = "Mentorship",
            icon = Icons.Default.School,
            unselectedIcon = Icons.Outlined.School
        ),
        BottomNavigationItem(
            name = "Blogs",
            icon = Icons.Default.Book,
            unselectedIcon = Icons.Outlined.Book
        ),


        )

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerContent = {
            ModalDrawerSheet() {
                DrawerHeader()

                Divider()

                NavigationDrawerItem(
                    label = {
                        Text("Profile")
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.Profile)
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Chat")
                    },
                    selected = false,
                    onClick = {}
                )

                NavigationDrawerItem(
                    label = {
                        Text("Notifications")
                    },
                    selected = false,
                    onClick = {}
                )

                NavigationDrawerItem(
                    label = {
                        Text("SignUp")
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.SignUp)
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Settings")
                    },
                    selected = false,
                    onClick = {}
                )

                NavigationDrawerItem(
                    label = {
                        Text("About")
                    },
                    selected = false,
                    onClick = {}
                )
            }
        },
        drawerState = drawerState
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(36.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Alumni Hive",
                                color = Color.Black,
                                fontSize = 34.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = customFontFamily
                            )

                        }

                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) { Icon(Icons.Default.Menu, contentDescription = "Menu") }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Chat, contentDescription = "Chat Button")
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = "Chat Notifications"
                            )
                        }

                    },

                    colors = TopAppBarDefaults.topAppBarColors(Color.White),
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

            },

            bottomBar = {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = WindowInsets.navigationBars.asPaddingValues()
                                .calculateBottomPadding()
                        )
                ) {
                    AnimatedBottomBar(
                        selectedItem = selectedItem,
                        itemSize = BottomNavItem.size,
                        containerColor = Color.Transparent,
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        indicatorDirection = IndicatorDirection.TOP,
                        indicatorStyle = IndicatorStyle.WORM
                    ) {
                        BottomNavItem.forEachIndexed { index, navigationItem ->
                            BottomBarItem(
                                modifier = Modifier.align(alignment = Alignment.Top),
                                selected = selectedItem == index,
                                onClick = {
                                    selectedItem = index

                                    when (index) {
                                        0 -> navController.navigate(Routes.Home)
                                        1 -> navController.navigate(Routes.Communities)
                                        2 -> navController.navigate(Routes.Mentorship)
                                        3 -> navController.navigate(Routes.Blogs)
                                    }
                                },
                                imageVector = navigationItem.icon,
                                label = navigationItem.name,
                                containerColor = Color.Transparent
                            )
                        }
                    }
                }
            }
        ) { innerpadding ->
//            ContentScreen(modifier = Modifier.padding(innerpadding), selectedItem)
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)) {
//
                NavHost(navController = navController, startDestination = Navigation.Main){

                    navigation<Navigation.Main>(startDestination = Routes.Home){

                        composable<Routes.Home>{
                            HomeScreen(navController = navController)
                        }

                        composable<Routes.Communities>{
                            CommunitiesScreen(navController = navController)
                        }

                        composable<Routes.Mentorship>{
                            MentorshipScreen(navController = navController)
                        }

                        composable<Routes.Blogs>{
                            BlogsScreen(navController = navController)
                        }

                        composable<Routes.Profile>{
                            ProfileCreationScreen(navController = navController)
                        }

                        composable<Routes.SignUp>{
                            SignUpScreen(navController = navController)
                        }
                    }
                }
            }


        }

    }
}
