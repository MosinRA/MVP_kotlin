package com.mosin.mvp_kotlin.mvp.model.repo

import com.mosin.mvp_kotlin.mvp.model.entity.GitHubRepo
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubUserRepos {
    fun getRepos(user: GitHubUser): Single<List<GitHubRepo>>
}