package com.mosin.mvp_kotlin.mvp.model

import com.github.terrakok.cicerone.Screen
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser

class GitHubUsersRepo : Screen {

    private val users = listOf<GitHubUser>(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5")
    )

    fun getUsers(): List<GitHubUser> {
        return users
    }

    fun getCurrentUser(pos: Int): GitHubUser {
        return users[pos]
    }
}