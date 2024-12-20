package com.viniciusjanner.apigithub.core.usecase

import androidx.paging.PagingData
import com.viniciusjanner.domain.model.ItemRepoModel
import io.reactivex.rxjava3.core.Observable

interface GetPopularJavaRepositoriesUseCase {
    fun invoke(): Observable<PagingData<ItemRepoModel>>
}
