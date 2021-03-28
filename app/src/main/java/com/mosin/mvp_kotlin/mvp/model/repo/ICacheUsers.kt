package com.mosin.mvp_kotlin.mvp.model.repo

import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface ICacheUsers {
    fun putTo(users: List<GitHubUser>)
    fun getFrom() : Single<List<GitHubUser>>
}