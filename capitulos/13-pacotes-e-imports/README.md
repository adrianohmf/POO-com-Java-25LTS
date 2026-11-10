[Índice](../../README.md) | [← Anterior: 12. Tratamento de Exceções](../12-tratamento-de-excecoes/README.md) | [Próximo: 14. Datas com java.time →](../14-datas-com-java-time/README.md)

# 13. Pacotes e Imports

> **Códigos deste capítulo:** [codigos/13-pacotes-e-imports](../../codigos/13-pacotes-e-imports/)

> **O que você vai aprender neste capítulo:**
> Organizar classes em pacotes, usar `package` e `import` corretamente, entender o modificador de acesso padrão (sem palavra-chave), compilar um projeto com várias pastas pelo terminal e evitar os erros mais comuns de organização.

Até aqui, quase todos os exemplos tinham uma ou duas classes no mesmo arquivo ou na mesma pasta. Um sistema real tem dezenas ou centenas de classes. Se todas ficarem juntas, encontrar qualquer coisa vira uma busca no escuro, e dois programadores podem criar classes com o mesmo nome sem perceber.

Os **pacotes** resolvem os dois problemas. Um pacote é um agrupamento de classes relacionadas, com um nome próprio. Na prática, ele funciona como uma pasta: as classes de modelo ficam em uma, as classes de serviço em outra, a classe que inicia o programa em uma terceira.

Você já usa pacotes desde o capítulo 1: `ArrayList` está em `java.util`, `LocalDate` está em `java.time` e `String` está em `java.lang`. Neste capítulo você aprende a criar os seus.

## 13.1 O que é um pacote

Um pacote tem três funções:

| Função | Explicação |
|---|---|
| **Organizar** | Agrupa classes por assunto (`modelo`, `servico`, `app`). |
| **Evitar conflito de nomes** | Duas classes `Livro` podem existir, desde que estejam em pacotes diferentes. |
| **Controlar acesso** | Define o que é visível apenas dentro do pacote e o que é público. |

Por convenção, o nome de um pacote usa **letras minúsculas** e começa com o domínio da organização escrito ao contrário. O IFPE usa `br.edu.ifpe`, então um projeto de biblioteca ficaria `br.edu.ifpe.biblioteca`. Cada ponto representa uma subpasta.

```
br.edu.ifpe.biblioteca.modelo   ->   br/edu/ifpe/biblioteca/modelo/
```

> **Regra de ouro:** o nome do pacote declarado no arquivo precisa ser **igual ao caminho das pastas** onde ele está.

## 13.2 Declarando e organizando pacotes

A declaração `package` é sempre a **primeira linha de código** do arquivo (comentários podem vir antes). Vamos montar um projeto `Biblioteca` com três pacotes:

```
Biblioteca/
└── src/
    └── br/edu/ifpe/biblioteca/
        ├── modelo/
        │   └── Livro.java
        ├── servico/
        │   └── Catalogo.java
        └── app/
            └── App.java
```

A classe `Livro` representa o dado. Ela é `public` para poder ser usada por outros pacotes:

```java
// Arquivo: src/br/edu/ifpe/biblioteca/modelo/Livro.java

package br.edu.ifpe.biblioteca.modelo;

public record Livro(String titulo, String autor, int ano) {

    public String descricao() {
        return titulo + " (" + autor + ", " + ano + ")";
    }
}
```

A classe `Catalogo` guarda a lista de livros. Ela precisa **importar** `Livro`, pois está em outro pacote:

```java
// Arquivo: src/br/edu/ifpe/biblioteca/servico/Catalogo.java

package br.edu.ifpe.biblioteca.servico;

import br.edu.ifpe.biblioteca.modelo.Livro;
import java.util.ArrayList;

public class Catalogo {

    private final ArrayList<Livro> livros = new ArrayList<>();

    public void adicionar(Livro livro) {
        livros.add(livro);
    }

    public ArrayList<Livro> buscarPorAutor(String autor) {
        var encontrados = new ArrayList<Livro>();
        for (Livro livro : livros) {
            if (livro.autor().equalsIgnoreCase(autor)) {
                encontrados.add(livro);
            }
        }
        return encontrados;
    }

    public int total() {
        return livros.size();
    }

    // Sem modificador: visível apenas para classes do pacote "servico"
    void limpar() {
        livros.clear();
    }
}
```

Por fim, a classe `App` inicia o programa. Os arquivos deste projeto usam a forma **clássica** (`public class` com `public static void main`), porque a forma compacta com `void main()` serve para programas de um único arquivo e não se combina com pacotes:

```java
// Arquivo: src/br/edu/ifpe/biblioteca/app/App.java

package br.edu.ifpe.biblioteca.app;

import br.edu.ifpe.biblioteca.modelo.Livro;
import br.edu.ifpe.biblioteca.servico.Catalogo;

public class App {

    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        catalogo.adicionar(new Livro("Dom Casmurro", "Machado de Assis", 1899));
        catalogo.adicionar(new Livro("Quincas Borba", "Machado de Assis", 1891));
        catalogo.adicionar(new Livro("Vidas Secas", "Graciliano Ramos", 1938));

        System.out.println("Total no catálogo: " + catalogo.total());

        for (Livro livro : catalogo.buscarPorAutor("machado de assis")) {
            System.out.println(livro.descricao());
        }
    }
}
```

Saída esperada:

```
Total no catálogo: 3
Dom Casmurro (Machado de Assis, 1899)
Quincas Borba (Machado de Assis, 1891)
```

## 13.3 Importando classes

O `import` diz ao compilador onde encontrar uma classe que está em outro pacote. Ele vem logo depois da linha `package`, antes da declaração da classe.

| Forma | Exemplo | Quando usar |
|---|---|---|
| Import de uma classe | `import java.util.ArrayList;` | Forma recomendada: deixa claro de onde cada classe vem. |
| Import de todo o pacote | `import java.util.*;` | Funciona, mas esconde a origem das classes. Evite em código de projeto. |
| Import estático | `import static java.lang.Math.max;` | Permite usar um método ou constante estática sem o nome da classe. |
| Nome completo | `java.util.ArrayList<String> lista` | Dispensa o import. Útil quando duas classes têm o mesmo nome. |

Duas observações importantes:

* O pacote `java.lang` (`String`, `Math`, `Integer`, `System`...) é importado automaticamente. Você nunca precisa escrever `import java.lang.String;`.
* Nos arquivos compactos do JDK 25, os pacotes do módulo `java.base` (como `java.util`) também ficam disponíveis sem `import`. Mesmo assim, **os exemplos deste guia mantêm os imports explícitos**, porque em um projeto com pacotes eles são obrigatórios e é bom criar o hábito.

```java
// Arquivo: UsandoImports.java

import java.util.ArrayList;
import static java.lang.Math.max;    // import estático: permite escrever max(...) sem "Math."

void main() {
    // ArrayList foi importado na primeira linha do arquivo
    var nomes = new ArrayList<String>();
    nomes.add("Ana");
    nomes.add("Bruno");

    // Nome totalmente qualificado: dispensa o import (útil em conflitos de nome)
    java.util.ArrayList<String> copia = new java.util.ArrayList<>(nomes);
    copia.add("Carla");

    IO.println(nomes);
    IO.println(copia);

    // Classes de java.lang (String, Math, Integer...) nunca precisam de import
    IO.println(Math.abs(-7));
    IO.println(max(3, 9));
}
```

Saída esperada:

```
[Ana, Bruno]
[Ana, Bruno, Carla]
7
9
```

## 13.4 Modificadores de acesso e pacotes

No capítulo 6 você viu `private` e `public`. Agora o **pacote** entra na conversa. Existe um quarto nível de acesso, que aparece quando você **não escreve nenhum modificador**: o acesso **padrão** (também chamado de *package-private*).

| Modificador | Mesma classe | Mesmo pacote | Subclasse em outro pacote | Qualquer lugar |
|---|:---:|:---:|:---:|:---:|
| `private` | sim | não | não | não |
| (padrão, sem palavra) | sim | sim | não | não |
| `protected` | sim | sim | sim | não |
| `public` | sim | sim | sim | sim |

No projeto `Biblioteca`, o método `limpar()` de `Catalogo` não tem modificador. Por isso, só as classes do pacote `servico` conseguem chamá-lo. Se `App` (pacote `app`) tentar, o compilador recusa:

```
error: limpar() is not public in Catalogo; cannot be accessed from outside package
```

Use essa regra como guia: comece com o acesso mais restrito possível e abra apenas o que outros pacotes realmente precisam usar.

## 13.5 Compilando e executando um projeto com pacotes

Em projetos com pacotes, o `javac` e o `java` precisam saber onde estão as pastas. Dentro da pasta `Biblioteca`:

**Linux e macOS**

```
javac -d out $(find src -name "*.java")
java -cp out br.edu.ifpe.biblioteca.app.App
```

**Windows (PowerShell)**

```
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out br.edu.ifpe.biblioteca.app.App
```

* `-d out` manda o `javac` salvar os arquivos `.class` na pasta `out`, recriando a estrutura dos pacotes.
* `-cp out` (*classpath*) diz ao `java` onde procurar as classes compiladas.
* O nome passado ao `java` é o **nome completo da classe**: pacote mais classe (`br.edu.ifpe.biblioteca.app.App`).

No **VS Code**, abra a pasta `Biblioteca` com **File > Open Folder**. A extensão Java reconhece a pasta `src` e mostra o botão **Run** acima do `main` da classe `App`.

> **Dica:** a pasta `out` é gerada automaticamente. Não envie essa pasta para o GitHub; ela entra no `.gitignore`.

## Erros comuns: Tema 13

```java
// ERRO 1: pacote declarado não bate com a pasta onde o arquivo está
// Arquivo: src/br/edu/ifpe/biblioteca/modelo/Livro.java
package br.edu.ifpe.biblioteca.servico;      // ERRADO: a pasta é "modelo"
// A classe passa a pertencer ao pacote "servico". Quem faz import de "modelo" recebe:
// package br.edu.ifpe.biblioteca.modelo does not exist
// CORRETO: o package deve repetir exatamente o caminho das pastas dentro de src

// ERRO 2: esquecer o import de uma classe de outro pacote
public class App {
    public static void main(String[] args) {
        Livro livro = new Livro("Vidas Secas", "Graciliano Ramos", 1938);   // cannot find symbol
    }
}
// CORRETO: import br.edu.ifpe.biblioteca.modelo.Livro;

// ERRO 3: usar uma classe sem public em outro pacote
package br.edu.ifpe.biblioteca.modelo;
class Livro { }                                  // sem public: só o pacote "modelo" enxerga
// Mensagem: Livro is not public in br.edu.ifpe.biblioteca.modelo; cannot be accessed from outside package

// ERRO 4: chamar um método sem modificador de outro pacote
catalogo.limpar();                               // limpar() é visível só no pacote "servico"
// Mensagem: limpar() is not public in Catalogo; cannot be accessed from outside package

// ERRO 5: rodar a classe pelo nome simples, sem o pacote
// java -cp out App
// Mensagem: Error: Could not find or load main class App
// CORRETO: java -cp out br.edu.ifpe.biblioteca.app.App

// ERRO 6: o mesmo nome de classe vindo de dois pacotes
import java.util.Date;
import java.sql.Date;                            // ERRO: o nome Date já foi importado
// Mensagem: a type with the same simple name is already defined by the single-type-import of Date
// CORRETO: importar um e usar o outro com o nome completo
// import java.util.Date;
// java.sql.Date dataSql = new java.sql.Date(0L);

// ERRO 7: apagar a pasta "out" e esquecer de compilar de novo
// java -cp out br.edu.ifpe.biblioteca.app.App
// Mensagem: Error: Could not find or load main class ... (os .class não existem mais)
```

## Exercícios: Tema 13

**Exercício 13.1:** Crie um projeto `Escola` com os pacotes `br.edu.ifpe.escola.modelo` (record `Aluno` com nome e nota), `br.edu.ifpe.escola.servico` (classe `Turma` com um `ArrayList<Aluno>` e um método `media()`) e `br.edu.ifpe.escola.app` (classe com o `main`). Compile pelo terminal com `javac -d out` e execute com `java -cp out`.

**Exercício 13.2:** No projeto do exercício anterior, remova o `public` da classe `Aluno` e compile de novo. Leia a mensagem de erro e explique com suas palavras por que ela aparece.

**Exercício 13.3:** Em `Turma`, crie um método `void zerar()` sem modificador e tente chamá-lo a partir da classe `App`. Corrija o código de duas formas diferentes e discuta qual delas é a melhor.

**Exercício 13.4:** Escreva um programa que use `java.util.Date` e `java.sql.Date` ao mesmo tempo. Resolva o conflito de nomes usando o nome completo de uma das classes.

**Exercício 13.5 (Projeto progressivo):** Reorganize o projeto `Artesao` em pacotes `modelo`, `servico` e `app`, usando o domínio `br.edu.ifpe.artesanato`.

---

[Índice](../../README.md) | [← Anterior: 12. Tratamento de Exceções](../12-tratamento-de-excecoes/README.md) | [Próximo: 14. Datas com java.time →](../14-datas-com-java-time/README.md)
