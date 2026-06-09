package com.example.alumnihivev11

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alumnihivev11.Navigation.Routes
import com.example.alumnihivev11.NavigationDrawer.DrawerHeader
import com.example.alumnihivev11.network.SessionManager
import com.example.alumnihivev11.screens.AlumniDirectoryScreen
import com.example.alumnihivev11.screens.BlogsListScreen
import com.example.alumnihivev11.screens.ChatListScreen
import com.example.alumnihivev11.screens.CommunitiesListScreen
import com.example.alumnihivev11.screens.DashboardScreen
import com.example.alumnihivev11.screens.EventsListScreen
import com.example.alumnihivev11.screens.LoginScreen
import com.example.alumnihivev11.screens.MentorshipListScreen
import com.example.alumnihivev11.screens.NotificationsScreen
import com.example.alumnihivev11.screens.ProfileScreen
import com.example.alumnihivev11.screens.QuestionsListScreen
import com.example.alumnihivev11.screens.RegisterScreen
import com.example.alumnihivev11.screens.SearchScreen
import com.example.alumnihivev11.screens.SettingsScreen
import com.example.alumnihivev11.ui.theme.Gray400
import com.example.alumnihivev11.ui.theme.Gray50
import com.example.alumnihivev11.ui.theme.Gray500
import com.example.alumnihivev11.ui.theme.IndigoLightest
import com.example.alumnihivev11.ui.theme.IndigoPrimary
import com.example.alumnihivev11.ui.theme.Red
import com.example.alumnihivev11.ui.theme.White
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class BottomNavigationItem(
    val name: String,
    val icon: ImageVector,
    val unselectedIcon: ImageVector
)

data class DrawerMenuItem(
    val label: String,
    val route: Any?,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val sessionManager = remember(context) { SessionManager(context.applicationContext) }
    var token by remember { mutableStateOf<String?>(null) }
    var isResolved by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        token = sessionManager.tokenFlow.first()
        isResolved = true
    }

    if (!isResolved) {
        Box(modifier = Modifier.fillMaxSize().background(White))
        return
    }

    val scope = rememberCoroutineScope()

    if (token == null) {
        val authNavController = rememberNavController()
        NavHost(
            navController = authNavController,
            startDestination = Routes.Login,
            modifier = Modifier.fillMaxSize()
        ) {
            composable<Routes.Login> {
                LoginScreen(
                    navController = authNavController,
                    onLoginSuccess = {
                        scope.launch {
                            token = sessionManager.tokenFlow.first()
                        }
                    }
                )
            }
            composable<Routes.SignUp> {
                RegisterScreen(navController = authNavController)
            }
        }
        return
    }

    var selectedItem by remember { mutableStateOf(0) }
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf<Any>(Routes.Home) }

    val BottomNavItem = listOf(
        BottomNavigationItem("Home", Icons.Default.Home, Icons.Outlined.Home),
        BottomNavigationItem("Communities", Icons.Default.People, Icons.Outlined.People),
        BottomNavigationItem("Mentorship", Icons.Default.School, Icons.Outlined.School),
        BottomNavigationItem("Blogs", Icons.Default.Book, Icons.Outlined.Book),
    )

    val drawerItems = listOf(
        DrawerMenuItem("Profile", Routes.Profile, Icons.Default.Person),
        DrawerMenuItem("Chat", Routes.Chat, Icons.Default.Chat),
        DrawerMenuItem("Notifications", Routes.Notifications, Icons.Default.Notifications),
        DrawerMenuItem("Questions", Routes.Questions, Icons.Default.QuestionAnswer),
        DrawerMenuItem("Events", Routes.Events, Icons.Default.CalendarToday),
        DrawerMenuItem("Alumni Directory", Routes.Alumni, Icons.Default.People),
        DrawerMenuItem("Search", Routes.Search, Icons.Default.Search),
        DrawerMenuItem("Settings", Routes.Settings, Icons.Default.Settings),
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
            ) {
                DrawerHeader()
                Divider(color = Gray400.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(8.dp))

                drawerItems.forEach { item ->
                    val isSelected = currentRoute == item.route
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = if (isSelected) IndigoPrimary else Gray500
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                color = if (isSelected) IndigoPrimary else Gray500
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            if (item.route != null) {
                                currentRoute = item.route
                                navController.navigate(item.route) {
                                    navController.graph.startDestinationRoute?.let { popUpTo(it) { saveState = true } }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color.Transparent,
                            selectedContainerColor = IndigoLightest
                        )
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(
                                        brush = androidx.compose.ui.graphics.Brush.linearGradient(
                                            colors = listOf(IndigoPrimary, IndigoPrimary.copy(alpha = 0.7f))
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "A",
                                    color = White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "AlumniHive",
                                color = IndigoPrimary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = IndigoPrimary
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(Routes.Chat) }) {
                            Icon(
                                Icons.Default.Chat,
                                contentDescription = "Chat",
                                tint = IndigoPrimary
                            )
                        }
                        IconButton(onClick = { navController.navigate(Routes.Notifications) }) {
                            BadgedBox(
                                badge = {
                                    Badge(
                                        containerColor = Red,
                                        contentColor = White
                                    ) {
                                        Text("3", style = MaterialTheme.typography.labelSmall)
                                    }
                                }
                            ) {
                                Icon(
                                    Icons.Default.Notifications,
                                    contentDescription = "Notifications",
                                    tint = IndigoPrimary
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = White,
                        scrolledContainerColor = White
                    )
                )
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White)
                        .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
                ) {
                    AnimatedBottomBar(
                        selectedItem = selectedItem,
                        itemSize = BottomNavItem.size,
                        containerColor = Color.Transparent,
                        indicatorColor = IndigoPrimary,
                        indicatorDirection = IndicatorDirection.TOP,
                        indicatorStyle = IndicatorStyle.WORM
                    ) {
                        BottomNavItem.forEachIndexed { index, navigationItem ->
                            BottomBarItem(
                                modifier = Modifier.align(Alignment.Top),
                                selected = selectedItem == index,
                                onClick = {
                                    selectedItem = index
                                    val route = when (index) {
                                        0 -> Routes.Home
                                        1 -> Routes.Communities
                                        2 -> Routes.Mentorship
                                        3 -> Routes.Blogs
                                        else -> return@BottomBarItem
                                    }
                                    currentRoute = route
                                    navController.navigate(route) {
                                        popUpTo(Routes.Home) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerpadding)
                    .background(Gray50)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Routes.Home,
                ) {
                    composable<Routes.Home> { DashboardScreen(navController = navController) }
                    composable<Routes.Communities> { CommunitiesListScreen(navController = navController) }
                    composable<Routes.Mentorship> { MentorshipListScreen(navController = navController) }
                    composable<Routes.Blogs> { BlogsListScreen(navController = navController) }
                    composable<Routes.Events> { EventsListScreen(navController = navController) }
                    composable<Routes.Chat> { ChatListScreen(navController = navController) }
                    composable<Routes.Notifications> { NotificationsScreen(navController = navController) }
                    composable<Routes.Questions> { QuestionsListScreen(navController = navController) }
                    composable<Routes.Profile> { ProfileScreen(navController = navController) }
                    composable<Routes.Settings> { SettingsScreen(navController = navController) }
                    composable<Routes.Alumni> { AlumniDirectoryScreen(navController = navController) }
                    composable<Routes.Search> { SearchScreen(navController = navController) }
                }
            }
        }
    }
}
