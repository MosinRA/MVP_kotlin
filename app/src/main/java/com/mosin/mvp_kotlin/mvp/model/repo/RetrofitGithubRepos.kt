package com.mosin.mvp_kotlin.mvp.model.repo

import com.mosin.mvp_kotlin.mvp.model.api.IDataSource
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubRepo
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomGitHubRepo
import com.mosin.mvp_kotlin.mvp.model.entity.room.db.Database
import com.mosin.mvp_kotlin.mvp.model.network.INetworkStatus
import com.mosin.mvp_kotlin.mvp.presenter.UserInfoPresenter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepos(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: ICacheRepo
) : IGitHubUserRepos {
    override fun getRepos(user: GitHubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUsersRepos(url)
                        .doAfterSuccess { repositories ->
                            cache.putTo(user, repositories)
                        }
                } ?: Single.error(RuntimeException("User has no repos url"))
            } else cache.getFrom(user)
        }.subscribeOn(Schedulers.io())
}


