package com.mosin.mvp_kotlin.ui.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.mosin.mvp_kotlin.mvp.model.image.IImageLoader
import com.mosin.mvp_kotlin.mvp.model.network.INetworkStatus
import com.mosin.mvp_kotlin.mvp.model.repo.IImageCache
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.IOException

class GlideImageLoader(val imageCache: IImageCache, val networkStatus: INetworkStatus) :
    IImageLoader<ImageView> {

    override fun load(url: String, container: ImageView) {
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                Glide.with(container.context)
                    .asBitmap()
                    .load(url)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            e?.printStackTrace()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            imageCache.putImage(url, resource?.let { convertBitmapToByteArray(it) })
                                .observeOn(Schedulers.io())
                                .subscribe()
                            return false
                        }
                    })
                    .into(container)
            } else {
                imageCache.getBytes(url).observeOn(AndroidSchedulers.mainThread())
                    .subscribe { byteArray ->
                        Glide.with(container.context)
                            .asBitmap()
                            .load(byteArray)
                            .into(container)
                    }
            }
        }
    }

    fun convertBitmapToByteArray(bmp: Bitmap): ByteArray {
        val out = ByteArrayOutputStream()
        try {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return out.toByteArray()
    }
}