package com.viniciusjanner.framework.usecase.repolist
import com.viniciusjanner.apigithub.core.usecase.GetPopularJavaRepositoriesUseCase
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetPopularJavaRepositoriesUseCaseImplTest {

    @Mock
    lateinit var useCase: GetPopularJavaRepositoriesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test invoke returns error when repository fails synchronously`() {
        // Simula um erro do repositório
        val throwable = Throwable("Erro no repositório")

        // Assegure que o método invoke() vai retornar um erro
        Mockito.`when`(useCase.invoke()).thenReturn(Observable.error(throwable))

        // Chama o método invoke() e usa o TestObserver para observar o erro
        val testObserver = useCase.invoke().test()

        // Espera que o Observable emita um erro
        testObserver.await()

        // Verifica se o erro foi o esperado
        testObserver.assertError { it.message == "Erro no repositório" }
    }
}
