package com.mosin.mvp_kotlin.ui

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.mosin.mvp_kotlin.mvp.model.entity.room.db.Database

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
}