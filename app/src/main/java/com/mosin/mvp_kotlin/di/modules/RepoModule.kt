package com.mosin.mvp_kotlin.di.modules

import com.mosin.mvp_kotlin.mvp.model.api.IDataSource
import com.mosin.mvp_kotlin.mvp.model.network.INetworkStatus
import com.mosin.mvp_kotlin.mvp.model.repo.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ICacheUsers
    ): IGitHubUsersRepo = RetrofitGitHubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun reposRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ICacheRepo
    ): IGitHubUserRepos = RetrofitGithubRepos(api, networkStatus, cache)

}