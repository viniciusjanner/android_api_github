package com.viniciusjanner.apigithub.framework.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import com.viniciusjanner.apigithub.framework.data.remote.api.GitHubApi
import com.viniciusjanner.apigithub.framework.data.remote.response.ItemRepoResponse
import com.viniciusjanner.apigithub.framework.data.remote.response.toItemRepoModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

class RepoListPagingSource(
    private val api: GitHubApi
) : RxPagingSource<Int, ItemRepoModel>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ItemRepoModel>> {
        val page = params.key ?: 1

        return api.getPopularJavaRepositories(page = page)
            .subscribeOn(Schedulers.io())
            .map { response ->
                val itemsResponse: List<ItemRepoResponse> = response.items ?: emptyList()
                val itemsModel = itemsResponse.map { it.toItemRepoModel() }

                LoadResult.Page(
                    data = itemsModel,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (itemsModel.isEmpty()) null else page + 1
                ) as LoadResult<Int, ItemRepoModel>
            }
            .onErrorReturn { e ->
                when (e) {
                    is IOException, is HttpException -> LoadResult.Error(e)
                    else -> throw e
                }
            }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemRepoModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}