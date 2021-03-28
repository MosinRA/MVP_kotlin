package com.mosin.mvp_kotlin.mvp.model.entity.room.dao

import androidx.room.*
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomCachedImage
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomGitHubRepo

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomCachedImage: RoomCachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomCachedImages: RoomCachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomCachedImages: List<RoomCachedImage>)

    @Update
    fun update(repository: RoomGitHubRepo)

    @Update
    fun update(vararg roomCachedImages: RoomCachedImage)

    @Update
    fun update(roomCachedImages: List<RoomCachedImage>)

    @Delete
    fun delete(roomCachedImage: RoomCachedImage)

    @Delete
    fun delete(vararg roomCachedImages: RoomCachedImage)

    @Delete
    fun delete(roomCachedImages: List<RoomCachedImage>)

    @Query("SELECT * FROM RoomCachedImage")
    fun getAll(): List<RoomCachedImage>

    @Query("SELECT * FROM RoomCachedImage WHERE url = :url")
    fun findImageByUrl(url: String): RoomCachedImage
}