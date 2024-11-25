package com.viniciusjanner.data.repository

import androidx.paging.PagingData
import com.viniciusjanner.domain.model.PullRequestModel
import io.reactivex.rxjava3.core.Observable

interface RepoPullRequestlRepository {
    fun getPullRequests(owner: String, repoName: String): Observable<PagingData<PullRequestModel>>
}
