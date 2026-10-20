[Índice](../../README.md) | [← Anterior: 9. Classes Abstratas e Interfaces](../09-classes-abstratas-e-interfaces/README.md) | [Próximo: 11. Tratamento de Exceções →](../11-tratamento-de-excecoes/README.md)

# 10. Coleções e Estruturas de Dados

> **Códigos deste capítulo:** [codigos/10-colecoes-e-estruturas-de-dados](../../codigos/10-colecoes-e-estruturas-de-dados/)

> **O que você vai aprender neste capítulo:**
> Usar `ArrayList` para listas dinamicas de objetos, usar `HashMap` para associar chaves a valores, percorrer coleções com for-each e aplicar métodos úteis como `sort()`, `contains()` e `remove()`.

Até agora trabalhamos com um ou poucos objetos por vez. Mas sistemas reais lidam com centenas ou milhares de objetos: uma escola tem centenas de alunos, uma loja tem milhares de produtos, um banco tem milhoes de contas.

Arrays resolvem parte do problema, mas tem uma limitacao critica: o tamanho e fixo. Se você criar um array de 100 alunos e precisar do 101, o array não cresce.

As **coleções** do Java resolvem isso. O `ArrayList` e uma lista que cresce conforme necessário, com métodos prontos para adicionar, remover, buscar e ordenar. O `HashMap` associa uma chave a um valor — como um dicionario — permitindo buscas instantaneas por nome, CPF, código ou qualquer outro identificador.

Dominar coleções e essencial para qualquer projeto Java real. O projeto final deste guia usa `ArrayList` para armazenar alunos e notas, exatamente como um sistema acadêmico real faria.


## 10.1 Array vs ArrayList

```
Array                           ArrayList
-----                           ---------
Tamanho fixo                    Tamanho dinamico
int[] nums = new int[5];        var lista = new ArrayList<Integer>();
nums[0] = 10;                   lista.add(10);
Acesso: nums[0]                 Acesso: lista.get(0)
Sem metodos uteis               sort(), contains(), remove()...
```

```java
// Arquivo: UsandoArrayList.java

import java.util.ArrayList;
import java.util.Collections;

void main() {

    var nomes = new ArrayList<String>();

    // Adicionando
    nomes.add("Maria");
    nomes.add("Joao");
    nomes.add("Ana");
    nomes.add("Pedro");

    IO.println("Lista: "    + nomes);
    IO.println("Tamanho: "  + nomes.size());
    IO.println("Primeiro: " + nomes.get(0));
    IO.println("Tem Ana? "  + nomes.contains("Ana"));

    // Removendo
    nomes.remove("Joao");   // por valor
    nomes.remove(0);        // por índice (remove Maria)

    // Percorrendo
    for (String nome : nomes) {
        IO.println("  - " + nome);
    }

    // Ordenando
    Collections.sort(nomes);
    IO.println("Ordenados: " + nomes);

    // ArrayList de objetos
    var alunos = new ArrayList<Aluno>();
    alunos.add(new Aluno("Maria", 20, "Informatica"));
    alunos.add(new Aluno("Joao", 22, "Sistemas"));

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

## 10.2 HashMap

```java
// Arquivo: UsandoHashMap.java

import java.util.HashMap;

void main() {

    // HashMap: associa chave -> valor
    var notas = new HashMap<String, Double>();

    notas.put("Maria", 8.5);
    notas.put("Joao",  7.0);
    notas.put("Ana",   9.5);
    notas.put("Pedro", 6.0);

    IO.println("Nota da Maria: " + notas.get("Maria"));
    IO.println("Tem Carlos? "   + notas.containsKey("Carlos"));

    notas.put("Joao", 7.5); // atualiza o valor

    IO.println("Boletim:");
    for (var entry : notas.entrySet()) {
        var sit = entry.getValue() >= 7.0 ? "Aprovado" : "Reprovado";
        IO.println("  " + entry.getKey() + ": " + entry.getValue() + " — " + sit);
    }

    notas.remove("Pedro");
    IO.println("Total: " + notas.size());
}
```

## Erros comuns — Tema 10

```java
// ERRO 1: usar tipo primitivo no ArrayList (use o wrapper)
ArrayList<int> lista = new ArrayList<>();     // ERRADO
ArrayList<Integer> lista = new ArrayList<>(); // CORRETO

// ERRO 2: ConcurrentModificationException — modificar lista enquanto percorre
for (String nome : lista) {
    lista.remove(nome); // ERRO em tempo de execução!
}
// Use iterator ou colete para remover depois:
lista.removeIf(nome -> nome.startsWith("J")); // CORRETO

// ERRO 3: get() fora do índice
var lista = new ArrayList<String>();
lista.add("Maria");
lista.get(1); // ERRO: IndexOutOfBoundsException! Só existe índice 0

// ERRO 4: usar == para comparar String em HashMap
notas.containsKey("Maria");  // CORRETO: usa equals internamente
notas.get("Maria") == 8.5;   // ERRADO para Double: use .equals()
notas.get("Maria").equals(8.5); // CORRETO
```

## Exercícios — Tema 10

> **Exercício 10.1** — Sistema de lista de presença com `ArrayList<Aluno>`: adicionar, remover, listar, buscar por curso.
>
> **Exercício 10.2** — `HashMap<String, ArrayList<String>>` com catalogo de artesaos nordestinos por estado.
>
> **Exercício 10.3 (Projeto progressivo)** — Substitua os arrays de `Artesao` por `ArrayList`. Adicione métodos para listar artesaos por técnica e calcular o faturamento total de todos os artesaos.

---

[Índice](../../README.md) | [← Anterior: 9. Classes Abstratas e Interfaces](../09-classes-abstratas-e-interfaces/README.md) | [Próximo: 11. Tratamento de Exceções →](../11-tratamento-de-excecoes/README.md)
