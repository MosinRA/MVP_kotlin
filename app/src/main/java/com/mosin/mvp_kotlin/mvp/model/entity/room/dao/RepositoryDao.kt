package com.mosin.mvp_kotlin.mvp.model.entity.room.dao

import androidx.room.*
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomGitHubRepo

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomGitHubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RoomGitHubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomGitHubRepo>)

    @Update
    fun update(user: RoomGitHubRepo)

    @Update
    fun update(vararg users: RoomGitHubRepo)

    @Update
    fun update(user: List<RoomGitHubRepo>)

    @Delete
    fun delete(user: RoomGitHubRepo)

    @Delete
    fun delete(vararg users: RoomGitHubRepo)

    @Delete
    fun delete(user: List<RoomGitHubRepo>)

    @Query("SELECT * FROM RoomGitHubRepo")
    fun getAll(): List<RoomGitHubRepo>

    @Query("SELECT * FROM RoomGitHubRepo WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomGitHubRepo>
}