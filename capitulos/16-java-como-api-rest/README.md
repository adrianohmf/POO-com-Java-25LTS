[Índice](../../README.md) | [← Anterior: 15. Leitura e Escrita de Arquivos](../15-leitura-e-escrita-de-arquivos/README.md) | [Próximo: 17. Projeto Final — Aplicação Completa →](../17-projeto-final/README.md)

# 16. Java como API REST

> **Códigos deste capítulo:** [codigos/16-java-como-api-rest](../../codigos/16-java-como-api-rest/)

> **O que você vai aprender neste capítulo:**
> Entender como cliente e servidor conversam pela web (HTTP, rotas, métodos e status), criar um servidor Java com `HttpServer`, devolver e receber dados em JSON, construir uma API completa de livros com persistência em arquivo e consumi-la por uma página HTML com JavaScript.

Até aqui, todo programa que você escreveu roda e termina no seu computador, com o teclado e o terminal como interface. Os sistemas que você usa no dia a dia são diferentes: o aplicativo do banco, o portal acadêmico e o site da biblioteca são **telas** (frontend) que conversam com um **programa no servidor** (backend), pela internet.

Neste capítulo você vai transformar o seu conhecimento de Java em um backend. A ideia é simples: o programa Java fica "escutando" pedidos, executa a lógica que você já sabe escrever (classes, listas, arquivos, exceções) e devolve uma resposta. Quem faz o pedido pode ser uma página web, um aplicativo de celular ou outro programa.

Esse tipo de serviço se chama **API REST**, e é a base do desenvolvimento web moderno. Para aprender o essencial sem instalar nada além do JDK, usaremos apenas o servidor HTTP que já vem com o Java.

## 16.1 Como a web conversa: HTTP, rotas e JSON

### Cliente e servidor

* **Cliente:** quem faz o pedido (navegador, aplicativo, `curl`, outro programa).
* **Servidor:** quem recebe o pedido, processa e responde (o seu programa Java).

A conversa acontece pelo protocolo **HTTP**. Cada conversa tem sempre um pedido (**requisição**) e uma resposta.

```
Cliente                                     Servidor (Java)
   |  --- GET /livros  ------------------>      |
   |                                            |  busca os livros
   |  <--- 200 OK  [{"id":1,...}] ---------     |
```

### O que vai em uma requisição

| Parte | Exemplo | Para que serve |
|---|---|---|
| **URL** | `http://localhost:8080/livros/2` | Endereço do servidor, a **porta** e o caminho (**rota**) do recurso |
| **Método** | `GET`, `POST`, `DELETE` | O que o cliente quer fazer |
| **Cabeçalhos** | `Content-Type: application/json` | Informações sobre o pedido |
| **Corpo** | `{"titulo":"Dom Casmurro",...}` | Os dados enviados (em `POST`, por exemplo) |

A **porta** (aqui, `8080`) identifica qual programa do computador deve atender o pedido. `localhost` significa "este mesmo computador".

### Métodos HTTP e a ideia de REST

Em uma API REST, cada **recurso** (por exemplo, os livros) tem um endereço, e o **método** diz a ação:

| Método | Rota | Ação | Equivale a |
|---|---|---|---|
| `GET` | `/livros` | Listar todos os livros | consultar |
| `GET` | `/livros/2` | Buscar o livro de id 2 | consultar |
| `POST` | `/livros` | Cadastrar um livro novo | criar |
| `DELETE` | `/livros/2` | Remover o livro de id 2 | apagar |

### Códigos de status

A resposta sempre traz um **código de status** que resume o resultado:

| Código | Nome | Quando usar |
|---|---|---|
| `200` | OK | Deu certo e há conteúdo na resposta |
| `201` | Created | Um recurso novo foi criado |
| `204` | No Content | Deu certo e não há conteúdo (ex.: remoção) |
| `400` | Bad Request | O cliente enviou dados inválidos |
| `404` | Not Found | O recurso ou a rota não existe |
| `405` | Method Not Allowed | O método não é aceito nessa rota |
| `500` | Internal Server Error | Erro inesperado no servidor |

Os códigos que começam com `2` indicam sucesso, os que começam com `4` indicam erro do cliente e os que começam com `5` indicam erro do servidor.

### JSON

Para trocar dados, cliente e servidor precisam de um formato comum. O mais usado é o **JSON** (*JavaScript Object Notation*): texto simples com pares de nome e valor.

```json
{"id": 1, "titulo": "Dom Casmurro", "autor": "Machado de Assis", "ano": 1899}
```

Uma lista de objetos fica entre colchetes: `[ {...}, {...} ]`. Repare como o JSON se parece com um `record` Java: cada campo do record vira um par no JSON.

## 16.2 Seu primeiro servidor

O JDK inclui o pacote `com.sun.net.httpserver`, com a classe `HttpServer`. Ela basta para criar uma API simples. O programa abaixo responde um texto na rota `/ola`:

```java
// Arquivo: PrimeiroServidor.java

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

void main() throws IOException {
    // Cria o servidor na porta 8080 (0 = tamanho padrão da fila de espera)
    HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

    // Registra uma rota: o que fazer quando alguém acessar /ola
    servidor.createContext("/ola", troca -> {
        byte[] corpo = "Ola, API do IFPE!".getBytes(StandardCharsets.UTF_8);

        troca.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        troca.sendResponseHeaders(200, corpo.length);   // status 200 e tamanho da resposta
        troca.getResponseBody().write(corpo);           // corpo da resposta
        troca.close();
    });

    servidor.start();
    IO.println("Servidor no ar: http://localhost:8080/ola");
}
```

**Explicação do código:**

* `HttpServer.create(new InetSocketAddress(8080), 0)` cria o servidor na porta 8080.
* `createContext("/ola", ...)` registra uma **rota** e a ação a executar quando alguém a acessar. A ação é uma expressão lambda que recebe a **troca** (`HttpExchange`), objeto que representa o pedido e a resposta daquela conversa.
* `getBytes(StandardCharsets.UTF_8)` converte o texto em bytes. O tamanho enviado em `sendResponseHeaders` deve ser o dos **bytes**, e não o número de caracteres, pois letras acentuadas ocupam mais de um byte.
* `sendResponseHeaders(200, tamanho)` envia o status e os cabeçalhos. Só depois é possível escrever o corpo.
* `troca.close()` encerra a troca. Chame sempre.
* `servidor.start()` coloca o servidor para escutar. O programa **não termina**: ele continua rodando até você pará-lo (botão Stop do VS Code ou `Ctrl+C` no terminal).

Execute o programa e abra `http://localhost:8080/ola` no navegador. Você verá:

```
Ola, API do IFPE!
```

Para ver a resposta completa, com status e cabeçalhos, use o `curl` no terminal:

```
curl -i http://localhost:8080/ola
```

```
HTTP/1.1 200 OK
Content-type: text/plain; charset=UTF-8
Content-length: 17

Ola, API do IFPE!
```

> **Atenção:** só um programa pode usar a porta 8080 por vez. Se você executar de novo sem parar o anterior, aparece `java.net.BindException: Address already in use`. Pare o servidor antigo antes de iniciar outro.

## 16.3 Devolvendo JSON

Agora o servidor vai devolver dados de verdade. Retomamos o domínio da biblioteca. O `record Livro` ganha um identificador (`id`), necessário para que a API diga de qual livro está falando, e um método que o converte em JSON:

```java
// Arquivo: Livro.java

record Livro(int id, String titulo, String autor, int ano) {

    // Converte o livro em texto JSON: {"id":1,"titulo":"...","autor":"...","ano":1899}
    String paraJson() {
        return "{\"id\":" + id
                + ",\"titulo\":" + Json.texto(titulo)
                + ",\"autor\":" + Json.texto(autor)
                + ",\"ano\":" + ano + "}";
    }
}
```

A conversão fica em uma classe utilitária. Ela escapa as aspas dos textos e monta a lista no formato `[ {...}, {...} ]`:

```java
// Arquivo: Json.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Utilitário mínimo de JSON, só para fins didáticos.
// Em projetos reais use uma biblioteca como Jackson ou Gson.
class Json {

    // Coloca um texto entre aspas, escapando aspas e barras
    static String texto(String valor) {
        String escapado = valor
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
        return "\"" + escapado + "\"";
    }

    // Monta um array JSON a partir de uma lista de livros: [ {...}, {...} ]
    static String lista(List<Livro> livros) {
        var partes = new ArrayList<String>();
        for (Livro livro : livros) {
            partes.add(livro.paraJson());
        }
        return "[" + String.join(",", partes) + "]";
    }

    // Lê um objeto JSON simples (sem objetos aninhados) e devolve campo -> valor
    static Map<String, String> lerCampos(String json) {
        var campos = new HashMap<String, String>();
        Matcher m = Pattern
                .compile("\"(\\w+)\"\\s*:\\s*(\"((?:[^\"\\\\]|\\\\.)*)\"|-?\\d+)")
                .matcher(json);
        while (m.find()) {
            String valor = m.group(3) != null
                    ? m.group(3).replace("\\\"", "\"").replace("\\\\", "\\").replace("\\n", "\n")
                    : m.group(2);
            campos.put(m.group(1), valor);
        }
        return campos;
    }
}
```

> **Importante:** essa classe `Json` é **didática**, feita para você ver como o formato funciona por dentro. Em projetos reais, use uma biblioteca madura como **Jackson** ou **Gson**, que convertem objetos em JSON e o contrário automaticamente, cobrindo todos os casos (objetos aninhados, listas, datas, números decimais).

O servidor registra a rota `/livros` e responde com o JSON, informando o tipo do conteúdo em `Content-Type`:

```java
// Arquivo: ServidorJson.java

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

void main() throws IOException {
    List<Livro> livros = List.of(
            new Livro(1, "Dom Casmurro", "Machado de Assis", 1899),
            new Livro(2, "Vidas Secas", "Graciliano Ramos", 1938));

    HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

    servidor.createContext("/livros", troca -> {
        byte[] corpo = Json.lista(livros).getBytes(StandardCharsets.UTF_8);

        troca.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        troca.sendResponseHeaders(200, corpo.length);
        troca.getResponseBody().write(corpo);
        troca.close();
    });

    servidor.start();
    IO.println("Servidor no ar: http://localhost:8080/livros");
}
```

Ao acessar `http://localhost:8080/livros`, a resposta é:

```
[{"id":1,"titulo":"Dom Casmurro","autor":"Machado de Assis","ano":1899},{"id":2,"titulo":"Vidas Secas","autor":"Graciliano Ramos","ano":1938}]
```

O cabeçalho `Content-Type: application/json` avisa ao cliente que o corpo é JSON, e não texto comum.

## 16.4 A API completa: listar, buscar, cadastrar e remover

Vamos montar a API final da biblioteca, com as quatro operações da tabela da seção 16.1 e persistência em arquivo. O projeto `BibliotecaApi` tem esta estrutura:

```
BibliotecaApi/
├── BibliotecaApi.java        servidor: cria as rotas e inicia
├── LivroHandler.java         trata GET, POST e DELETE de /livros
├── Livro.java                record com paraJson()
├── Json.java                 utilitário de JSON
├── RepositorioLivros.java    salva e carrega os livros em CSV
└── web/
    └── index.html            frontend (seção 16.5)
```

Cada classe tem uma responsabilidade, como você viu nos capítulos de pacotes e de arquivos. O `RepositorioLivros` é o mesmo conceito do capítulo 15, agora com o `id` no arquivo:

```java
// Arquivo: RepositorioLivros.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// Guarda os livros em um arquivo CSV (mesma ideia do capítulo 15)
class RepositorioLivros {

    private final Path arquivo;

    RepositorioLivros(Path arquivo) {
        this.arquivo = arquivo;
    }

    List<Livro> carregar() throws IOException {
        var livros = new ArrayList<Livro>();
        if (!Files.exists(arquivo)) {
            return livros;
        }
        for (String linha : Files.readAllLines(arquivo)) {
            if (linha.isBlank()) continue;
            String[] p = linha.split(";");
            livros.add(new Livro(Integer.parseInt(p[0]), p[1], p[2], Integer.parseInt(p[3])));
        }
        return livros;
    }

    void salvar(List<Livro> livros) throws IOException {
        Files.createDirectories(arquivo.toAbsolutePath().getParent());
        var linhas = new ArrayList<String>();
        for (Livro l : livros) {
            // ponto e vírgula dentro do texto quebraria o CSV, por isso é trocado por vírgula
            linhas.add(l.id() + ";" + l.titulo().replace(';', ',') + ";"
                    + l.autor().replace(';', ',') + ";" + l.ano());
        }
        Files.write(arquivo, linhas);
    }
}
```

O coração da API é o `LivroHandler`. Ele implementa a interface `HttpHandler` (lembra das interfaces do capítulo 9?) e decide o que fazer conforme o método HTTP:

```java
// Arquivo: LivroHandler.java

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Trata todas as requisições para /livros e /livros/{id}
class LivroHandler implements HttpHandler {

    private final RepositorioLivros repositorio;
    private final List<Livro> livros;

    LivroHandler(RepositorioLivros repositorio) throws IOException {
        this.repositorio = repositorio;
        this.livros = new ArrayList<>(repositorio.carregar());
    }

    @Override
    public void handle(HttpExchange troca) throws IOException {
        try {
            String metodo = troca.getRequestMethod();
            String caminho = troca.getRequestURI().getPath();       // /livros ou /livros/3
            String id = caminho.length() > "/livros/".length()
                    ? caminho.substring("/livros/".length()) : null;

            switch (metodo) {
                case "GET"     -> listarOuBuscar(troca, id);
                case "POST"    -> cadastrar(troca);
                case "DELETE"  -> remover(troca, id);
                case "OPTIONS" -> responder(troca, 204, "");         // resposta ao navegador (CORS)
                default        -> responder(troca, 405, Json.erro("Metodo nao permitido"));
            }
        } catch (Exception e) {
            responder(troca, 500, Json.erro("Erro interno do servidor"));
        }
    }

    // GET /livros  ou  GET /livros/{id}
    private void listarOuBuscar(HttpExchange troca, String id) throws IOException {
        if (id == null) {
            responder(troca, 200, Json.lista(livros));
            return;
        }
        Livro livro = buscarPorId(id);
        if (livro == null) {
            responder(troca, 404, Json.erro("Livro nao encontrado"));
        } else {
            responder(troca, 200, livro.paraJson());
        }
    }

    // POST /livros   corpo: {"titulo":"...","autor":"...","ano":1899}
    private void cadastrar(HttpExchange troca) throws IOException {
        String corpo = new String(troca.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> campos = Json.lerCampos(corpo);

        String titulo = campos.getOrDefault("titulo", "").trim();
        String autor = campos.getOrDefault("autor", "").trim();
        int ano;
        try {
            ano = Integer.parseInt(campos.getOrDefault("ano", ""));
        } catch (NumberFormatException e) {
            responder(troca, 400, Json.erro("Informe um ano valido"));
            return;
        }
        if (titulo.isEmpty() || autor.isEmpty()) {
            responder(troca, 400, Json.erro("Titulo e autor sao obrigatorios"));
            return;
        }

        int novoId = livros.stream().mapToInt(Livro::id).max().orElse(0) + 1;
        Livro novo = new Livro(novoId, titulo, autor, ano);
        livros.add(novo);
        repositorio.salvar(livros);
        responder(troca, 201, novo.paraJson());                     // 201 = criado
    }

    // DELETE /livros/{id}
    private void remover(HttpExchange troca, String id) throws IOException {
        Livro livro = id == null ? null : buscarPorId(id);
        if (livro == null) {
            responder(troca, 404, Json.erro("Livro nao encontrado"));
            return;
        }
        livros.remove(livro);
        repositorio.salvar(livros);
        responder(troca, 204, "");                                  // 204 = sem conteúdo
    }

    private Livro buscarPorId(String id) {
        try {
            int numero = Integer.parseInt(id);
            for (Livro l : livros) {
                if (l.id() == numero) return l;
            }
        } catch (NumberFormatException e) {
            // id que não é número: tratado como não encontrado
        }
        return null;
    }

    // Envia a resposta com status, cabeçalhos e corpo
    static void responder(HttpExchange troca, int status, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        var cabecalhos = troca.getResponseHeaders();
        cabecalhos.set("Content-Type", "application/json; charset=UTF-8");
        cabecalhos.set("Access-Control-Allow-Origin", "*");         // libera frontend em outra origem
        cabecalhos.set("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
        cabecalhos.set("Access-Control-Allow-Headers", "Content-Type");
        // 204 não tem corpo: o tamanho é -1
        troca.sendResponseHeaders(status, status == 204 ? -1 : bytes.length);
        if (status != 204) {
            troca.getResponseBody().write(bytes);
        }
        troca.close();
    }
}
```

**Pontos importantes do código:**

* **`switch` por método:** cada método HTTP chama um método Java diferente, usando o `switch` com seta que você viu no capítulo 1.
* **Validação:** o `cadastrar` confere se `titulo` e `autor` foram enviados e se `ano` é um número. Se não forem, responde `400` com uma mensagem clara em vez de deixar o programa quebrar. É o tratamento de exceções (capítulo 12) a serviço do cliente da API.
* **Status certos:** `201` ao criar, `204` ao remover, `404` quando o id não existe e `405` para métodos não previstos.
* **Persistência:** a cada cadastro ou remoção, a lista inteira é gravada no arquivo `dados/livros.csv`. Ao reiniciar o servidor, os dados voltam.
* **`catch (Exception e)` no topo:** qualquer erro inesperado vira uma resposta `500`, e o servidor continua de pé.
* **`id` novo:** o próximo id é o maior existente mais um, calculado com `stream().mapToInt(...).max()`.
* **Cabeçalhos CORS:** o método `responder` envia `Access-Control-Allow-*`. Eles só importam quando a página web está em outro endereço (veja a seção 16.5).

Por fim, a classe principal liga tudo: cria o repositório, registra a rota da API e a rota da página inicial.

```java
// Arquivo: BibliotecaApi.java

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

void main() throws IOException {
    var repositorio = new RepositorioLivros(Path.of("dados", "livros.csv"));
    HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

    // API: tudo que começa com /livros é tratado pelo LivroHandler
    servidor.createContext("/livros", new LivroHandler(repositorio));

    // Frontend: a página web/index.html é servida na raiz
    servidor.createContext("/", troca -> {
        Path pagina = Path.of("web", "index.html");
        if (!troca.getRequestURI().getPath().equals("/") || !Files.exists(pagina)) {
            LivroHandler.responder(troca, 404, Json.erro("Recurso nao encontrado"));
            return;
        }
        byte[] html = Files.readAllBytes(pagina);
        troca.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        troca.sendResponseHeaders(200, html.length);
        troca.getResponseBody().write(html);
        troca.close();
    });

    servidor.start();
    IO.println("Biblioteca no ar: http://localhost:8080");
}
```

### Compilando e executando

Dentro da pasta `BibliotecaApi` (é importante estar nela, pois os caminhos `dados` e `web` são relativos à pasta de execução, como vimos no capítulo 15):

```
javac -d out *.java
java -cp out BibliotecaApi
```

No Windows PowerShell, os mesmos comandos funcionam. Ou, no VS Code, clique em **Run** acima do `void main()`.

## 16.5 O frontend: uma página que consome a API

A API só devolve dados. Quem os apresenta é o **frontend**. Aqui ele é uma página HTML com um pouco de JavaScript, servida pelo próprio servidor Java na rota `/`. Acesse `http://localhost:8080` no navegador após iniciar o `BibliotecaApi`.

A página tem um formulário para cadastrar livros e uma lista com botão de remover. O trecho que importa é o JavaScript, que usa a função `fetch` para chamar a API. O arquivo completo está em [`web/index.html`](../../codigos/16-java-como-api-rest/16.4-api-completa-com-frontend/BibliotecaApi/web/index.html). Veja as três chamadas, uma para cada método HTTP:

```javascript
// Listar: GET /livros
const resposta = await fetch("/livros");
const livros = await resposta.json();          // converte o JSON em objetos JavaScript

// Cadastrar: POST /livros, com o livro no corpo
await fetch("/livros", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ titulo: "Dom Casmurro", autor: "Machado de Assis", ano: 1899 })
});

// Remover: DELETE /livros/2
await fetch("/livros/2", { method: "DELETE" });
```

**Explicação:**

* `fetch(url, opções)` faz a requisição HTTP e devolve uma promessa. Com `await`, o código espera a resposta antes de continuar.
* `resposta.json()` lê o corpo e converte o JSON em objetos JavaScript.
* `JSON.stringify(objeto)` faz o caminho inverso, transformando o objeto em texto JSON para enviar.
* `resposta.status` (ou `resposta.ok`) permite saber se deu certo. A página usa isso para mostrar a mensagem de erro que a API devolve em um `400`.
* A URL é relativa (`/livros`), pois a página e a API estão no **mesmo servidor**.

> **Segurança:** a página usa `textContent` para mostrar os títulos, e não `innerHTML`. Assim, se alguém cadastrar um título como `<script>...</script>`, ele aparece como texto e não é executado. Nunca insira dados recebidos de uma API como HTML sem tratamento.

### E se o frontend estiver em outro lugar?

Muitas vezes a página fica em outro endereço, por exemplo `http://localhost:5500` (uma extensão como o Live Server do VS Code) ou um projeto React em outra porta. Nesse caso, o navegador aplica uma regra de segurança chamada **CORS** (*Cross-Origin Resource Sharing*): ele só deixa a página ler a resposta de outro endereço se o servidor autorizar, com o cabeçalho `Access-Control-Allow-Origin`.

Por isso o método `responder` envia esses cabeçalhos e o `LivroHandler` responde ao método `OPTIONS`, uma consulta prévia (*preflight*) que o navegador faz antes de um `POST` com JSON. Com isso, a mesma API funciona com um frontend hospedado em qualquer origem. No exemplo usamos `*` (qualquer origem) por simplicidade; em produção, libere apenas os endereços do seu frontend.

## 16.6 Testando a API

Antes de ter frontend, e sempre que algo der errado, teste a API diretamente. Com o servidor rodando, em **outro terminal**:

**Linux e macOS** (ou o Git Bash no Windows)

```
curl -i http://localhost:8080/livros

curl -i -X POST http://localhost:8080/livros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Dom Casmurro","autor":"Machado de Assis","ano":1899}'

curl -i http://localhost:8080/livros/1

curl -i -X DELETE http://localhost:8080/livros/1
```

**Windows (PowerShell)**

```
Invoke-RestMethod http://localhost:8080/livros

Invoke-RestMethod -Method Post -Uri http://localhost:8080/livros `
  -ContentType "application/json" `
  -Body '{"titulo":"Dom Casmurro","autor":"Machado de Assis","ano":1899}'

Invoke-RestMethod -Method Delete -Uri http://localhost:8080/livros/1
```

O PowerShell tem um apelido `curl` que aponta para outro comando, por isso usamos `Invoke-RestMethod`.

Respostas esperadas no primeiro uso:

| Chamada | Status | Corpo |
|---|---|---|
| `GET /livros` (lista vazia) | `200` | `[]` |
| `POST /livros` com dados válidos | `201` | `{"id":1,"titulo":"Dom Casmurro","autor":"Machado de Assis","ano":1899}` |
| `POST /livros` com `"ano":"abc"` | `400` | `{"erro":"Informe um ano valido"}` |
| `POST /livros` sem `titulo` | `400` | `{"erro":"Titulo e autor sao obrigatorios"}` |
| `GET /livros/1` | `200` | o livro de id 1 |
| `GET /livros/99` | `404` | `{"erro":"Livro nao encontrado"}` |
| `DELETE /livros/1` | `204` | (vazio) |
| `PUT /livros` | `405` | `{"erro":"Metodo nao permitido"}` |

Depois de cadastrar alguns livros, abra o arquivo `dados/livros.csv`. Ele é o "banco de dados" dessa API:

```
2;Vidas Secas;Graciliano Ramos;1938
3;O "Quinze";Rachel de Queiroz;1930
```

Pare o servidor, inicie de novo e consulte `GET /livros`: os livros continuam lá.

> **Dica:** no navegador, a tecla `F12` abre as ferramentas do desenvolvedor. A aba **Rede** (*Network*) mostra cada chamada que a página faz, com método, status e corpo. A aba **Console** mostra erros de JavaScript e de CORS.

## 16.7 Limites desta abordagem e próximos passos

O que você construiu é real e funciona, mas é propositalmente simples. Em um projeto profissional, você encontraria:

| Neste capítulo | Em projetos reais |
|---|---|
| `HttpServer` do JDK | **Spring Boot** (o mais usado no mercado) ou **Javalin**, que cuidam de rotas, JSON e validação com muito menos código |
| Classe `Json` didática | **Jackson** ou **Gson** para converter objetos e JSON automaticamente |
| Arquivo CSV | **Banco de dados** (PostgreSQL, MySQL) acessado com JDBC ou JPA |
| Um único arquivo com todos os livros regravado a cada alteração | Operações pontuais no banco, com transações |
| Sem login | **Autenticação** (por exemplo, tokens JWT) e controle de quem pode fazer o quê |
| Execução no seu computador | **Deploy** em um servidor ou na nuvem |

Mesmo assim, as ideias deste capítulo (rotas, métodos HTTP, status, JSON, separação entre frontend e backend) são exatamente as mesmas dessas ferramentas. Quem entende o que o `LivroHandler` faz aprende Spring Boot muito mais rápido, porque reconhece o que o framework automatiza.

## Erros comuns: Tema 16

```java
// ERRO 1: porta já está em uso (outro servidor ou o mesmo programa rodando de novo)
HttpServer.create(new InetSocketAddress(8080), 0);
// Mensagem: java.net.BindException: Address already in use
// CORRETO: encerre o programa anterior (botão Stop no VS Code) ou troque a porta

// ERRO 2: acessar a API com o servidor desligado
// curl: (7) Failed to connect to localhost port 8080
// No navegador: ERR_CONNECTION_REFUSED
// CORRETO: execute o programa e espere a mensagem "no ar" antes de testar

// ERRO 3: esquecer de fechar a troca (exchange)
servidor.createContext("/ola", troca -> {
    troca.sendResponseHeaders(200, corpo.length);
    troca.getResponseBody().write(corpo);
    // faltou troca.close(): a conexão fica aberta e recursos do servidor ficam presos
});
// CORRETO: chame troca.close() ao final de todos os caminhos do método

// ERRO 4: tamanho informado diferente do corpo enviado
byte[] corpo = "Olá".getBytes(StandardCharsets.UTF_8);       // 4 bytes (o á ocupa 2)
troca.sendResponseHeaders(200, "Olá".length());              // ERRADO: 3 caracteres, mas 4 bytes
// Resultado: a resposta chega vazia ou cortada
// CORRETO: use corpo.length, o tamanho em bytes

// ERRO 5: acentos quebrados na resposta
troca.getResponseHeaders().set("Content-Type", "text/plain");   // sem charset
// O navegador pode mostrar "OlÃ¡". CORRETO: "text/plain; charset=UTF-8"

// ERRO 6: não validar o corpo do POST
int ano = Integer.parseInt(campos.get("ano"));
// Se o campo faltar ou não for número: NumberFormatException e erro 500
// CORRETO: validar, capturar a exceção e responder 400 com uma mensagem clara

// ERRO 7: responder 200 para tudo
// Use o status que descreve o resultado: 201 criado, 204 sem conteúdo,
// 400 dados inválidos, 404 não encontrado, 405 método não permitido, 500 erro interno

// ERRO 8: frontend em outra origem sem liberar CORS
// (página em http://localhost:5500 chamando http://localhost:8080)
// Console do navegador: Access to fetch at 'http://localhost:8080/livros' from origin
// 'http://localhost:5500' has been blocked by CORS policy
// CORRETO: servir a página pelo próprio servidor Java, ou enviar o cabeçalho
// Access-Control-Allow-Origin na resposta (e responder ao método OPTIONS)

// ERRO 9: abrir o index.html com duplo clique (file://)
// Com fetch("/livros") a página não encontra a API. Abra http://localhost:8080 no navegador.

// ERRO 10: guardar dados só na memória
// Ao reiniciar o servidor a lista volta vazia. Persista em arquivo (capítulo 15) ou banco de dados.
```

## Exercícios: Tema 16

**Exercício 16.1:** Execute o `PrimeiroServidor` e acrescente uma segunda rota, `/hora`, que responde a data e a hora atuais (use `LocalDateTime.now()` do capítulo 14, formatada em `dd/MM/yyyy HH:mm`).

**Exercício 16.2:** No `ServidorJson`, crie a rota `/alunos` que devolve uma lista de três alunos em JSON. Crie um `record Aluno(int id, String nome, double nota)` com o método `paraJson()`.

**Exercício 16.3:** Na `BibliotecaApi`, acrescente a rota `GET /livros?autor=Machado` (ou `/livros/busca/Machado`) que devolve apenas os livros do autor informado. Dica: use `troca.getRequestURI().getQuery()` para ler o parâmetro.

**Exercício 16.4:** Acrescente a operação `PUT /livros/{id}`, que atualiza título, autor e ano de um livro existente. Responda `200` com o livro atualizado, ou `404` se o id não existir. Atualize também o frontend com um botão "Editar".

**Exercício 16.5:** Crie uma validação extra no cadastro: o ano deve estar entre 1000 e o ano atual (use `LocalDate.now().getYear()`), e não pode haver dois livros com o mesmo título e autor. Responda `400` ou `409` (Conflict) com mensagens claras.

**Exercício 16.6:** Faça a página mostrar a mensagem de erro da API quando o servidor estiver desligado. Dica: `fetch` lança uma exceção nesse caso; trate com `try` e `catch` em JavaScript.

**Exercício 16.7 (Projeto progressivo):** Crie uma API para o sistema de `Artesao` com as rotas `GET /artesaos`, `POST /artesaos` e `DELETE /artesaos/{id}`, guardando os dados em CSV. Monte uma página HTML com o cadastro e a lista.

**Exercício 16.8 (Desafio):** Substitua a classe `Json` por **Gson** ou **Jackson**. Pesquise como adicionar a biblioteca ao projeto (por exemplo, com Maven) e compare a quantidade de código antes e depois.

---

[Índice](../../README.md) | [← Anterior: 15. Leitura e Escrita de Arquivos](../15-leitura-e-escrita-de-arquivos/README.md) | [Próximo: 17. Projeto Final — Aplicação Completa →](../17-projeto-final/README.md)
