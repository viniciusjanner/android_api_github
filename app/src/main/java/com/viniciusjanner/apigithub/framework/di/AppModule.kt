package com.viniciusjanner.apigithub.framework.di

import com.viniciusjanner.apigithub.framework.imageloader.GlideImageLoader
import com.viniciusjanner.apigithub.framework.imageloader.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface AppModule {

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader
}
