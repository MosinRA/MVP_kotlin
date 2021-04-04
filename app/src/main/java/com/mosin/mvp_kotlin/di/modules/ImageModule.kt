package com.mosin.mvp_kotlin.di.modules

import android.widget.ImageView
import com.mosin.mvp_kotlin.mvp.model.image.IImageLoader
import com.mosin.mvp_kotlin.mvp.model.network.INetworkStatus
import com.mosin.mvp_kotlin.mvp.model.repo.IImageCache
import com.mosin.mvp_kotlin.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ImageModule {

    @Singleton
    @Provides
    fun glideImageLoader(
        imageCache: IImageCache,
        networkStatus: INetworkStatus
    ): IImageLoader<ImageView> = GlideImageLoader(imageCache, networkStatus)
}
