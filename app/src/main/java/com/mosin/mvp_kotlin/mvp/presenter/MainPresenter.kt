package com.mosin.mvp_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.mosin.mvp_kotlin.mvp.navigation.IScreens
import com.mosin.mvp_kotlin.mvp.view.UsersView
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<UsersView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backPressed() {
        router.exit()
    }
}
