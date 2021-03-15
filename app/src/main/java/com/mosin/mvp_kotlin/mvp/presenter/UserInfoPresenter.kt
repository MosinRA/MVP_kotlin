package com.mosin.mvp_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import com.mosin.mvp_kotlin.mvp.view.UserInfoView
import moxy.MvpPresenter

class UserInfoPresenter(val router: Router, val user: GitHubUser) :
    MvpPresenter<UserInfoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}