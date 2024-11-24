package com.viniciusjanner.apigithub.core.usecase

import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import io.reactivex.rxjava3.core.Flowable

interface GetPopularJavaRepositoriesUseCase {
    fun invoke(): Flowable<PagingData<ItemRepoModel>>
}
