package com.mosin.mvp_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import com.mosin.mvp_kotlin.mvp.model.repo.IGitHubUsersRepo
import com.mosin.mvp_kotlin.mvp.navigation.IScreens
import com.mosin.mvp_kotlin.mvp.presenter.list.IUserListPresenter
import com.mosin.mvp_kotlin.mvp.view.UsersView
import com.mosin.mvp_kotlin.mvp.view.list.IUserItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class UsersPresenter() : MvpPresenter<UsersView>() {

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var usersRepo: IGitHubUsersRepo

    @field:Named("ui")
    @Inject
    lateinit var uiScheduler: Scheduler


    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()
    val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            val user = usersListPresenter.users[view.pos]
            router.navigateTo(screens.user(user))
        }
    }

    fun loadData() {
        val disposable = usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ usersList ->
                usersListPresenter.users.addAll(usersList)
                viewState.updateList()
            }, { error ->
                println("Error: ${error.message}")
            })
        compositeDisposable.add(disposable)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}