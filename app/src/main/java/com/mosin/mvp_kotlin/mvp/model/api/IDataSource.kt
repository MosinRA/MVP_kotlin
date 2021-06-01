package com.mosin.mvp_kotlin.mvp.model.api

import com.mosin.mvp_kotlin.mvp.model.entity.GitHubRepo
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {

    @GET("/users")
    fun getUsers(): Single<List<GitHubUser>>

    @GET
    fun getUsersRepos(@Url url: String): Single<List<GitHubRepo>>
}