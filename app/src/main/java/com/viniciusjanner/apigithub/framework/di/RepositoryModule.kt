package com.viniciusjanner.apigithub.framework.di

import com.viniciusjanner.apigithub.core.data.repository.RepoListRepository
import com.viniciusjanner.apigithub.core.data.repository.RepoPullRequestlRepository
import com.viniciusjanner.apigithub.framework.repository.RepoListRepositoryImpl
import com.viniciusjanner.apigithub.framework.repository.RepoPullRequestlRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepoListRepository(impl: RepoListRepositoryImpl): RepoListRepository

    @Binds
    @Singleton
    abstract fun bindRepoDetailRepository(impl: RepoPullRequestlRepositoryImpl): RepoPullRequestlRepository
}
