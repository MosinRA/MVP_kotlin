package com.mosin.mvp_kotlin.mvp.model

import com.github.terrakok.cicerone.Screen
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Observable

class GitHubUsersRepo : Screen {

    private val users = listOf<GitHubUser>(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5"),
        GitHubUser("login6")
    )

    fun getUsers(): Observable<List<GitHubUser>> {
        return Observable.just(users)
    }
}