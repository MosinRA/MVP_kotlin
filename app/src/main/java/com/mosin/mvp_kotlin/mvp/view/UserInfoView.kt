package com.mosin.mvp_kotlin.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserInfoView : MvpView {
    fun setLogin(text: String)
}