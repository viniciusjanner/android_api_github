# GitHub API Android

Este é um aplicativo Android desenvolvido com Kotlin que consome a API do GitHub para mostrar repositórios populares. O projeto segue boas práticas de desenvolvimento, utilizando Clean Architecture, MVVM, injeção de dependência com Dagger Hilt, e outros recursos essenciais do ecossistema Android.

## Funcionalidades

- **Listagem de Repositórios**: Exibe uma lista de repositórios populares, com detalhes sobre cada um.
- **Paginação**: Implementação de paginação utilizando `PagingData` para carregar dados de forma eficiente.
- **Refresh**: Implementação de Swipe to Refresh com `SwipeRefreshLayout` para carregar os dados novamente.
- **Detalhamento do Repositório**: A partir da listagem de repositórios, é possível acessar informações detalhadas de cada repositório, como pull requests abertas e fechadas.

## Arquitetura

A arquitetura do projeto foi baseada nos seguintes princípios:

- **Clean Architecture**: Separação clara entre as camadas de apresentação, domínio e dados, permitindo fácil manutenção e escalabilidade.
- **MVVM (Model-View-ViewModel)**: A comunicação entre a interface do usuário e os dados é realizada via ViewModels, garantindo que a lógica de negócios não seja misturada com a camada de UI.
- **Injeção de Dependências com Dagger Hilt**: Usando o Dagger Hilt para injeção de dependências, o projeto facilita o gerenciamento das dependências de maneira centralizada e modular.

## Tecnologias e Ferramentas Utilizadas

- **Kotlin**: Linguagem moderna para desenvolvimento Android.
- **Dagger Hilt**: Biblioteca para injeção de dependências.
- **RxJava 3**: Para gerenciamento de fluxo assíncrono e reativo.
- **Paging 3**: Para carregar grandes listas de dados de forma eficiente com suporte a paginação.
- **Room**: Biblioteca para persistência de dados no dispositivo.
- **Glide**: Para carregamento de imagens de maneira eficiente.
- **Retrofit**: Para fazer chamadas HTTP para a API do GitHub.
- **JUnit, Mockito**: Para realização de testes unitários e mocks.
- **AndroidX**: Um conjunto de bibliotecas do Android para compatibilidade com versões mais antigas e novos recursos da plataforma.

## Dependências

### No arquivo `build.gradle.kts`, temos as seguintes dependências:

- **AndroidX Libraries**:
    - `androidx.appcompat`, `androidx.lifecycle`, `androidx.room`, `androidx.paging`, `androidx.navigation`, etc.
    - **ViewBinding** para facilitar a manipulação das views.
    - **SwipeRefreshLayout** para permitir a funcionalidade de "swipe to refresh".

- **Dagger Hilt**:
    - `dagger.hilt.android` e `dagger.hilt.compiler` para facilitar a injeção de dependências.

- **RxJava3**:
    - `rxjava3.rxjava` e `rxjava3.rxandroid` para gerenciamento de fluxo assíncrono.

- **Glide**:
    - `bumptech.glide.glide` para carregar imagens.

- **Retrofit e OkHttp**:
    - `retrofit2` e `okhttp3` para realizar chamadas HTTP e integrar com a API do GitHub.

- **Testes**:
    - `mockito-core`, `mockito-kotlin`, `junit` e outras dependências para realizar testes unitários.

## Como Rodar o Projeto

### Pré-requisitos

- Android Studio
- JDK 17 ou superior

### Passos

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    ```

2. Abra o projeto no Android Studio.

3. Sincronize o projeto com os arquivos Gradle.

4. Construa o projeto e execute no seu dispositivo ou emulador Android.

## Testes

O projeto inclui testes unitários para verificar a lógica de negócios e a integração com a API. As dependências de testes incluem:

- **JUnit**: Framework de testes unitários.
- **Mockito**: Para mockar objetos e realizar testes.
- **Core Testing**: Para testes específicos de componentes do Android, como `LiveData` e `Paging`.

### Como Executar os Testes

Para executar os testes unitários, basta rodar o comando no terminal do Android Studio ou via Gradle:

```bash
./gradlew test
```

## Contribuição

Se você deseja contribuir para este projeto, sinta-se à vontade para fazer um fork e enviar um pull request com melhorias ou correções de bugs.

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
