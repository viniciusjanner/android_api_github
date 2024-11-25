package com.viniciusjanner.apigithub.framework.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.viniciusjanner.apigithub.core.domain.model.PullRequestModel
import com.viniciusjanner.apigithub.framework.data.remote.api.GitHubApi
import com.viniciusjanner.apigithub.framework.data.remote.response.PullRequestResponse
import com.viniciusjanner.apigithub.framework.data.remote.response.toModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

class RepoPullRequestPagingSource(
    private val api: GitHubApi,
    private val owner: String,
    private val repoName: String
) : RxPagingSource<Int, PullRequestModel>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PullRequestModel>> {
        val page = params.key ?: 1

        return api.getPullRequests(owner = owner, repoName = repoName, page = page)
            .subscribeOn(Schedulers.io())
            .map { response ->
                val itemsResponse: List<PullRequestResponse> = response ?: emptyList()
                val itemsModel = itemsResponse.map { it.toModel() }

                LoadResult.Page(
                    data = itemsModel,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (itemsModel.isEmpty()) null else page + 1
                ) as LoadResult<Int, PullRequestModel>
            }
            .onErrorReturn { e ->
                when (e) {
                    is IOException, is HttpException -> LoadResult.Error(e)
                    else -> throw e
                }
            }
    }

    override fun getRefreshKey(state: PagingState<Int, PullRequestModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
