package com.mosin.mvp_kotlin.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface UserInfoView : MvpView {
    fun init()
    fun updateReposList()
    fun setLogin(text: String)
    fun setImage(url: String)

    @OneExecution
    fun showRepoInfo(scoreFork: Int, scoreViews: Int, language: String)
}