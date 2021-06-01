package com.mosin.mvp_kotlin.mvp.model.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}