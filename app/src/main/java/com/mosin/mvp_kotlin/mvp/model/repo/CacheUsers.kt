package com.mosin.mvp_kotlin.mvp.model.repo

import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomGithubUser
import com.mosin.mvp_kotlin.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Single


class CacheUsers(private val db: Database) : ICacheUsers {
    override fun putTo(users: List<GitHubUser>) {
        val roomUsers = users.map { user ->
            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
        }
        db.userDao.insert(roomUsers)
    }

    override fun getFrom() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GitHubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }
}