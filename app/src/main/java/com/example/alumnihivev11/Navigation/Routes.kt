package com.example.alumnihivev11.Navigation

import kotlinx.serialization.Serializable

sealed class Navigation{

    @Serializable
    object Main: Navigation()

    @Serializable
    object Auth: Navigation()
}

sealed class Routes{

    @Serializable
    object Start: Routes()

    @Serializable
    object Login: Routes()

    @Serializable
    object Register: Routes()

    @Serializable
    object Home: Routes()

    @Serializable
    object Communities: Routes()

    @Serializable
    object CommunityDetail: Routes()

    @Serializable
    object Mentorship: Routes()

    @Serializable
    object MentorDetail: Routes()

    @Serializable
    object Blogs: Routes()

    @Serializable
    object BlogDetail: Routes()

    @Serializable
    object Questions: Routes()

    @Serializable
    object QuestionDetail: Routes()

    @Serializable
    object Events: Routes()

    @Serializable
    object EventDetail: Routes()

    @Serializable
    object Chat: Routes()

    @Serializable
    object ChatDetail: Routes()

    @Serializable
    object Notifications: Routes()

    @Serializable
    object Profile: Routes()

    @Serializable
    object ProfileEdit: Routes()

    @Serializable
    object Settings: Routes()

    @Serializable
    object Alumni: Routes()

    @Serializable
    object Search: Routes()

    @Serializable
    object SignUp: Routes()
}