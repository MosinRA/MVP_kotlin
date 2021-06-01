package com.mosin.mvp_kotlin.ui

import android.app.Application
import com.mosin.mvp_kotlin.di.AppComponent
import com.mosin.mvp_kotlin.di.DaggerAppComponent
import com.mosin.mvp_kotlin.di.modules.AppModule
import com.mosin.mvp_kotlin.mvp.model.entity.room.db.Database

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}