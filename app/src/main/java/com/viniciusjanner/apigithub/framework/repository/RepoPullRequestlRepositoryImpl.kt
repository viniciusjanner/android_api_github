package com.viniciusjanner.apigithub.framework.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.viniciusjanner.apigithub.framework.data.remote.api.GitHubApi
import com.viniciusjanner.apigithub.framework.paging.RepoPullRequestPagingSource
import com.viniciusjanner.data.repository.RepoPullRequestlRepository
import com.viniciusjanner.domain.model.PullRequestModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RepoPullRequestlRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : RepoPullRequestlRepository {

    private var currentPagingSource: RepoPullRequestPagingSource? = null

    override fun getPullRequests(owner: String, repoName: String): Observable<PagingData<PullRequestModel>> {
        val pagingSourceFactory = {
            currentPagingSource = RepoPullRequestPagingSource(api, owner, repoName)
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
