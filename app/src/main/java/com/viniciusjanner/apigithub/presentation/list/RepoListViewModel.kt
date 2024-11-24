package com.viniciusjanner.apigithub.presentation.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import com.viniciusjanner.apigithub.core.usecase.GetPopularJavaRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val getPopularJavaRepositoriesUseCase: GetPopularJavaRepositoriesUseCase
) : ViewModel() {

    private val _repositoriesSubject = BehaviorSubject.create<PagingData<ItemRepoModel>>()

    val repositoriesLiveData: LiveData<PagingData<ItemRepoModel>> =
        _repositoriesSubject.toFlowable(BackpressureStrategy.BUFFER)
            .toLiveData()

    init {
        fetchPopularJavaRepositories()
    }

    @SuppressLint("CheckResult")
   fun fetchPopularJavaRepositories() {
        getPopularJavaRepositoriesUseCase.invoke()
            .subscribe(
                { pagingData ->
                    _repositoriesSubject.onNext(pagingData)
                },
                { error ->
                    Log.e("RepoListViewModel", "Error: ${error.message}")
                    error.printStackTrace()
                }
            )
    }
}
