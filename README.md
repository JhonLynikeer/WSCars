# WSCars
Desafio basico de mobile

Tecnologias Utilizadas
Linguagem de Programação: Kotlin
Escolhi Kotlin pela sua sintaxe concisa e expressiva, interoperabilidade total com Java e suporte oficial
do Android. Kotlin facilita a escrita de código seguro e robusto, reduzindo a quantidade de
código boilerplate.

Banco de Dados: SQLite
Para a persistência de dados locais, utilizei SQLite, uma escolha comum para aplicações Android
devido à sua leveza e robustez. SQLite permite o armazenamento estruturado de dados com consultas
eficientes utilizando SQL.

Injeção de Dependência: Koin
Optei por Koin para injeção de dependência devido à sua simplicidade e integração perfeita com Kotlin.
Koin é uma alternativa leve e de fácil configuração ao Dagger, permitindo um desenvolvimento mais rápido
e com menos boilerplate.

Persistência de Dados
Para a persistência de dados, além do SQLite, utilizei o padrão Repository para abstrair as fontes de dados,
facilitando a manutenção e a escalabilidade do código. A persistência local é gerenciada pelo SQLite,
enquanto a sincronização de dados remotos é feita via Retrofit.

Comunicação com API: Retrofit
Escolhi Retrofit para a comunicação com APIs RESTful devido à sua simplicidade de uso e
capacidade de converter automaticamente as respostas JSON em objetos Kotlin.
Retrofit facilita a criação de chamadas de rede de maneira eficiente e organizada.

Arquitetura: MVVM (Model-View-ViewModel)
Adotei a arquitetura MVVM (Model-View-ViewModel) para separar claramente as responsabilidades da aplicação,
promovendo um código mais limpo e testável. No MVVM:

Model: Responsável pela lógica de negócios e manipulação de dados
(incluindo fontes de dados locais e remotas).
View: Responsável pela interface do usuário e pela apresentação dos dados.
ViewModel: Atua como intermediário entre Model e View, gerenciando os dados que serão exibidos pela View e lidando com a lógica da interface do usuário.
Gerenciamento de Ciclo de Vida
Utilizei componentes do Android Jetpack, como LiveData e ViewModel, para gerenciar o ciclo de vida das atividades e fragments, garantindo que os dados sobrevivam a mudanças de configuração, como rotações de tela.
