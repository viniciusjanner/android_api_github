package com.viniciusjanner.presentation.repopullrequest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.domain.model.PullRequestModel
import com.viniciusjanner.apigithub.core.usecase.GetPullRequestsUseCase
import com.viniciusjanner.apigithub.presentation.pullrequest.RepoPullRequestViewModel
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class RepoPullRequestViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: GetPullRequestsUseCase

    private lateinit var viewModel: RepoPullRequestViewModel

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<String?>

    @Mock
    private lateinit var repositoriesObserver: Observer<PagingData<PullRequestModel>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = RepoPullRequestViewModel(useCase)
        viewModel.loadingLiveData.observeForever(loadingObserver)
        viewModel.errorLiveData.observeForever(errorObserver)
        viewModel.pullRequestsLiveData.observeForever(repositoriesObserver)
    }

    //
    // deve emitir carregamento verdadeiro, dados de paginação vazios e nenhum erro ao buscar repositórios
    //
    @Test
    fun `should emit loading true, empty paging data and no error when fetching repositories`() {
        // Mocking usecase para retornar um PagingData vazio
        val emptyPagingData = PagingData.empty<PullRequestModel>()
        Mockito.`when`(useCase.invoke("torvalds", "linux")).thenReturn(Observable.just(emptyPagingData))

        // Chamar o método da ViewModel
        viewModel.fetchPullRequests("torvalds", "linux")

        // Verificar se o loading foi setado corretamente
        verify(loadingObserver).onChanged(true)

        // Verificar se o PagingData vazio foi emitido
        verify(repositoriesObserver).onChanged(emptyPagingData)

        // Verificar se o erro foi emitido apenas uma vez
        verify(errorObserver, times(1)).onChanged(null)

        // Verificar se o loading foi finalizado
        verify(loadingObserver).onChanged(false)
    }

    //
    // deve emitir erro quando o caso de uso falhar
    //
    @Test
    fun `should emit error when use case fails`() {
        // Mocking usecase para lançar uma exceção com uma mensagem de erro
        val errorMessage = "Ocorreu um erro. Tente novamente."
        Mockito.`when`(useCase.invoke("torvalds", "linux"))
            .thenReturn(Observable.error(Throwable("Error")))

        // Chamar o método da ViewModel
        viewModel.fetchPullRequests("torvalds", "linux")

        // Verificar se o loading foi setado corretamente
        verify(loadingObserver).onChanged(true)

        // Verificar se o erro foi emitido
        verify(errorObserver).onChanged(errorMessage)

        // Verificar se o loading foi finalizado
        verify(loadingObserver).onChanged(false)
    }
}
