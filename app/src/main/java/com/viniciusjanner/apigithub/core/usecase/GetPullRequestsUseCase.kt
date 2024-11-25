package com.viniciusjanner.apigithub.core.usecase

import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.domain.model.PullRequestModel
import io.reactivex.rxjava3.core.Observable

interface GetPullRequestsUseCase {
    fun invoke(owner: String, repoName: String): Observable<PagingData<PullRequestModel>>
}
