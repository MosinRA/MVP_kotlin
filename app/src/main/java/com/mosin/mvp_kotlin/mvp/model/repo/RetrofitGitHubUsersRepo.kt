package com.mosin.mvp_kotlin.mvp.model.repo

import com.mosin.mvp_kotlin.mvp.model.api.IDataSource
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUsersRepo(val api: IDataSource) : IGitHubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}