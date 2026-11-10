[Índice](../../README.md) | [← Anterior: 14. Datas com java.time](../14-datas-com-java-time/README.md) | [Próximo: 16. Java como API REST →](../16-java-como-api-rest/README.md)

# 15. Leitura e Escrita de Arquivos

> **Códigos deste capítulo:** [codigos/15-leitura-e-escrita-de-arquivos](../../codigos/15-leitura-e-escrita-de-arquivos/)

> **O que você vai aprender neste capítulo:**
> Gravar e ler arquivos de texto com a API `java.nio.file` (`Path` e `Files`), usar `try-with-resources` para fechar arquivos com segurança, tratar `IOException` e guardar os objetos do programa em um arquivo CSV para que os dados sobrevivam depois que o programa terminar.

Todos os programas que você escreveu até aqui têm uma limitação: quando o programa termina, **os dados desaparecem**. A lista de livros, as notas dos alunos, os empréstimos, tudo some da memória. Para que os dados permaneçam, é preciso guardá-los em um **arquivo**.

Esse é o primeiro passo de um conceito chamado **persistência**. Mais adiante, você vai conhecer bancos de dados, que são a forma profissional de persistir informação. Mas arquivos de texto são simples, funcionam em qualquer sistema, podem ser abertos no Bloco de Notas ou no Excel e ensinam a ideia central: **converter objetos em texto para gravar, e texto em objetos para ler**.

## 15.1 Path e Files

A API moderna de arquivos fica no pacote `java.nio.file` e tem duas peças principais:

| Classe | Papel | Exemplo |
|---|---|---|
| `Path` | Representa o **caminho** de um arquivo ou pasta | `Path.of("saida", "notas.txt")` |
| `Files` | Reúne os **métodos** para criar, gravar, ler, copiar e apagar | `Files.readString(caminho)` |

Alguns métodos úteis de `Files`:

| Método | O que faz |
|---|---|
| `Files.exists(caminho)` | Informa se o arquivo ou pasta existe |
| `Files.createDirectories(pasta)` | Cria a pasta (e as intermediárias) se ainda não existir |
| `Files.writeString(caminho, texto)` | Grava um texto inteiro |
| `Files.write(caminho, lista)` | Grava uma linha para cada elemento da lista |
| `Files.readString(caminho)` | Lê o arquivo todo em uma `String` |
| `Files.readAllLines(caminho)` | Lê o arquivo em uma `List<String>`, uma linha por elemento |
| `Files.size(caminho)` | Tamanho em bytes |

### Caminhos relativos e absolutos

* **Caminho absoluto:** começa na raiz do sistema (`C:\Users\ana\notas.txt` ou `/home/ana/notas.txt`).
* **Caminho relativo:** parte da **pasta onde o programa foi executado** (`saida/notas.txt`). É o mais usado em projetos, pois funciona em qualquer computador.

Use `Path.of("pasta", "arquivo.txt")` em vez de escrever a barra à mão. Assim o Java usa o separador correto para cada sistema operacional.

## 15.2 Escrevendo arquivos

O programa abaixo cria a pasta `saida`, grava o primeiro texto, acrescenta mais linhas e confere o resultado:

```java
// Arquivo: EscreverArquivo.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

void main() throws IOException {
    Path pasta = Path.of("saida");
    Path arquivo = pasta.resolve("notas.txt");        // saida/notas.txt

    Files.createDirectories(pasta);                   // cria a pasta se ela não existir

    // writeString: grava um texto inteiro (substitui o conteúdo anterior)
    Files.writeString(arquivo, "Maria;8.5\n");

    // write com lista: uma linha por elemento
    Files.write(arquivo, List.of("Joao;7.0", "Ana;9.5"), StandardOpenOption.APPEND);

    // APPEND acrescenta ao final, sem apagar o que já existe
    Files.writeString(arquivo, "Pedro;6.0\n", StandardOpenOption.APPEND);

    IO.println("Arquivo criado em: " + arquivo.toAbsolutePath().getFileName());
    IO.println("Existe? " + Files.exists(arquivo));
    IO.println("Tamanho: " + Files.size(arquivo) + " bytes");
}
```

Saída esperada:

```
Arquivo criado em: notas.txt
Existe? true
Tamanho: 37 bytes
```

O arquivo `saida/notas.txt` fica com este conteúdo:

```
Maria;8.5
Joao;7.0
Ana;9.5
Pedro;6.0
```

Três pontos importantes:

* `Files.writeString` **substitui** o conteúdo anterior. Para **acrescentar** ao final, use `StandardOpenOption.APPEND`.
* `Files.write(arquivo, lista)` coloca a quebra de linha entre os elementos. Com `writeString` você mesmo precisa escrever o `\n`.
* A pasta precisa existir antes de gravar. Por isso o `createDirectories`.

### Por que `throws IOException`?

Mexer com arquivos pode falhar por motivos fora do controle do programa: disco cheio, permissão negada, arquivo apagado. Por isso os métodos de `Files` declaram que podem lançar `IOException`, uma **exceção verificada** (capítulo 12). O compilador exige que você faça uma de duas coisas:

```java
void main() throws IOException { ... }      // 1. repassar o erro para quem chamou

try { ... } catch (IOException e) { ... }   // 2. tratar o erro ali mesmo
```

## 15.3 Lendo arquivos

Para ler, você escolhe entre ler tudo de uma vez (`readString`) ou obter as linhas separadas (`readAllLines`). Cada linha pode ser dividida com `split`, e o texto de um número é convertido com `Double.parseDouble` ou `Integer.parseInt`.

```java
// Arquivo: LerArquivo.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

void main() throws IOException {
    Path arquivo = Path.of("saida", "notas.txt");

    // Prepara um arquivo de exemplo para o programa funcionar sozinho
    Files.createDirectories(arquivo.getParent());
    Files.write(arquivo, List.of("Maria;8.5", "Joao;7.0", "Ana;9.5", "Pedro;6.0"));

    // readString: o arquivo inteiro em um único texto
    String tudo = Files.readString(arquivo);
    IO.println("Caracteres lidos: " + tudo.length());

    // readAllLines: uma lista com uma String por linha
    List<String> linhas = Files.readAllLines(arquivo);
    double soma = 0;
    for (String linha : linhas) {
        String[] partes = linha.split(";");           // separa nome e nota
        String nome = partes[0];
        double nota = Double.parseDouble(partes[1]);  // texto -> número
        IO.println(nome + " tirou " + nota);
        soma += nota;
    }
    IO.println("Media: " + soma / linhas.size());

    // Arquivo que não existe lança NoSuchFileException
    try {
        Files.readAllLines(Path.of("saida", "nao-existe.txt"));
    } catch (NoSuchFileException e) {
        IO.println("Arquivo nao encontrado: " + e.getMessage());
    }
}
```

Saída esperada:

```
Caracteres lidos: 37
Maria tirou 8.5
Joao tirou 7.0
Ana tirou 9.5
Pedro tirou 6.0
Media: 7.75
Arquivo nao encontrado: saida/nao-existe.txt
```

O formato `nome;nota` usado no exemplo é chamado de **CSV** (*comma-separated values*, valores separados por vírgula). Aqui usamos ponto e vírgula, que é o padrão do Excel em português do Brasil e evita confusão com a vírgula decimal. Arquivos assim podem ser abertos direto em planilhas.

## 15.4 Leitura segura com try-with-resources

`readAllLines` carrega o arquivo inteiro na memória, o que é ótimo para arquivos pequenos. Para arquivos grandes, ou quando você quer controlar a leitura linha a linha, use `BufferedReader` e `BufferedWriter`.

Esses objetos mantêm o arquivo **aberto** e precisam ser **fechados** ao final. Esquecer de fechar pode deixar o arquivo preso ou perder parte do texto gravado. O Java oferece o **try-with-resources**: o recurso declarado entre parênteses depois do `try` é fechado **automaticamente**, mesmo se ocorrer um erro.

```java
// Arquivo: LeituraSegura.java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

void main() {
    Path log = Path.of("log.txt");

    // try-with-resources: o arquivo é fechado automaticamente ao sair do bloco
    try (BufferedWriter escritor = Files.newBufferedWriter(log)) {
        escritor.write("Sistema iniciado");
        escritor.newLine();
        escritor.write("Usuario conectado");
        escritor.newLine();
    } catch (IOException e) {
        IO.println("Erro ao gravar: " + e.getMessage());
        return;
    }

    // Lendo linha a linha, sem carregar o arquivo todo na memória
    try (BufferedReader leitor = Files.newBufferedReader(log)) {
        String linha;
        int numero = 1;
        while ((linha = leitor.readLine()) != null) {   // null indica o fim do arquivo
            IO.println(numero + ": " + linha);
            numero++;
        }
    } catch (IOException e) {
        IO.println("Erro ao ler: " + e.getMessage());
    }
}
```

Saída esperada:

```
1: Sistema iniciado
2: Usuario conectado
```

Repare no laço de leitura: `readLine()` devolve `null` quando o arquivo termina, e é isso que encerra o `while`.

> **Dica:** sempre que abrir um arquivo com `newBufferedReader` ou `newBufferedWriter`, use `try-with-resources`. Você não precisa escrever `close()` nem `finally`.

## 15.5 Persistindo objetos em CSV

Agora juntamos tudo. Vamos fazer o catálogo da biblioteca **lembrar** os livros entre uma execução e outra. A ideia é dividir as responsabilidades em três classes:

| Classe | Responsabilidade |
|---|---|
| `Livro` | Guarda os dados e sabe se converter para uma linha CSV e voltar (`paraCsv` e `deCsv`) |
| `RepositorioLivros` | Sabe **onde** e **como** salvar e carregar a lista de livros |
| `AgendaDeLivros` | Programa principal: carrega, acrescenta um livro e salva |

Essa separação é a mesma que você viu nos pacotes (capítulo 13): o `main` não precisa saber nada sobre arquivos.

```java
// Arquivo: Livro.java

record Livro(String titulo, String autor, int ano) {

    // Objeto -> linha de texto no formato CSV
    String paraCsv() {
        return titulo + ";" + autor + ";" + ano;
    }

    // Linha de texto -> objeto
    static Livro deCsv(String linha) {
        String[] partes = linha.split(";");
        return new Livro(partes[0], partes[1], Integer.parseInt(partes[2]));
    }
}
```

```java
// Arquivo: RepositorioLivros.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class RepositorioLivros {

    private final Path arquivo;

    RepositorioLivros(Path arquivo) {
        this.arquivo = arquivo;
    }

    void salvar(List<Livro> livros) throws IOException {
        Files.createDirectories(arquivo.toAbsolutePath().getParent());
        List<String> linhas = new ArrayList<>();
        for (Livro livro : livros) {
            linhas.add(livro.paraCsv());
        }
        Files.write(arquivo, linhas);
    }

    List<Livro> carregar() throws IOException {
        List<Livro> livros = new ArrayList<>();
        if (!Files.exists(arquivo)) {
            return livros;                       // primeira execução: ainda não há dados
        }
        for (String linha : Files.readAllLines(arquivo)) {
            if (!linha.isBlank()) {
                livros.add(Livro.deCsv(linha));
            }
        }
        return livros;
    }
}
```

```java
// Arquivo: AgendaDeLivros.java

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

void main() throws IOException {
    var repositorio = new RepositorioLivros(Path.of("dados", "livros.csv"));

    List<Livro> livros = repositorio.carregar();
    IO.println("Livros carregados do arquivo: " + livros.size());

    // A cada execução acrescenta um livro novo, e o arquivo guarda o histórico
    livros.add(new Livro("Livro numero " + (livros.size() + 1), "Autor", 2000 + livros.size()));
    repositorio.salvar(livros);

    for (Livro livro : livros) {
        IO.println("  - " + livro.titulo() + " (" + livro.ano() + ")");
    }
    IO.println("Total salvo: " + livros.size());
}
```

Execute o programa **três vezes seguidas**. A cada execução ele encontra os livros gravados antes e acrescenta um novo:

```
Livros carregados do arquivo: 0
  - Livro numero 1 (2000)
Total salvo: 1
```

```
Livros carregados do arquivo: 1
  - Livro numero 1 (2000)
  - Livro numero 2 (2001)
Total salvo: 2
```

```
Livros carregados do arquivo: 2
  - Livro numero 1 (2000)
  - Livro numero 2 (2001)
  - Livro numero 3 (2002)
Total salvo: 3
```

O arquivo `dados/livros.csv` fica assim, e pode ser aberto no Excel:

```
Livro numero 1;Autor;2000
Livro numero 2;Autor;2001
Livro numero 3;Autor;2002
```

Detalhes de projeto que valem a pena notar:

* Na primeira execução o arquivo ainda não existe. O método `carregar()` verifica isso e devolve uma lista vazia em vez de lançar `NoSuchFileException`.
* Linhas em branco são ignoradas ao carregar.
* O título do livro não pode conter `;`, senão a linha seria dividida no lugar errado. Em sistemas reais isso é tratado com bibliotecas de CSV ou JSON, e é um bom motivo para a próxima etapa de estudo: bancos de dados e formatos como JSON.

## 15.6 Codificação de caracteres (UTF-8)

Os métodos `Files.readString`, `writeString`, `readAllLines`, `newBufferedReader` e `newBufferedWriter` usam **UTF-8** por padrão. Esse é o formato que suporta acentos e caracteres especiais (`ç`, `ã`, `é`) em qualquer sistema. Por isso você pode gravar `"Graciliano Ramos, Vidas Secas, São Bernardo"` sem se preocupar.

Se um arquivo vier de outro programa com acentos "quebrados" (`SÃ£o` em vez de `São`), é sinal de que ele foi salvo em outra codificação. Abra o arquivo no editor e salve novamente como UTF-8.

## Erros comuns: Tema 15

```java
// ERRO 1: ler um arquivo que não existe
Files.readAllLines(Path.of("saida", "nao-existe.txt"));
// Mensagem: java.nio.file.NoSuchFileException: saida/nao-existe.txt
// CORRETO: verifique com Files.exists(caminho) ou trate a exceção com try-catch

// ERRO 2: esquecer de tratar a IOException
void main() {
    String texto = Files.readString(Path.of("a.txt"));
}
// Mensagem: unreported exception IOException; must be caught or declared to be thrown
// CORRETO: void main() throws IOException   ou   envolver em try-catch

// ERRO 3: gravar em uma pasta que não existe
Files.writeString(Path.of("saida", "notas.txt"), "Maria;8.5");
// Mensagem: java.nio.file.NoSuchFileException: saida/notas.txt
// CORRETO: Files.createDirectories(Path.of("saida")); antes de gravar

// ERRO 4: apagar o conteúdo ao querer acrescentar
Files.writeString(arquivo, "novo texto");                                    // substitui tudo
Files.writeString(arquivo, "novo texto", StandardOpenOption.APPEND);         // acrescenta

// ERRO 5: esquecer o \n (ou newLine) e juntar tudo em uma linha só
Files.writeString(arquivo, "Maria;8.5");
Files.writeString(arquivo, "Joao;7.0", StandardOpenOption.APPEND);
// O arquivo fica com: Maria;8.5Joao;7.0
// CORRETO: "Maria;8.5\n"  ou  usar Files.write(arquivo, List.of(...))

// ERRO 6: não fechar o arquivo
BufferedWriter w = Files.newBufferedWriter(Path.of("log.txt"));
w.write("texto");
// Sem close(), parte do texto pode nem chegar ao arquivo
// CORRETO: try (BufferedWriter w = Files.newBufferedWriter(...)) { ... }

// ERRO 7: linha mal formatada ao ler CSV
String[] partes = "Maria".split(";");
Integer.parseInt(partes[1]);
// Mensagem: ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
// CORRETO: confira partes.length antes de usar os índices

// ERRO 8: caminho relativo apontando para o lugar errado
// Path.of("dados/livros.csv") é relativo à pasta onde o programa foi EXECUTADO,
// não à pasta do arquivo .java. Use Path.of("x").toAbsolutePath() para conferir.
```

## Exercícios: Tema 15

**Exercício 15.1:** Escreva um programa que grave em `saida/nomes.txt` os nomes de cinco colegas, um por linha, e depois leia o arquivo e mostre cada nome em letras maiúsculas.

**Exercício 15.2:** Crie um programa que leia `saida/notas.txt` (formato `nome;nota`) e mostre o aluno com a maior nota e a média da turma.

**Exercício 15.3:** Faça um registro de log: cada vez que o programa executa, acrescente ao arquivo `log.txt` uma linha com a data e a hora (`LocalDateTime.now()` formatado) e a mensagem `"Programa executado"`.

**Exercício 15.4:** Altere `RepositorioLivros` para que linhas com formato inválido (menos de 3 campos ou ano não numérico) sejam **ignoradas** e contadas. Mostre no final quantas linhas foram descartadas.

**Exercício 15.5:** Adicione ao `AgendaDeLivros` um menu com as opções `1. Listar`, `2. Cadastrar` e `3. Sair`. Os cadastros devem ser salvos no arquivo ao escolher `Sair`.

**Exercício 15.6 (Projeto progressivo):** Faça o sistema de `Artesao` salvar e carregar os artesãos e as vendas em arquivos CSV, usando o campo de data do capítulo 14.

---

[Índice](../../README.md) | [← Anterior: 14. Datas com java.time](../14-datas-com-java-time/README.md) | [Próximo: 16. Java como API REST →](../16-java-como-api-rest/README.md)
