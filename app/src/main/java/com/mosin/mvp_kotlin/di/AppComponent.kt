package com.mosin.mvp_kotlin.di

import com.mosin.mvp_kotlin.di.modules.*
import com.mosin.mvp_kotlin.mvp.presenter.MainPresenter
import com.mosin.mvp_kotlin.mvp.presenter.UserInfoPresenter
import com.mosin.mvp_kotlin.mvp.presenter.UsersPresenter
import com.mosin.mvp_kotlin.ui.activity.MainActivity
import com.mosin.mvp_kotlin.ui.adapter.UsersRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CiceroneModule::class,
        RepoModule::class,
        CacheModule::class,
        ImageModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userInfoPresenter: UserInfoPresenter)
    fun inject(usersRvAdapter: UsersRVAdapter)
}