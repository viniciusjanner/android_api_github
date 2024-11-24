package com.viniciusjanner.apigithub.core.data.repository

import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import io.reactivex.rxjava3.core.Flowable

interface RepoListRepository {
    fun getPopularJavaRepositories(): Flowable<PagingData<ItemRepoModel>>
}
