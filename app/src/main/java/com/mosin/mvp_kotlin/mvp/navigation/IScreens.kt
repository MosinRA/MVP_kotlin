package com.mosin.mvp_kotlin.mvp.navigation

import com.github.terrakok.cicerone.Screen
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser

interface IScreens {
    fun users(): Screen
    fun user(user: GitHubUser) : Screen
}