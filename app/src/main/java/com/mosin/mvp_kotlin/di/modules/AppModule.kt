package com.mosin.mvp_kotlin.di.modules

import com.mosin.mvp_kotlin.ui.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@Module
class AppModule(val app: App) {

    @Named("ui")
    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun app(): App = app
}