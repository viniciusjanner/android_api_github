package com.viniciusjanner.apigithub.core.data.repository

import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.domain.model.PullRequestModel
import io.reactivex.rxjava3.core.Observable

interface RepoPullRequestlRepository {
    fun getPullRequests(owner: String, repoName: String): Observable<PagingData<PullRequestModel>>
}
