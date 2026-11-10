[Índice](../../README.md) | [← Anterior: 10. Coleções: ArrayList](../10-colecoes-e-estruturas-de-dados/README.md) | [Próximo: 12. Tratamento de Exceções →](../12-tratamento-de-excecoes/README.md)

# 11. Conjuntos e Mapas: HashSet e HashMap

> **Códigos deste capítulo:** [codigos/11-mapas-com-hashmap](../../codigos/11-mapas-com-hashmap/)

> **O que você vai aprender neste capítulo:**
> Usar `HashSet` para guardar elementos sem repetição, remover duplicados de uma lista, usar `HashMap` para associar chaves a valores, aplicar métodos como `put()`, `get()`, `containsKey()` e `getOrDefault()`, percorrer um mapa com `entrySet()`, contar ocorrências, guardar listas dentro de um mapa e escolher entre `List`, `Set` e `Map`.

No capítulo anterior, as listas localizavam elementos pela **posição**. Este capítulo apresenta as outras duas ideias do Java Collections Framework: o `Set`, que garante elementos **sem repetição**, e o `Map`, que localiza elementos pelo **significado**: "qual a nota da Maria?", "qual o produto de código 4021?".

O `Set` funciona como um conjunto matemático, e a implementação mais usada é o `HashSet`. O `Map` associa uma **chave** a um **valor**, como um dicionário ou uma agenda de contatos, e a implementação mais usada é o `HashMap`. Ele aparece em praticamente todo sistema real: cadastros por CPF, produtos por código, configurações por nome.

## 11.1 HashSet: conjuntos sem repetição

Quando você precisa guardar elementos **sem permitir duplicatas**, use um `HashSet`. Ele funciona como um conjunto matemático:

```java
// Arquivo: UsandoHashSet.java

import java.util.ArrayList;
import java.util.HashSet;

void main() {
    var cidades = new HashSet<String>();

    IO.println(cidades.add("Recife"));    // true: foi inserido
    IO.println(cidades.add("Olinda"));    // true
    IO.println(cidades.add("Recife"));    // false: já existia, ignorado

    IO.println("Total: " + cidades.size());                     // 2
    IO.println("Tem Olinda? " + cidades.contains("Olinda"));    // true

    // Uso prático: remover duplicados de uma lista
    var comRepetidos = new ArrayList<String>();
    comRepetidos.add("Renda");
    comRepetidos.add("Cerâmica");
    comRepetidos.add("Renda");
    comRepetidos.add("Renda");

    var unicos = new HashSet<String>(comRepetidos);
    IO.println("Antes: " + comRepetidos.size() + " itens");
    IO.println("Depois: " + unicos.size() + " itens");
}
```

O método `add` devolve `true` se o elemento foi inserido e `false` se ele já existia. A ordem dos elementos também não é garantida: o `HashSet` organiza os elementos pelo cálculo do hash, e não pela ordem de inserção. O segundo trecho mostra um uso prático: **remover duplicados** de uma lista, bastando criar o `HashSet` a partir dela. Como o conjunto não tem posições, não existe `get(0)`: para percorrer, use o for each.

Saída esperada:

```
true
true
false
Total: 2
Tem Olinda? true
Antes: 4 itens
Depois: 2 itens
```

## 11.2 HashMap: chave e valor

O `HashMap` guarda **pares de chave e valor**.

Pense numa agenda de contatos: o nome é a chave, o telefone é o valor. Você não procura "o contato da posição 37", e sim "o contato Ana".

Regras importantes:

* Cada **chave é única**. Se você inserir uma chave que já existe, o valor antigo é substituído.
* Os **valores podem se repetir**. Duas pessoas podem ter a mesma nota.
* A busca por chave é muito rápida, mesmo com milhões de elementos, porque o `HashMap` usa um cálculo chamado *hash* para saber onde procurar, em vez de percorrer tudo.
* A **ordem de percorrimento não é garantida**. Não conte com os elementos aparecendo na ordem em que foram inseridos.

```java
// Arquivo: UsandoHashMap.java

import java.util.HashMap;

void main() {

    // HashMap: associa chave -> valor
    var notas = new HashMap<String, Double>();

    notas.put("Maria", 8.5);
    notas.put("João",  7.0);
    notas.put("Ana",   9.5);
    notas.put("Pedro", 6.0);

    IO.println("Nota da Maria: " + notas.get("Maria"));
    IO.println("Tem Carlos? "   + notas.containsKey("Carlos"));

    notas.put("João", 7.5); // atualiza o valor

    IO.println("Boletim:");
    for (var entry : notas.entrySet()) {
        var sit = entry.getValue() >= 7.0 ? "Aprovado" : "Reprovado";
        IO.println("  " + entry.getKey() + ": " + entry.getValue() + " — " + sit);
    }

    notas.remove("Pedro");
    IO.println("Total: " + notas.size());
}
```

**Explicação do código:**

* `new HashMap<String, Double>()` declara um mapa em que a **chave é `String`** (nome do aluno) e o **valor é `Double`** (nota). Há dois tipos entre `<>`, na ordem chave e valor. Lembre que, como nas listas, não se usa `double` primitivo, e sim o wrapper `Double`.
* `notas.put("Maria", 8.5)` insere o par. Se a chave `Maria` já existisse, o valor seria trocado.
* `notas.get("Maria")` devolve o valor associado, no caso `8.5`. Se a chave não existir, devolve `null`.
* `notas.containsKey("Carlos")` testa se a chave existe. Como Carlos não foi inserido, o resultado é `false`.
* `notas.put("Joao", 7.5)` mostra que `put` com chave existente **atualiza** o valor. João passa de 7.0 para 7.5 e continua havendo um único João no mapa.
* `notas.entrySet()` devolve o conjunto de **entradas** (cada par chave e valor). No for each, a variável `entry` representa um par, e usamos `entry.getKey()` para a chave e `entry.getValue()` para o valor.
* A expressão `entry.getValue() >= 7.0 ? "Aprovado" : "Reprovado"` é o operador ternário: se a condição for verdadeira, resulta no primeiro texto, senão no segundo.
* `notas.remove("Pedro")` remove o par inteiro cuja chave é `Pedro`. Depois disso, `size()` devolve 3.

**Saída esperada** (a ordem das linhas do boletim pode variar de uma execução para outra, é normal no `HashMap`):

```
Nota da Maria: 8.5
Tem Carlos? false
Boletim:
  Maria: 8.5 — Aprovado
  João: 7.5 — Aprovado
  Ana: 9.5 — Aprovado
  Pedro: 6.0 — Reprovado
Total: 3
```

## 11.3 Outros métodos úteis do HashMap

```java
void main() {
    var estoque = new HashMap<String, Integer>();
    estoque.put("Rapadura", 30);
    estoque.put("Cuscuz", 12);

    // getOrDefault: evita null quando a chave não existe
    int qtd = estoque.getOrDefault("Tapioca", 0);
    IO.println(qtd);                          // 0

    // putIfAbsent: só insere se a chave ainda não existir
    estoque.putIfAbsent("Rapadura", 99);      // ignorado, já existe (continua 30)
    estoque.putIfAbsent("Beiju", 8);          // inserido

    // Percorrer só as chaves ou só os valores
    for (String produto : estoque.keySet()) {
        IO.println("Produto: " + produto);
    }
    for (int quantidade : estoque.values()) {
        IO.println("Quantidade: " + quantidade);
    }

    // containsValue: procura entre os valores
    IO.println(estoque.containsValue(12));    // true
}
```

* `getOrDefault(chave, padrao)`: devolve o valor da chave ou o padrão informado se ela não existir. Evita o `null`.
* `putIfAbsent(chave, valor)`: insere apenas se a chave ainda não existir.
* `keySet()`: todas as chaves. `values()`: todos os valores. `entrySet()`: todos os pares.
* `containsKey(chave)` e `containsValue(valor)`: testam a existência de uma chave ou de um valor. A busca por chave é rápida; a busca por valor percorre o mapa inteiro.

## 11.4 Padrão clássico: contar ocorrências

Um uso muito comum do `HashMap` é contar quantas vezes algo aparece:

```java
void main() {
    var tecnicas = new ArrayList<String>();
    tecnicas.add("Renda");
    tecnicas.add("Cerâmica");
    tecnicas.add("Renda");
    tecnicas.add("Xilogravura");
    tecnicas.add("Renda");

    var contagem = new HashMap<String, Integer>();

    for (String t : tecnicas) {
        contagem.put(t, contagem.getOrDefault(t, 0) + 1);
    }

    IO.println(contagem);  // {Renda=3, Cerâmica=1, Xilogravura=1}
}
```

Para cada técnica, lemos a contagem atual (ou 0, se ainda não existir), somamos 1 e gravamos de volta. É um padrão que aparece em análise de textos, estatísticas e relatórios.

## 11.5 Mapa com lista como valor

O valor de um mapa pode ser qualquer tipo, inclusive outra coleção. Isso permite agrupar elementos sob uma mesma chave, como alunos por curso ou artesãos por estado (é o que pede o Exercício 11.1):

```java
void main() {
    var porEstado = new HashMap<String, ArrayList<String>>();

    porEstado.put("Pernambuco", new ArrayList<String>());
    porEstado.get("Pernambuco").add("Maria");
    porEstado.get("Pernambuco").add("João");

    porEstado.put("Ceará", new ArrayList<String>());
    porEstado.get("Ceará").add("Ana");

    IO.println(porEstado.get("Pernambuco"));  // [Maria, João]
}
```

Atenção: antes de usar `porEstado.get("Pernambuco").add(...)`, a lista daquele estado precisa existir. Caso contrário, `get` devolve `null` e o programa lança `NullPointerException`.

## 11.6 Variações do HashMap

* **`HashMap`**: não garante ordem. É o mais rápido e o mais usado.
* **`LinkedHashMap`**: mantém a **ordem de inserção**. Útil quando a ordem de exibição importa.
* **`TreeMap`**: mantém as chaves **ordenadas** (alfabética ou numericamente).

Para trocar, basta mudar a classe no `new`, mantendo os mesmos métodos: `var notas = new TreeMap<String, Double>();`.

## 11.7 Qual coleção escolher?

Agora que você conhece as três famílias, use esta regra para decidir:

* Preciso de uma sequência com ordem e posição, aceitando repetidos: `ArrayList`.
* Preciso buscar rapidamente um valor a partir de um identificador (nome, CPF, código): `HashMap`.
* Preciso garantir que não haja repetidos: `HashSet`.
* Tenho um tamanho fixo e conhecido, e quero desempenho máximo ou uso tipos primitivos: array.

## Erros comuns do Tema 11

```java
// ERRO 1: tratar um HashSet como se fosse uma lista
var cidades = new HashSet<String>();
cidades.add("Recife");
cidades.get(0);        // ERRO: o conjunto não tem posições, o método get não existe

// ERRO 2: comparar objetos Double com == em vez de equals
Double a = notas.get("Maria");
Double b = notas.get("Ana");
a == b;        // ERRADO: compara referências, não valores
a.equals(b);   // CORRETO: compara os valores

// ERRO 3: usar o retorno de get() sem verificar se a chave existe
double nota = notas.get("Carlos"); // ERRO: NullPointerException (get devolveu null)
double nota = notas.getOrDefault("Carlos", 0.0); // CORRETO

// ERRO 4: esperar que o HashMap preserve a ordem de inserção
var notas = new HashMap<String, Double>();
notas.put("Maria", 8.5);
notas.put("João", 7.0);
IO.println(notas);     // a ordem de exibição não é garantida
// Para manter a ordem de inserção, use LinkedHashMap; para ordem alfabética, TreeMap.
```

**Entendendo cada erro:**

1. **Conjunto não tem índice:** o `HashSet` não guarda posições, então não existe `get(0)`. Percorra com for each ou use `contains` para testar se um elemento existe. Se você precisa de posições, use `ArrayList`.
2. **`==` entre objetos:** com objetos, `==` testa se são **o mesmo objeto na memória**, e não se têm o mesmo conteúdo. Para `Double`, `Integer` e `String`, compare sempre com `equals()`. Observação: comparar um objeto `Double` com um literal primitivo, como `notas.get("Maria") == 8.5`, funciona, porque o Java converte o objeto automaticamente para `double` antes de comparar. O problema aparece quando os **dois lados** são objetos.
3. **Chave inexistente:** `get` devolve `null` quando a chave não existe, e converter `null` para `double` causa `NullPointerException`. Use `containsKey` antes ou, melhor, `getOrDefault`.
4. **Ordem não garantida:** o `HashMap` organiza os pares pelo cálculo do hash, e não pela ordem em que foram inseridos. Se a ordem de exibição importa, troque por `LinkedHashMap` (ordem de inserção) ou `TreeMap` (chaves ordenadas), como vimos na seção 11.6.

## Exercícios do Tema 11

> **Exercício 11.1** Dada uma lista de nomes com repetições, use um `HashSet` para mostrar apenas os nomes únicos e quantos eles são. Depois, teste `add` com um nome repetido e mostre o valor devolvido.
>
> *Dicas:* crie o `HashSet` a partir da lista (`new HashSet<String>(lista)`) e use `size()` para contar. O retorno de `add` é `true` ou `false`.
>
> **Exercício 11.2** `HashMap<String, ArrayList<String>>` com catálogo de artesãos nordestinos por estado.
>
> *Dicas:* a chave é o estado e o valor é a lista de nomes de artesãos daquele estado. Ao cadastrar um artesão, verifique com `containsKey` se o estado já existe; se não existir, crie a lista antes (`put(estado, new ArrayList<String>())`) e depois adicione o nome. Para exibir, percorra o mapa com `entrySet()` e imprima o estado seguido da lista de artesãos.
>
> **Exercício 11.3 (desafio)** Usando o padrão de contagem com `HashMap<String, Integer>`, conte quantos artesãos existem por técnica e imprima o resultado. Depois, descubra qual técnica tem mais artesãos.

---

[Índice](../../README.md) | [← Anterior: 10. Coleções: ArrayList](../10-colecoes-e-estruturas-de-dados/README.md) | [Próximo: 12. Tratamento de Exceções →](../12-tratamento-de-excecoes/README.md)
