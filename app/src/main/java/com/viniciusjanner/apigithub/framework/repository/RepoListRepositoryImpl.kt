package com.viniciusjanner.apigithub.framework.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.viniciusjanner.apigithub.core.data.repository.RepoListRepository
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import com.viniciusjanner.apigithub.framework.data.remote.api.GitHubApi
import com.viniciusjanner.apigithub.framework.paging.RepoListPagingSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RepoListRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : RepoListRepository {

    private var currentPagingSource: RepoListPagingSource? = null

    override fun getPopularJavaRepositories(): Observable<PagingData<ItemRepoModel>> {
        val pagingSourceFactory = {
            currentPagingSource = RepoListPagingSource(api)
            currentPagingSource!!
        }

        currentPagingSource?.invalidate()

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSourceFactory
        ).observable
    }
}
