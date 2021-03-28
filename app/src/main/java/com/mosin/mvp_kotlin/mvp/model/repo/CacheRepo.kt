package com.mosin.mvp_kotlin.mvp.model.repo

import com.mosin.mvp_kotlin.mvp.model.entity.GitHubRepo
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomGitHubRepo
import com.mosin.mvp_kotlin.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Single

class CacheRepo(private val db: Database) : ICacheRepo {

    override fun putTo(user: GitHubUser, repos: List<GitHubRepo>) {
        val roomUser = db.userDao.findByLogin(user.login)
            ?: throw RuntimeException("Пользователь отсутствует в базе данных")
        val roomRepos = repos.map { repo ->
            RoomGitHubRepo(
                repo.id,
                repo.name,
                repo.forksCount,
                repo.watchersCount,
                repo.language,
                roomUser.id
            )
        }
        db.repositoryDao.insert(roomRepos)
    }

    override fun getFrom(user: GitHubUser) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login)
            ?: throw RuntimeException("No user in DB")
        db.repositoryDao.findForUser(roomUser.id).map { roomRepo ->
            GitHubRepo(
                roomRepo.id,
                roomRepo.name,
                roomRepo.forksCount,
                roomRepo.watchersCount,
                roomRepo.language
            )
        }
    }
}
