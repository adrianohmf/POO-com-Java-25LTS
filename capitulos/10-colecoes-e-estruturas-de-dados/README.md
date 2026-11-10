[Índice](../../README.md) | [← Anterior: 9. Classes Abstratas e Interfaces](../09-classes-abstratas-e-interfaces/README.md) | [Próximo: 11. Conjuntos e Mapas: HashSet e HashMap →](../11-mapas-com-hashmap/README.md)

# 10. Coleções: ArrayList

> **Códigos deste capítulo:** [codigos/10-colecoes-e-estruturas-de-dados](../../codigos/10-colecoes-e-estruturas-de-dados/)

> **O que você vai aprender neste capítulo:**
> Usar `ArrayList` para listas dinâmicas de objetos, percorrer coleções com o laço for each, remover elementos com segurança e aplicar métodos úteis como `sort()`, `contains()` e `remove()`.

Até agora trabalhamos com um ou poucos objetos por vez. Mas sistemas reais lidam com centenas ou milhares de objetos: uma escola tem centenas de alunos, uma loja tem milhares de produtos, um banco tem milhões de contas.

Arrays resolvem parte do problema, mas têm uma limitação crítica: o tamanho é fixo. Se você criar um array de 100 alunos e precisar do 101º, o array não cresce. Para "crescer", seria preciso criar um array maior e copiar tudo manualmente, o que dá trabalho e abre espaço para erros.

As **coleções** do Java resolvem isso. O `ArrayList` é uma lista que cresce conforme necessário, com métodos prontos para adicionar, remover, buscar e ordenar. No próximo capítulo você conhece o `HashSet`, que guarda elementos sem repetição, e o `HashMap`, que associa uma chave a um valor, como um dicionário, útil quando você precisa buscar por nome, CPF ou código.

Dominar coleções é essencial para qualquer projeto Java real. O projeto final deste guia usa `ArrayList` para armazenar alunos e notas, exatamente como um sistema acadêmico real faria.

## Visão geral: o framework de coleções

Todas as coleções do Java ficam no pacote `java.util` e fazem parte do chamado **Java Collections Framework**. Ele é organizado em torno de três ideias principais, cada uma representada por uma interface:

* **`List`**: sequência ordenada de elementos, com posições (índices) e que aceita repetidos. Implementação mais usada: `ArrayList`.
* **`Set`**: conjunto de elementos sem repetição. Implementação mais usada: `HashSet`.
* **`Map`**: pares de chave e valor, com chaves únicas. Implementação mais usada: `HashMap`.

Neste capítulo você conhece `List`, com `ArrayList`. O `Set` e o `Map` ficam para o próximo capítulo. Como vimos no capítulo 9, programar pensando na interface traz flexibilidade, e por isso é comum declarar `List<String> nomes = new ArrayList<>();`. Nos exemplos abaixo usamos `var` para manter o código curto, mas lembre que o tipo real da variável continua sendo `ArrayList`.

## 10.1 Array vs ArrayList

Compare os dois lado a lado:

```
Array                           ArrayList
                                
Tamanho fixo                    Tamanho dinâmico
int[] nums = new int[5];        var lista = new ArrayList<Integer>();
nums[0] = 10;                   lista.add(10);
Acesso: nums[0]                 Acesso: lista.get(0)
Tamanho: nums.length            Tamanho: lista.size()
Aceita primitivos (int)         Só aceita objetos (Integer)
Sem métodos úteis               sort(), contains(), remove()...
```

Repare em dois detalhes que costumam confundir iniciantes:

1. **Tamanho:** no array é o atributo `length` (sem parênteses). No `ArrayList` é o método `size()` (com parênteses).
2. **Tipos:** o `ArrayList` só trabalha com objetos. Por isso, para guardar números inteiros usamos `Integer`, para decimais `Double`, para booleanos `Boolean`, e assim por diante. Essas classes são chamadas de **wrappers** (empacotadores) dos tipos primitivos. O Java converte automaticamente entre `int` e `Integer`, um recurso chamado **autoboxing**.

### Lendo a sintaxe `ArrayList<String>`

Os sinais `<` e `>` indicam o **tipo genérico** da lista, ou seja, o tipo dos elementos que ela guarda. `ArrayList<String>` é uma lista de textos, `ArrayList<Aluno>` é uma lista de objetos `Aluno`. Isso garante segurança: o compilador recusa, por exemplo, adicionar um número numa lista de `String`.

Na expressão `var nomes = new ArrayList<String>();`, a palavra `var` pede ao compilador que descubra o tipo da variável pelo lado direito. É o mesmo que escrever `ArrayList<String> nomes = new ArrayList<String>();`, só que mais curto.

### Exemplo completo, passo a passo

```java
// Arquivo: UsandoArrayList.java

import java.util.ArrayList;
import java.util.Collections;

void main() {

    var nomes = new ArrayList<String>();

    // Adicionando
    nomes.add("Maria");
    nomes.add("João");
    nomes.add("Ana");
    nomes.add("Pedro");

    IO.println("Lista: "    + nomes);
    IO.println("Tamanho: "  + nomes.size());
    IO.println("Primeiro: " + nomes.get(0));
    IO.println("Tem Ana? "  + nomes.contains("Ana"));

    // Removendo
    nomes.remove("João");   // por valor
    nomes.remove(0);        // por índice (remove Maria)

    // Percorrendo
    for (String nome : nomes) {
        IO.println("  * " + nome);
    }

    // Ordenando
    Collections.sort(nomes);
    IO.println("Ordenados: " + nomes);

    // ArrayList de objetos
    var alunos = new ArrayList<Aluno>();
    alunos.add(new Aluno("Maria", 20, "Informática"));
    alunos.add(new Aluno("João", 22, "Sistemas"));

    IO.println("Total: " + alunos.size());
    for (Aluno a : alunos) {
        a.apresentar();
    }
}

class Aluno {
    String nome; int idade; String curso;
    Aluno(String nome, int idade, String curso) {
        this.nome = nome; this.idade = idade; this.curso = curso;
    }
    void apresentar() {
        IO.println("  " + nome + " (" + idade + "a) — " + curso);
    }
}
```

**Explicação do código:**

* `import java.util.ArrayList;` e `import java.util.Collections;` trazem as classes que vamos usar. Sem o `import`, o compilador não reconhece o nome `ArrayList`.
* `nomes.add("Maria");` coloca o elemento no **final** da lista. A lista começa vazia e cresce a cada `add`. Após as quatro chamadas, ela contém `[Maria, Joao, Ana, Pedro]`, nessa ordem.
* `IO.println("Lista: " + nomes);` funciona porque o `ArrayList` sabe se transformar em texto. A saída é `Lista: [Maria, Joao, Ana, Pedro]`.
* `nomes.size()` devolve a quantidade de elementos (4). `nomes.get(0)` devolve o elemento da posição 0, ou seja, `Maria`. **Os índices começam em 0**, como nos arrays.
* `nomes.contains("Ana")` percorre a lista e devolve `true` se encontrar o elemento. Para comparar, ele usa o método `equals()`.
* `nomes.remove("Joao")` remove o **primeiro elemento igual** ao valor informado. A lista passa a ser `[Maria, Ana, Pedro]`.
* `nomes.remove(0)` remove o elemento da **posição** 0 (Maria). A lista passa a ser `[Ana, Pedro]`. Veja que o mesmo método `remove` tem dois comportamentos, dependendo de receber um `String` ou um `int`. Esse detalhe gera uma pegadinha logo adiante.
* No laço `for (String nome : nomes)` lemos: "para cada `nome` em `nomes`". A cada volta, a variável `nome` recebe o próximo elemento. Não precisamos controlar índice nem se preocupar com o tamanho da lista.
* `Collections.sort(nomes)` ordena a lista alterando-a diretamente. Para `String`, a ordem é alfabética. Resultado: `Ordenados: [Ana, Pedro]`.
* Na parte final, criamos uma lista de objetos `Aluno`. O princípio é o mesmo, só muda o tipo entre `<>`. No for each, `Aluno a` representa cada aluno e chamamos `a.apresentar()` nele.

**Saída esperada do programa:**

```
Lista: [Maria, João, Ana, Pedro]
Tamanho: 4
Primeiro: Maria
Tem Ana? true
  * Ana
  * Pedro
Ordenados: [Ana, Pedro]
Total: 2
  Maria (20a) — Informática
  João (22a) — Sistemas
```

### Outros métodos úteis do ArrayList

```java
void main() {
    var frutas = new ArrayList<String>();

    frutas.add("Manga");
    frutas.add("Caju");
    frutas.add(1, "Acerola");        // insere na posição 1: [Manga, Acerola, Caju]
    frutas.set(0, "Goiaba");         // substitui a posição 0: [Goiaba, Acerola, Caju]

    IO.println(frutas.indexOf("Caju"));   // 2 (posição); devolve -1 se não achar
    IO.println(frutas.isEmpty());         // false
    IO.println(frutas.size());            // 3

    var extras = new ArrayList<String>();
    extras.add("Umbu");
    extras.add("Cajá");
    frutas.addAll(extras);                // junta as duas listas

    IO.println(frutas);                   // [Goiaba, Acerola, Caju, Umbu, Cajá]

    frutas.clear();                       // esvazia a lista
    IO.println(frutas.isEmpty());         // true
}
```

Resumo dos métodos mais usados:

* `add(e)`: adiciona no final.
* `add(i, e)`: insere na posição `i`, empurrando os demais para frente.
* `get(i)`: lê o elemento da posição `i`.
* `set(i, e)`: troca o elemento da posição `i` por `e`.
* `remove(i)` ou `remove(obj)`: remove por posição ou por valor.
* `indexOf(obj)`: posição da primeira ocorrência, ou -1 se não existir.
* `contains(obj)`: informa se o elemento existe na lista.
* `size()`, `isEmpty()`, `clear()`: tamanho, teste de lista vazia e limpeza.
* `addAll(outra)`: adiciona todos os elementos de outra coleção.

### A pegadinha do `remove` com `ArrayList<Integer>`

```java
void main() {
    var numeros = new ArrayList<Integer>();
    numeros.add(10);
    numeros.add(20);
    numeros.add(30);

    numeros.remove(1);                    // remove a POSIÇÃO 1 (o valor 20)
    IO.println(numeros);                  // [10, 30]

    numeros.remove(Integer.valueOf(30));  // remove o VALOR 30
    IO.println(numeros);                  // [10]
}
```

Quando a lista é de `Integer`, `remove(1)` é interpretado como "remover a posição 1", e não "remover o valor 1". Para remover por valor, é preciso informar um objeto, como em `Integer.valueOf(30)`.

### Ordenando com critérios próprios

`Collections.sort(lista)` só funciona sozinho para tipos que o Java já sabe comparar (`String`, `Integer`, `Double`...). Para ordenar objetos `Aluno`, precisamos dizer **por qual critério**. Isso é feito com um `Comparator`:

```java
import java.util.ArrayList;
import java.util.Comparator;

void main() {
    var alunos = new ArrayList<Aluno>();
    alunos.add(new Aluno("Maria", 20, "Informática"));
    alunos.add(new Aluno("João", 22, "Sistemas"));
    alunos.add(new Aluno("Ana", 19, "Redes"));

    // Ordenar pelo nome (ordem alfabética)
    alunos.sort(Comparator.comparing(a -> a.nome));
    for (Aluno a : alunos) a.apresentar();

    // Ordenar pela idade (do mais novo ao mais velho)
    alunos.sort(Comparator.comparingInt(a -> a.idade));
    for (Aluno a : alunos) a.apresentar();
}
```

O trecho `a -> a.nome` é uma **expressão lambda**. Ela significa: "dado um aluno `a`, use `a.nome` como critério de comparação". Não é preciso dominar lambdas agora; basta copiar o padrão `Comparator.comparing(a -> a.atributo)` e trocar o atributo desejado. Use `comparingInt` quando o atributo for `int`.

### Buscar objetos com `contains` e `equals`

Com `String`, `contains` funciona direto porque a classe `String` já sabe comparar conteúdos. Com objetos criados por você, o Java compara **referências** (se são o mesmo objeto na memória), a menos que a classe sobrescreva `equals()`:

```java
var a1 = new Aluno("Maria", 20, "Informática");
var a2 = new Aluno("Maria", 20, "Informática");

var lista = new ArrayList<Aluno>();
lista.add(a1);

IO.println(lista.contains(a1));  // true: é o mesmo objeto
IO.println(lista.contains(a2));  // false: objeto diferente, mesmo com dados iguais
```

Para a busca por nome ou por outro atributo, o caminho mais simples neste estágio do curso é percorrer a lista com for each e comparar o atributo desejado:

```java
Aluno buscarPorNome(ArrayList<Aluno> alunos, String nome) {
    for (Aluno a : alunos) {
        if (a.nome.equals(nome)) {
            return a;
        }
    }
    return null; // não encontrou
}
```

## Erros comuns do Tema 10

```java
// ERRO 1: usar tipo primitivo no ArrayList (use o wrapper)
ArrayList<int> lista = new ArrayList<>();     // ERRADO
ArrayList<Integer> lista = new ArrayList<>(); // CORRETO

// ERRO 2: ConcurrentModificationException, modificar a lista enquanto percorre
for (String nome : lista) {
    lista.remove(nome); // ERRO em tempo de execução!
}
// Use removeIf, que faz a remoção com segurança:
lista.removeIf(nome -> nome.startsWith("J")); // CORRETO

// ERRO 3: get() fora do índice
var lista = new ArrayList<String>();
lista.add("Maria");
lista.get(1); // ERRO: IndexOutOfBoundsException! Só existe o índice 0
```

**Entendendo cada erro:**

1. **Primitivo na lista:** os tipos genéricos exigem classes. Troque `int` por `Integer`, `double` por `Double`, `boolean` por `Boolean`, `char` por `Character`.
2. **Modificar durante o percorrimento:** o Java detecta que a lista mudou no meio do for each e interrompe o programa com `ConcurrentModificationException`. A solução mais simples é `removeIf`, que recebe uma condição (lambda) e remove todos os elementos que a satisfazem. Outra alternativa é usar um `Iterator` e chamar `iterator.remove()`.
3. **Índice inválido:** numa lista de tamanho `n`, os índices válidos vão de 0 até `n` menos 1. Chamar `get` com valor fora desse intervalo lança `IndexOutOfBoundsException`. Antes de acessar, confira com `size()`.

## Exercícios do Tema 10

> **Exercício 10.1** Sistema de lista de presença com `ArrayList<Aluno>`: adicionar, remover, listar, buscar por curso.
>
> *Dicas:* crie um menu com `switch` (capítulo anterior) com as opções adicionar, remover, listar e buscar. Para remover, peça o nome e percorra a lista procurando o aluno; use `removeIf(a -> a.nome.equals(nome))` para remover com segurança. Para buscar por curso, percorra a lista e imprima apenas os alunos cujo `curso` seja igual ao informado.
>
> **Exercício 10.2 (Projeto progressivo)** Substitua os arrays de `Artesao` por `ArrayList`. Adicione métodos para listar artesãos por técnica e calcular o faturamento total de todos os artesãos.
>
> *Dicas:* troque o array `Artesao[]` por `ArrayList<Artesao>` e os acessos `arr[i]` por `lista.get(i)`, ou melhor, use for each. Para listar por técnica, percorra a lista comparando `a.tecnica.equals(tecnica)`. Para o faturamento total, declare uma variável acumuladora `double total = 0;` antes do laço e some o faturamento de cada artesão a cada volta.

---

[Índice](../../README.md) | [← Anterior: 9. Classes Abstratas e Interfaces](../09-classes-abstratas-e-interfaces/README.md) | [Próximo: 11. Conjuntos e Mapas: HashSet e HashMap →](../11-mapas-com-hashmap/README.md)
