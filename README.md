# repositoriesChalenge

Esse projeto possui chamada para api de repositorios do git com scroll infinito requisitando a api
por paginação. Também possui shimmer no loading, custom view para exibição de erro genérico e uma
Search View para pesquisar os repositorios pelo repo name.

Sempre que a api devolve respostas com sucesso, é salvo no shared preferences esse json, permitindo
o armazenamento dos dados em cache e a utilização do app sem internet.

O app também possui um float button que permite a alteração do layout da recyclew view entre grid e
lista.

O projeto foi pensado no clean architecture, com separação das responsabilidades:  
Presenter (View e ViewModel), Domain (Interactor e Mapper) e Data (Repository e Service)
A arquitetura definida foi a MVVM com injeção de dependencias com Koin