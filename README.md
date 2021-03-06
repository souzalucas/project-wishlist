# wishlist-microsservice

Serviço HTTP em Java Spring Boot para manipular uma Wishlist.

### Especificações

- **Linguagem de programação:** Java 17
- **Framework HTTP:** Spring Boot
- **Gerenciador de  dependências:** Maven
- **Banco de dados:** MongoDB

### Executando os testes
1. No terminal, execute `git clone https://github.com/souzalucas/project-wishlist.git`
2. Carregue o projeto no [Spring Tool Suite](https://spring.io/tools)
3. Crie um banco de dados MongoDB com o nome de "store"
4. Em `src/main/resources/application.properties` adicione as configurações do MongoDB
	```
	spring.data.mongodb.uri=<uri_de_conexao_mongodb> 
	spring.data.mongodb.database=store
	```
5. Abra o arquivo `src/test/java/resources/features/wishlist.feature`, clique com o botão direito e vá até `Run As >> Cucumber Feature`

### Dependências
- spring-boot-starter-data-mongodb
- spring-boot-starter-web
- spring-boot-devtools
- spring-boot-starter-test
- cucumber-java
- cucumber-junit
- cucumber-spring
- rest-assured
- hamcrest-all
