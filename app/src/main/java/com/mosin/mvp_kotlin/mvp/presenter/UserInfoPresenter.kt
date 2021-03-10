package com.mosin.mvp_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.mosin.mvp_kotlin.mvp.view.UserInfoView
import moxy.MvpPresenter

class UserInfoPresenter(val router: Router) :
    MvpPresenter<UserInfoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initUserInfo()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}