package com.viniciusjanner.apigithub.framework.di

import com.viniciusjanner.apigithub.core.usecase.GetPopularJavaRepositoriesUseCase
import com.viniciusjanner.apigithub.core.usecase.GetPullRequestsUseCase
import com.viniciusjanner.apigithub.framework.usecase.GetPopularJavaRepositoriesUseCaseImpl
import com.viniciusjanner.apigithub.framework.usecase.GetPullRequestsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetPopularJavaRepositoriesUseCase(impl: GetPopularJavaRepositoriesUseCaseImpl): GetPopularJavaRepositoriesUseCase

    @Binds
    @Singleton
    abstract fun bindGetPullRequestsUseCase(impl: GetPullRequestsUseCaseImpl): GetPullRequestsUseCase
}
