package com.example.alumnihivev11.Navigation

import kotlinx.serialization.Serializable

sealed class Navigation{

    @Serializable
    object Main: Navigation()
}

sealed class Routes{

    @Serializable
    object Start: Routes()

    @Serializable
    object Home: Routes()

    @Serializable
    object Communities: Routes()

    @Serializable
    object Mentorship: Routes()

    @Serializable
    object Blogs: Routes()

    @Serializable
    object Chats: Routes()

    @Serializable
    object Notifications: Routes()

    @Serializable
    object Profile: Routes()

    @Serializable
    object SignUp: Routes()
}