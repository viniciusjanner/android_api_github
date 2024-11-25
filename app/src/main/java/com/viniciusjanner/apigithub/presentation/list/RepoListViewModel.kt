package com.viniciusjanner.apigithub.presentation.list

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.usecase.GetPopularJavaRepositoriesUseCase
import com.viniciusjanner.domain.model.ItemRepoModel
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

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> = _errorLiveData

    @SuppressLint("CheckResult")
    fun fetchPopularJavaRepositories() {
        _loadingLiveData.postValue(true)
        _errorLiveData.postValue(null)

        _repositoriesSubject.onNext(PagingData.empty())

        getPopularJavaRepositoriesUseCase.invoke()
            .doFinally {
                _loadingLiveData.postValue(false)
            }
            .subscribe(
                { pagingData ->
                    _repositoriesSubject.onNext(pagingData)
                },
                { error ->
                    _errorLiveData.postValue("Ocorreu um erro. Tente novamente.")
                }
            )
    }
}
