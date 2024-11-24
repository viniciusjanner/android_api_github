package com.viniciusjanner.apigithub.framework.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.flowable
import com.viniciusjanner.apigithub.core.data.repository.RepoListRepository
import com.viniciusjanner.apigithub.framework.data.remote.api.GitHubApi
import com.viniciusjanner.apigithub.framework.paging.RepoListPagingSource
import javax.inject.Inject

class RepoListRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : RepoListRepository {

    override fun getPopularJavaRepositories() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { RepoListPagingSource(api) }
    ).flowable
}
