package com.viniciusjanner.data.repository

import androidx.paging.PagingData
import com.viniciusjanner.domain.model.ItemRepoModel
import io.reactivex.rxjava3.core.Observable

interface RepoListRepository {
    fun getPopularJavaRepositories(): Observable<PagingData<ItemRepoModel>>
}
