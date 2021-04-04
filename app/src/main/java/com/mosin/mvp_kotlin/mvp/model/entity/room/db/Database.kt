package com.mosin.mvp_kotlin.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomCachedImage
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomGitHubRepo
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomGithubUser
import com.mosin.mvp_kotlin.mvp.model.entity.room.dao.ImageDao
import com.mosin.mvp_kotlin.mvp.model.entity.room.dao.RepositoryDao
import com.mosin.mvp_kotlin.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGitHubRepo::class,
        RoomCachedImage::class
    ],
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object{
        const val DB_NAME = "dbGH.db"
        private var instance: Database? = null
        fun getInstance() = instance?: throw IllegalAccessException("База данных не создана")
        fun create (context: Context){
            if (instance == null){
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
//                    .addMigrations(MIGRATION_2_3)
//                    .allowMainThreadQueries()
                    .build()
            }
        }

    }
}