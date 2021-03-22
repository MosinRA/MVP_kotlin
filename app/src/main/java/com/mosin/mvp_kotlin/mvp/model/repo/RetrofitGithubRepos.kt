package com.mosin.mvp_kotlin.mvp.model.repo

import com.mosin.mvp_kotlin.mvp.model.api.IDataSource
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepos(val api: IDataSource) : IGitHubUserRepos {
    override fun getRepos(user: GitHubUser) =
        api.getUsersRepos(user.reposUrl).subscribeOn(Schedulers.io())
}
