[Índice](../README.md) | [← Anterior: 3. Classes e Objetos](../03-classes-e-objetos/README.md) | [Próximo: 5. Construtores →](../05-construtores/README.md)

# 4. Atributos e Métodos

> **O que você vai aprender neste capítulo:**
> Entender os diferentes tipos de atributos (de instância, estaticos e constantes), criar métodos com e sem retorno, usar sobrecarga de métodos e entender o papel do `this`.

Se a classe é o molde e o objeto e o produto final, então os **atributos** são o que o objeto guarda e os **métodos** são o que o objeto sabe fazer. Juntos, eles definem completamente o comportamento de um objeto.

Um `Aluno` guarda `nome`, `idade` e `notas` — esses são seus atributos. Ele sabe `apresentar()` seus dados, `calcularMedia()` e dizer sua `getSituacao()` — esses são seus métodos. Atributos e métodos trabalham juntos: os métodos usam os atributos para produzir resultados.

Neste capítulo você vai descobrir que existem diferentes tipos de atributos (cada objeto com o seu, ou compartilhado por todos) e diferentes tipos de métodos (que fazem algo ou que devolvem um valor). Você também vai aprender um recurso poderoso do Java: ter varios métodos com o mesmo nome, cada um resolvendo um problema diferente.


## 4.1 Tipos de atributos — diagrama

```
Atributos de Instancia          Atributos Estaticos
----------------------          -------------------
Cada objeto tem o seu           Todos os objetos compartilham

  Aluno a1                        Aluno
  nome: "Maria"     ---|          totalAlunos: 3  <-- unico na memoria
  idade: 20             |
                        |----> class Aluno
  Aluno a2             |       { static int totalAlunos }
  nome: "Joao"      ---|
  idade: 22
```

```java
// Arquivo: TiposAtributos.java

class Produto {

    // ATRIBUTOS DE INSTÂNCIA: cada objeto tem sua própria cópia
    String nome;
    double preco;

    // ATRIBUTO ESTÁTICO: compartilhado por TODOS os objetos. Pertence à
    // classe, compartilhado por todos os objetos, mas o valor pode ser
    // modificado ao longo do tempo.
    static int totalProdutos = 0;

    // CONSTANTE: pertence à classe (não muda por objeto) e o valor não
    // pode ser alterado (é uma constante real). (por convenção: MAIÚSCULAS)
    static final double TAXA_IMPOSTO = 0.12;

    // ATRIBUTO COM VALOR PADRÃO
    boolean ativo  = true;
    String  origem = "Nordeste";
}

void main() {
    var p1 = new Produto();
    p1.nome  = "Mel de Abelha";
    p1.preco = 25.00;

    IO.println(p1.nome + " — ativo: " + p1.ativo + " — origem: " + p1.origem);

    // Atributo estático: acessado PELA CLASSE, não pelo objeto
    IO.println("Taxa de imposto: " + Produto.TAXA_IMPOSTO);

    double precoFinal = p1.preco * (1 + Produto.TAXA_IMPOSTO);
    System.out.format("Preco com imposto: R$ %.2f%n", precoFinal);
}
```

> **Por que `%.2f` em vez de concatenar direto?** Operações com `double` podem gerar resultados como `28.000000000000004` em vez de `28.0`, por causa de como números decimais são representados internamente na memória. Usar `%.2f` no `System.out.format()` arredonda a exibição para 2 casas decimais, sem esse ruído. Veja a seção [Saída formatada — System.out.format e printf](../01-introducao-java-jdk25/README.md#saída-formatada--systemoutformat-e-printf) para mais detalhes.

### Atributo estático (`static`) — pertence à classe

Um atributo estático não pertence a nenhum objeto individual — ele pertence à **classe**. Isso significa que existe **uma única cópia** dele na memória, compartilhada por todos os objetos daquela classe. Se um objeto mudar o valor, todos os outros "enxergam" a mudança, porque não existe cópia separada para cada um.

```java
// Arquivo: AtributoEstatico.java

class Produto {
    String nome;

    // static: uma unica copia, compartilhada por TODOS os objetos
    static int totalProdutos = 0;

    Produto(String nome) {
        this.nome = nome;
        totalProdutos++;   // incrementa a copia UNICA a cada objeto criado
    }
}

void main() {
    IO.println("Total antes: " + Produto.totalProdutos); // 0

    var p1 = new Produto("Mel de Abelha");
    var p2 = new Produto("Castanha de Caju");
    var p3 = new Produto("Rapadura");

    // Acessado PELA CLASSE, nao por p1, p2 ou p3
    IO.println("Total depois: " + Produto.totalProdutos); // 3

    // Mesmo acessando por um objeto, o valor e o MESMO para todos
    IO.println("Via p1: " + p1.totalProdutos); // 3
    IO.println("Via p2: " + p2.totalProdutos); // 3
}
```

```
# Saída esperada:
Total antes: 0
Total depois: 3
Via p1: 3
Via p2: 3
```

Repare que `totalProdutos` não foi zerado nem duplicado para cada `Produto` criado — os três objetos **compartilham a mesma variável**. Compare com um atributo de instância como `nome`: cada objeto tem o seu próprio, independente dos demais.

> **Convenção:** mesmo sendo possível acessar um atributo estático através de um objeto (`p1.totalProdutos`), o correto é sempre acessar pela classe (`Produto.totalProdutos`) — deixa claro para quem lê o código que aquele valor é compartilhado, e não específico de um objeto.

## 4.2 Tipos de métodos

Assim como os atributos guardam os dados do objeto, os métodos definem o que ele **sabe fazer**. Em Java, existem três tipos principais de método, e a diferença entre eles está em duas perguntas: *ele devolve algum valor?* e *ele pertence ao objeto ou à classe?*

**Método `void`** — executa uma ação, mas não devolve nada para quem chamou. Serve para efeitos como imprimir na tela, alterar um atributo, ou disparar alguma operação.

**Método com retorno** — processa algo e devolve um valor, que pode ser guardado numa variável ou usado diretamente. O tipo do retorno (`int`, `double`, `String`, etc.) é declarado antes do nome do método, e o corpo precisa terminar com `return valorAlgumaCoisa;`.

**Método estático** — não pertence a nenhum objeto específico, e sim à classe como um todo. Por isso pode ser chamado sem usar `new` — direto pelo nome da classe, como `Calculadora.calcularImposto(...)`. É útil para operações que não dependem do estado de um objeto particular (cálculos genéricos, conversões, utilitários).

Veja cada um isoladamente antes do exemplo completo:

**Método `void`** — executa uma ação, mas não devolve nada.

```java
void exibirSoma(int a, int b) {
    IO.println("Soma: " + (a + b));
}
```

**Método com retorno** — processa e devolve um valor, guardado com `return`.

```java
int somar(int a, int b) {
    return a + b;
}
```

```
Tipo de método       Devolve valor?      Como chamar
---------------      --------------      -----------------------------
void                 Não                 objeto.metodo()
com retorno          Sim                 var x = objeto.metodo()
estático             Sim ou não          Classe.metodo()  (sem "new")
```

```java
// Arquivo: TiposMetodos.java

class Calculadora {

    // MÉTODO VOID: executa ação, não retorna valor
    void exibirSoma(int a, int b) {
        IO.println("Soma: " + (a + b));
    }

    // MÉTODO COM RETORNO: declara o tipo antes do nome
    int somar(int a, int b) {
        return a + b;
    }

    double calcularMedia(double[] notas) {
        double soma = 0;
        for (double nota : notas) soma += nota;
        return soma / notas.length;
    }

    String classificar(double media) {
        if (media >= 7.0)      return "Aprovado";
        else if (media >= 5.0) return "Recuperacao";
        else                   return "Reprovado";
    }

    // MÉTODO ESTÁTICO: chamado pela classe, sem criar objeto
    static double calcularImposto(double valor, double taxa) {
        return valor * taxa;
    }
}

void main() {
    var calc = new Calculadora();

    calc.exibirSoma(10, 5);

    int resultado = calc.somar(10, 5);
    IO.println("Resultado: " + resultado);

    double[] notas = { 8.0, 7.5, 9.0, 6.5 };
    double media = calc.calcularMedia(notas);
    IO.println("Media:    " + media);
    IO.println("Situacao: " + calc.classificar(media));

    // Método estático: sem criar objeto
    IO.println("Imposto: R$ " + Calculadora.calcularImposto(100.0, 0.12));
}
```

**Percorrendo o exemplo:** `exibirSoma()` é `void` — só imprime, não retorna nada, por isso é chamado sozinho na linha `calc.exibirSoma(10, 5);`. Já `somar()` retorna um `int`, então seu resultado é guardado em `resultado`. O mesmo vale para `calcularMedia()` e `classificar()` — cada um devolve um valor que é usado logo em seguida. Por fim, `calcularImposto()` é `static`: repare que ele é chamado como `Calculadora.calcularImposto(...)`, direto pela classe, sem precisar do objeto `calc`.

> **Por que `calcularImposto` é estático?** Porque calcular imposto sobre um valor não depende de nenhum dado específico de um objeto `Calculadora` — é uma operação genérica que só usa os parâmetros recebidos. Quando um método não acessa nenhum atributo de instância, ele é candidato natural a ser `static`.

## 4.3 Sobrecarga de métodos (Overloading)

> **Sobrecarga:** mesmo nome, parâmetros diferentes. O Java escolhe automaticamente qual versão chamar pelo tipo e quantidade de argumentos.

```java
// Arquivo: Sobrecarga.java

class Impressora {

    // Versão 1: recebe texto
    void imprimir(String texto) {
        IO.println("[TEXTO] " + texto);
    }

    // Versão 2: recebe número
    void imprimir(int numero) {
        IO.println("[NUMERO] " + numero);
    }

    // Versão 3: recebe texto e quantidade de vezes
    void imprimir(String texto, int vezes) {
        for (int i = 0; i < vezes; i++) {
            IO.println("[" + (i+1) + "] " + texto);
        }
    }
}

void main() {
    var imp = new Impressora();

    imp.imprimir("Ola!");         // chama versão 1
    imp.imprimir(42);             // chama versão 2
    imp.imprimir("Java 25!", 3);  // chama versão 3
}
```

## Erros comuns — Tema 4

```java
// ERRO 1: esquecer o "return" em método com retorno
int somar(int a, int b) {
    int resultado = a + b;
    // esqueceu o return! -> ERRO de compilação
}

// ERRO 2: usar o retorno de um método void
void exibir() { IO.println("Ola"); }
String texto = exibir(); // ERRADO: void não retorna nada

// ERRO 3: acessar atributo estático pelo objeto
var p = new Produto();
p.TAXA_IMPOSTO;          // funciona mas e ma prática
Produto.TAXA_IMPOSTO;    // CORRETO

// ERRO 4: dividir inteiros e esperar decimal
int a = 5, b = 2;
double resultado = a / b;     // resultado = 2.0 (não 2.5!)
double resultado = (double) a / b; // CORRETO: resultado = 2.5
```

## Exercícios — Tema 4

> **Exercício 4.1** — Crie `ContaBancaria` com: `titular`, `saldo`. Métodos: `depositar(double)`, `sacar(double)` (sem saldo negativo), `exibirSaldo()`.
>
> **Exercício 4.2** — Adicione atributo estatico `totalContas` a `ContaBancaria`. Incremente a cada conta criada.
>
> **Exercício 4.3 (Projeto progressivo)** — Adicione métodos a `Artesao`: `calcularFaturamento(int pecasVendidas)` e `exibirPerfil()`. Crie uma versão sobrecarregada de `exibirPerfil(boolean detalhado)`.

---

[Índice](../README.md) | [← Anterior: 3. Classes e Objetos](../03-classes-e-objetos/README.md) | [Próximo: 5. Construtores →](../05-construtores/README.md)
