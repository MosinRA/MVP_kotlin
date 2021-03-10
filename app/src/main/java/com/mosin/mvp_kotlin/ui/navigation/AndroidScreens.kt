package com.mosin.mvp_kotlin.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import com.mosin.mvp_kotlin.mvp.navigation.IScreens
import com.mosin.mvp_kotlin.ui.fragment.UserInfoFragment
import com.mosin.mvp_kotlin.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GitHubUser) = FragmentScreen { UserInfoFragment.newInstance(user) }
}