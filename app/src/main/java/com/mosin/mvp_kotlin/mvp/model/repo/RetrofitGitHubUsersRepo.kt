package com.mosin.mvp_kotlin.mvp.model.repo

import com.mosin.mvp_kotlin.mvp.model.api.IDataSource
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomGithubUser
import com.mosin.mvp_kotlin.mvp.model.entity.room.db.Database
import com.mosin.mvp_kotlin.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: ICacheUsers
) : IGitHubUsersRepo {
    override fun getUsers(): Single<List<GitHubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline)
                api.getUsers().doAfterSuccess { users ->
                    cache.putTo(users)
                }
            else cache.getFrom()
        }.subscribeOn(Schedulers.io())
}