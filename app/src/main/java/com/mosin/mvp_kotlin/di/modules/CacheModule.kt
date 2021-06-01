package com.mosin.mvp_kotlin.di.modules

import androidx.room.Room
import com.mosin.mvp_kotlin.mvp.model.entity.room.db.Database
import com.mosin.mvp_kotlin.mvp.model.repo.*
import com.mosin.mvp_kotlin.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Provides
    @Singleton
    fun usersCache(db: Database): ICacheUsers = CacheUsers(db)

    @Singleton
    @Provides
    fun reposCache(database: Database): ICacheRepo = CacheRepo(database)

    @Singleton
    @Provides
    fun imageCache(app: App, database: Database): IImageCache = RoomImageCache(app, database)

}