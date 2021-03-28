package com.mosin.mvp_kotlin.mvp.model.repo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.mosin.mvp_kotlin.mvp.model.entity.room.RoomCachedImage
import com.mosin.mvp_kotlin.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class RoomImageCache(val context: Context, val db: Database) : IImageCache {

    override fun getBytes(url: String) = Single.fromCallable {
        val path = db.imageDao.findImageByUrl(url).path
        val bitmap = BitmapFactory.decodeFile(path)
        val out = ByteArrayOutputStream()
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        out.toByteArray()
    }.subscribeOn(Schedulers.io())

    override fun putImage(url: String, bytes: ByteArray?) = Completable.fromCallable {
        bytes?.let {
            val file = File(context.getExternalFilesDir("Image"), "${UUID.randomUUID()}.png")
            val fos = FileOutputStream(file)
            fos.write(bytes)
            fos.flush()
            fos.close()
            val image = RoomCachedImage(url, file.absolutePath)
            db.imageDao.insert(image)
        }
    }.subscribeOn(Schedulers.io())
}