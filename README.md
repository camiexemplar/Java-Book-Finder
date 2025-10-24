# Integrantes
* Eduarda Weiss Ventura — RM: 564434
* Lucas Nunes Soares — RM: 566503
*	Camily Vitoria Pereira Maciel — RM: 566520

---

# Java Book Finder

Aplicação **Java** que **consome uma API pública de livros**, exibe os resultados no console e permite persistir um título selecionado em banco Oracle. O projeto segue arquitetura em camadas (MVC) para separar responsabilidades.

---

## Objetivo da solução

- **Buscar livros** por termo usando uma API pública.
- **Tratar e apresentar** os dados de forma organizada no console.
- **Salvar** um livro escolhido no banco de dados (Oracle).
- **Organização do código** em **Model–View–Controller**.

---

**Como as camadas conversam:**
1. `Main` recebe o termo digitado pelo usuário.  
2. `BookController` chama `BookService` para buscar na API.  
3. `BookService` retorna `List<Book>`.  
4. `BookView` apresenta os resultados numerados.  
5. Usuário escolhe um item.  
6. `BookController` chama `BookRepository` para inserir no Oracle.  
7. Console confirma o salvamento.

---

### Funcionamento da API — explicação simples

* A aplicação realiza uma requisição **HTTP GET** para `https://openlibrary.org/search.json?q={termo}`, onde `{termo}` é o texto digitado pelo usuário (com **URL encoding**, por exemplo `dom%20casmurro`). A **resposta** vem em **JSON** e contém, entre outros campos, o array `docs`, de onde o sistema extrai os metadados principais de cada obra: `title` (título), `author_name` (lista de autores, que é **concatenada** em uma única string) e `first_publish_year` (ano da primeira publicação). No código Java, o **HttpClient** executa a chamada e o **Gson** faz o *parse* para objetos do modelo **`Book`**; quando algum campo estiver ausente, aplicamos valores padrão como `"N/A"` para manter a exibição consistente no console. 
* A API suporta **paginação** pelo parâmetro `page` (1, 2, 3, …), mas o aplicativo exibe apenas os primeiros resultados para simplificar a navegação. Em caso de erro de rede ou **status HTTP** diferente de 200, o serviço trata a exceção e informa de forma clara que não foi possível obter os dados naquele momento. Em resumo: o usuário digita um termo, o serviço faz o GET na Open Library, normaliza `title`, `author_name` e `first_publish_year`, a *view* apresenta a lista no console e o usuário pode escolher um item para ser salvo no banco.

---
## **Persistência em Banco de Dados (Oracle)**

- **Driver**: `ojdbc8`
- **Credenciais** (ajustar em `BookRepository.java`):
```java
private final String url = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/orcl";
private final String user = "rm";
private final String password = "";

