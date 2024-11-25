package com.viniciusjanner.apigithub.framework.data.remote.api

import com.viniciusjanner.apigithub.framework.data.remote.response.PullRequestResponse
import com.viniciusjanner.apigithub.framework.data.remote.response.RepoListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories")
    fun getPopularJavaRepositories(
        @Query("q") query: String = "language:java",
        @Query("sort") sort: String = "stars",
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int
    ): Single<RepoListResponse>

    @GET("repos/{owner}/{repo}/pulls")
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repoName: String,
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int
    ): Single<List<PullRequestResponse>>
}
