package com.viniciusjanner.apigithub.framework.usecase

import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.data.repository.RepoListRepository
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import com.viniciusjanner.apigithub.core.usecase.GetPopularJavaRepositoriesUseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPopularJavaRepositoriesUseCaseImpl @Inject constructor(
    private val repository: RepoListRepository
) : GetPopularJavaRepositoriesUseCase {

    override fun invoke(): Observable<PagingData<ItemRepoModel>> {
        return repository.getPopularJavaRepositories()
    }
}
