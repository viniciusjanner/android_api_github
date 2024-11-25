package com.viniciusjanner.framework.data.remote.api

import com.google.gson.Gson
import com.viniciusjanner.apigithub.framework.data.remote.api.GitHubApi
import com.viniciusjanner.apigithub.framework.data.remote.response.ItemRepoResponse
import com.viniciusjanner.apigithub.framework.data.remote.response.OwnerResponse
import com.viniciusjanner.apigithub.framework.data.remote.response.RepoListResponse
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitHubApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var gitHubApi: GitHubApi

    @Before
    fun setUp() {
        // Inicializa o MockWebServer
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Cria o Retrofit para os testes
        val okHttpClient = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Usa a URL do MockWebServer
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        // Cria a instância da API
        gitHubApi = retrofit.create(GitHubApi::class.java)

        // Configura o RxJava para usar Schedulers síncronos nos testes
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        // Finaliza o MockWebServer
        mockWebServer.shutdown()

        // Restaura o comportamento padrão do RxJava
        RxJavaPlugins.reset()
    }

    private fun getJsonSuccessResponse(): String {
        // Criando objetos de exemplo
        val owner = OwnerResponse("user1", "https://avatar.url")
        val itemRepo = ItemRepoResponse(
            id = 1L,
            name = "Repo 1",
            owner = owner,
            description = "Descrição do Repositório",
            stargazersCount = 100L,
            forksCount = 50L
        )

        val repoListResponse = RepoListResponse(
            totalCount = 1L,
            isIncompleteResults = false,
            items = listOf(itemRepo)
        )

        // Convertendo o objeto para JSON usando Gson
        val gson = Gson()
        val json = gson.toJson(repoListResponse)

        return json
    }

    @Test
    fun `test getPopularJavaRepositories returns successful response synchronously`() {
        // Mocka a resposta da API
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(getJsonSuccessResponse())
        mockWebServer.enqueue(mockResponse)

        // Chama o método da API de forma síncrona
        val response = gitHubApi.getPopularJavaRepositories(page = 1)
            .subscribeOn(Schedulers.io()) // Garante que a chamada será feita no IO Scheduler
            .observeOn(Schedulers.trampoline()) // Garante que a observação será feita no mesmo thread
            .blockingGet() // Espera pela resposta de forma síncrona

        // Verifica se a resposta é de sucesso
        assertNotNull(response)
        assertTrue(response.items?.isNotEmpty() == true)
        assertEquals("Repo 1", response.items!![0].name)

        // Verifica se a requisição foi feita para o caminho correto
        val request = mockWebServer.takeRequest()
        println("Request made to: ${request.path}")

        // Verifica se o observable completou
        assertTrue(response.items?.isNotEmpty() == true)
    }
}
