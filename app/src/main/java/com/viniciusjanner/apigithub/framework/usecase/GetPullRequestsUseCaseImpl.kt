package com.viniciusjanner.apigithub.framework.usecase

import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.data.repository.RepoPullRequestlRepository
import com.viniciusjanner.apigithub.core.domain.model.PullRequestModel
import com.viniciusjanner.apigithub.core.usecase.GetPullRequestsUseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPullRequestsUseCaseImpl @Inject constructor(
    private val repository: RepoPullRequestlRepository
) : GetPullRequestsUseCase {

    override fun invoke(owner: String, repoName: String): Observable<PagingData<PullRequestModel>> {
        return repository.getPullRequests(owner, repoName)
    }
}