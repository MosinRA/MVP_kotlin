package com.mosin.mvp_kotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomGitHubRepo(
    @PrimaryKey val id: String,
    val name: String,
    val forksCount: Int,
    val watchersCount: Int,
    val language: String?,
    var userId: String
)