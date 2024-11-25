package com.viniciusjanner.presentation.repolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import com.viniciusjanner.apigithub.core.usecase.GetPopularJavaRepositoriesUseCase
import com.viniciusjanner.apigithub.presentation.list.RepoListViewModel
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class RepoListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: GetPopularJavaRepositoriesUseCase

    private lateinit var viewModel: RepoListViewModel

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<String?>

    @Mock
    private lateinit var repositoriesObserver: Observer<PagingData<ItemRepoModel>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = RepoListViewModel(useCase)
        viewModel.loadingLiveData.observeForever(loadingObserver)
        viewModel.errorLiveData.observeForever(errorObserver)
        viewModel.repositoriesLiveData.observeForever(repositoriesObserver)
    }

    //
    // deve emitir carregamento verdadeiro, dados de paginação vazios e nenhum erro ao buscar repositórios
    //
    @Test
    fun `should emit loading true, empty paging data and no error when fetching repositories`() {
        // Mocking usecase para retornar um PagingData vazio
        val emptyPagingData = PagingData.empty<ItemRepoModel>()
        Mockito.`when`(useCase.invoke()).thenReturn(Observable.just(emptyPagingData))

        // Chamar o método da ViewModel
        viewModel.fetchPopularJavaRepositories()

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
        Mockito.`when`(useCase.invoke())
            .thenReturn(Observable.error(Throwable("Error")))

        // Chamar o método da ViewModel
        viewModel.fetchPopularJavaRepositories()

        // Verificar se o loading foi setado corretamente
        verify(loadingObserver).onChanged(true)

        // Verificar se o erro foi emitido
        verify(errorObserver).onChanged(errorMessage)

        // Verificar se o loading foi finalizado
        verify(loadingObserver).onChanged(false)
    }
}
