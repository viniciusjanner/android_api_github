package com.viniciusjanner.apigithub.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import com.viniciusjanner.apigithub.framework.data.remote.api.GitHubApi
import com.viniciusjanner.apigithub.framework.data.remote.response.RepoItemResponse
import com.viniciusjanner.apigithub.framework.data.remote.response.RepoListResponse
import com.viniciusjanner.apigithub.framework.data.remote.response.toItemRepoModel
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

class RepoListPagingSource(
    private val api: GitHubApi
) : PagingSource<Int, ItemRepoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemRepoModel> {

        val page = params.key ?: 1

        return try {
            val response: RepoListResponse = api.getPopularJavaRepositories(page = page)
                .subscribeOn(Schedulers.io())
                .blockingGet()

            val itemsResponse: List<RepoItemResponse> = response.items ?: emptyList()

            val itemsModel = itemsResponse.map { it.toItemRepoModel() }

            LoadResult.Page(
                data = itemsModel,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (itemsModel.isEmpty()) null else page + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)

        } catch (e: HttpException) {
            LoadResult.Error(e)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemRepoModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
