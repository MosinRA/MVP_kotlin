package com.mosin.mvp_kotlin.mvp.presenter

import com.mosin.mvp_kotlin.mvp.model.CountersModel
import com.mosin.mvp_kotlin.mvp.view.MainView

class MainPresenter(val mainView: MainView) {
    val model = CountersModel()

    fun counterClickOne() {
        val nextValue = model.next(0)
        mainView.setBtnTextOne(nextValue.toString())
    }

    fun counterClickTwo() {
        val nextValue = model.next(1)
        mainView.setBtnTextTwo(nextValue.toString())
    }

    fun counterClickThree() {
        val nextValue = model.next(2)
        mainView.setBtnTextThree(nextValue.toString())
    }
}
