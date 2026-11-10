[Índice](../../README.md) | [← Anterior: 10. Coleções: ArrayList e HashSet](../10-colecoes-e-estruturas-de-dados/README.md) | [Próximo: 12. Tratamento de Exceções →](../12-tratamento-de-excecoes/README.md)

# 11. Mapas com HashMap

> **Códigos deste capítulo:** [codigos/11-mapas-com-hashmap](../../codigos/11-mapas-com-hashmap/)

> **O que você vai aprender neste capítulo:**
> Usar `HashMap` para associar chaves a valores, aplicar métodos como `put()`, `get()`, `containsKey()` e `getOrDefault()`, percorrer um mapa com `entrySet()`, contar ocorrências, guardar listas dentro de um mapa e escolher entre `List`, `Set` e `Map`.

No capítulo anterior, as listas localizavam elementos pela **posição**, e os conjuntos garantiam elementos **sem repetição**. Mas, muitas vezes, queremos localizar pelo **significado**: "qual a nota da Maria?", "qual o produto de código 4021?".

O `Map` é a terceira ideia do Java Collections Framework: ele associa uma **chave** a um **valor**, como um dicionário ou uma agenda de contatos. A implementação mais usada é o `HashMap`, e é ela que você conhece neste capítulo. Ele aparece em praticamente todo sistema real: cadastros por CPF, produtos por código, configurações por nome.

## 11.1 HashMap: chave e valor

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

**Explicação do código:**

* `new HashMap<String, Double>()` declara um mapa em que a **chave é `String`** (nome do aluno) e o **valor é `Double`** (nota). Há dois tipos entre `<>`, na ordem chave e valor. Lembre que, como nas listas, não se usa `double` primitivo, e sim o wrapper `Double`.
* `notas.put("Maria", 8.5)` insere o par. Se a chave `Maria` já existisse, o valor seria trocado.
* `notas.get("Maria")` devolve o valor associado, no caso `8.5`. Se a chave não existir, devolve `null`.
* `notas.containsKey("Carlos")` testa se a chave existe. Como Carlos não foi inserido, o resultado é `false`.
* `notas.put("Joao", 7.5)` mostra que `put` com chave existente **atualiza** o valor. Joao passa de 7.0 para 7.5 e continua havendo um único Joao no mapa.
* `notas.entrySet()` devolve o conjunto de **entradas** (cada par chave e valor). No for each, a variável `entry` representa um par, e usamos `entry.getKey()` para a chave e `entry.getValue()` para o valor.
* A expressão `entry.getValue() >= 7.0 ? "Aprovado" : "Reprovado"` é o operador ternário: se a condição for verdadeira, resulta no primeiro texto, senão no segundo.
* `notas.remove("Pedro")` remove o par inteiro cuja chave é `Pedro`. Depois disso, `size()` devolve 3.

**Saída esperada** (a ordem das linhas do boletim pode variar de uma execução para outra, é normal no `HashMap`):

```
Nota da Maria: 8.5
Tem Carlos? false
Boletim:
  Maria: 8.5 — Aprovado
  Joao: 7.5 — Aprovado
  Ana: 9.5 — Aprovado
  Pedro: 6.0 — Reprovado
Total: 3
```

## 11.2 Outros métodos úteis do HashMap

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

## 11.3 Padrão clássico: contar ocorrências

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

## 11.4 Mapa com lista como valor

O valor de um mapa pode ser qualquer tipo, inclusive outra coleção. Isso permite agrupar elementos sob uma mesma chave, como alunos por curso ou artesãos por estado (é o que pede o Exercício 11.1):

```java
void main() {
    var porEstado = new HashMap<String, ArrayList<String>>();

    porEstado.put("Pernambuco", new ArrayList<String>());
    porEstado.get("Pernambuco").add("Maria");
    porEstado.get("Pernambuco").add("Joao");

    porEstado.put("Ceara", new ArrayList<String>());
    porEstado.get("Ceara").add("Ana");

    IO.println(porEstado.get("Pernambuco"));  // [Maria, Joao]
}
```

Atenção: antes de usar `porEstado.get("Pernambuco").add(...)`, a lista daquele estado precisa existir. Caso contrário, `get` devolve `null` e o programa lança `NullPointerException`.

## 11.5 Variações do HashMap

* **`HashMap`**: não garante ordem. É o mais rápido e o mais usado.
* **`LinkedHashMap`**: mantém a **ordem de inserção**. Útil quando a ordem de exibição importa.
* **`TreeMap`**: mantém as chaves **ordenadas** (alfabética ou numericamente).

Para trocar, basta mudar a classe no `new`, mantendo os mesmos métodos: `var notas = new TreeMap<String, Double>();`.

## 11.6 Qual coleção escolher?

Agora que você conhece as três famílias, use esta regra para decidir:

* Preciso de uma sequência com ordem e posição, aceitando repetidos: `ArrayList`.
* Preciso buscar rapidamente um valor a partir de um identificador (nome, CPF, código): `HashMap`.
* Preciso garantir que não haja repetidos: `HashSet`.
* Tenho um tamanho fixo e conhecido, e quero desempenho máximo ou uso tipos primitivos: array.

## Erros comuns do Tema 11

```java
// ERRO 1: comparar objetos Double com == em vez de equals
Double a = notas.get("Maria");
Double b = notas.get("Ana");
a == b;        // ERRADO: compara referências, não valores
a.equals(b);   // CORRETO: compara os valores

// ERRO 2: usar o retorno de get() sem verificar se a chave existe
double nota = notas.get("Carlos"); // ERRO: NullPointerException (get devolveu null)
double nota = notas.getOrDefault("Carlos", 0.0); // CORRETO

// ERRO 3: esperar que o HashMap preserve a ordem de inserção
var notas = new HashMap<String, Double>();
notas.put("Maria", 8.5);
notas.put("Joao", 7.0);
IO.println(notas);     // a ordem de exibição não é garantida
// Para manter a ordem de inserção, use LinkedHashMap; para ordem alfabética, TreeMap.
```

**Entendendo cada erro:**

1. **`==` entre objetos:** com objetos, `==` testa se são **o mesmo objeto na memória**, e não se têm o mesmo conteúdo. Para `Double`, `Integer` e `String`, compare sempre com `equals()`. Observação: comparar um objeto `Double` com um literal primitivo, como `notas.get("Maria") == 8.5`, funciona, porque o Java converte o objeto automaticamente para `double` antes de comparar. O problema aparece quando os **dois lados** são objetos.
2. **Chave inexistente:** `get` devolve `null` quando a chave não existe, e converter `null` para `double` causa `NullPointerException`. Use `containsKey` antes ou, melhor, `getOrDefault`.
3. **Ordem não garantida:** o `HashMap` organiza os pares pelo cálculo do hash, e não pela ordem em que foram inseridos. Se a ordem de exibição importa, troque por `LinkedHashMap` (ordem de inserção) ou `TreeMap` (chaves ordenadas), como vimos na seção 11.5.

## Exercícios do Tema 11

> **Exercício 11.1** `HashMap<String, ArrayList<String>>` com catálogo de artesãos nordestinos por estado.
>
> *Dicas:* a chave é o estado e o valor é a lista de nomes de artesãos daquele estado. Ao cadastrar um artesão, verifique com `containsKey` se o estado já existe; se não existir, crie a lista antes (`put(estado, new ArrayList<String>())`) e depois adicione o nome. Para exibir, percorra o mapa com `entrySet()` e imprima o estado seguido da lista de artesãos.
>
> **Exercício 11.2 (desafio)** Usando o padrão de contagem com `HashMap<String, Integer>`, conte quantos artesãos existem por técnica e imprima o resultado. Depois, descubra qual técnica tem mais artesãos.

---

[Índice](../../README.md) | [← Anterior: 10. Coleções: ArrayList e HashSet](../10-colecoes-e-estruturas-de-dados/README.md) | [Próximo: 12. Tratamento de Exceções →](../12-tratamento-de-excecoes/README.md)
