package com.mosin.mvp_kotlin.mvp.model.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IImageCache {
    fun putImage(url: String, bytes: ByteArray?): Completable
    fun getBytes(url: String): Single<ByteArray>
}