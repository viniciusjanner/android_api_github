package com.viniciusjanner.apigithub.presentation.pullrequest

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.domain.model.PullRequestModel
import com.viniciusjanner.apigithub.core.usecase.GetPullRequestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class RepoPullRequestViewModel @Inject constructor(
    private val getPullRequestsUseCase: GetPullRequestsUseCase
) : ViewModel() {

    private val _pullRequestsSubject = BehaviorSubject.create<PagingData<PullRequestModel>>()
    val pullRequestsLiveData: LiveData<PagingData<PullRequestModel>> =
        _pullRequestsSubject.toFlowable(BackpressureStrategy.BUFFER)
            .toLiveData()

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> = _errorLiveData

    @SuppressLint("CheckResult", "LongLogTag")
    fun fetchPullRequests(owner: String, repoName: String) {
        _loadingLiveData.postValue(true)
        _errorLiveData.postValue(null)

        _pullRequestsSubject.onNext(PagingData.empty())

        getPullRequestsUseCase.invoke(owner, repoName)
            .doOnSubscribe {
                _loadingLiveData.postValue(true)
                _errorLiveData.postValue(null)
            }
            .doFinally {
                _loadingLiveData.postValue(false)
            }
            .subscribe(
                { pagingData ->
                    _pullRequestsSubject.onNext(pagingData)
                },
                { error ->
                    _errorLiveData.postValue("Ocorreu um erro. Tente novamente.")
                }
            )
    }
}
