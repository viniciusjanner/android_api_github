package com.viniciusjanner.apigithub.framework.usecase

import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.usecase.GetPopularJavaRepositoriesUseCase
import com.viniciusjanner.data.repository.RepoListRepository
import com.viniciusjanner.domain.model.ItemRepoModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPopularJavaRepositoriesUseCaseImpl @Inject constructor(
    private val repository: RepoListRepository
) : GetPopularJavaRepositoriesUseCase {

    override fun invoke(): Observable<PagingData<ItemRepoModel>> {
        return repository.getPopularJavaRepositories()
    }
}
