[Índice](../README.md) | [← Anterior: 5. Construtores](../05-construtores/README.md) | [Próximo: 7. Herança →](../07-heranca/README.md)

# 6. Encapsulamento

> **O que você vai aprender neste capítulo:**
> Entender por que expor atributos diretamente e perigoso, usar `private` para proteger os dados, criar getters e setters com validação, e aplicar os modificadores de acesso corretamente.

Pense no painel de controle de um avião. O piloto acessa os sistemas por botões e alavancas específicas — ele não mexe diretamente nos motores, nos cabos hidráulicos ou nos circuitos. Essa separação existe por segurança: garante que só ações válidas e controladas possam afetar o sistema.

Encapsulamento é exatamente esse princípio aplicado ao código. Quando um atributo é público, qualquer parte do programa pode alterar seu valor diretamente — inclusive para valores absurdos como saldo negativo, nota acima de 10 ou idade negativa. Não há nenhuma barreira de proteção.

Ao tornar os atributos privados e fornecer métodos controlados de acesso, você garante que o objeto sempre esteja em um estado válido. É um dos pilares mais importantes da POO na prática, é o que separa um código amador de um código profissional.


## 6.1 O problema sem encapsulamento

```
SEM encapsulamento:
   Main --> conta.saldo = -999999   <- qualquer um pode fazer isso!
   Main --> conta.saldo = 0.001
   Main --> conta.saldo = "abc"     <- Java nao deixa, mas a logica fica exposta

COM encapsulamento:
   Main --> conta.sacar(100)        <- passa pela validacao
                |
                v
            if (valor > saldo) -> BLOQUEIA
            if (valor <= 0)    -> BLOQUEIA
            else               -> executa
```

```mermaid
classDiagram
    class ContaBancaria {
        -String titular
        -double saldo
        -String numeroConta
        -boolean ativa
        +getTitular() String
        +getSaldo() double
        +isAtiva() boolean
        +depositar(double valor) void
        +sacar(double valor) void
        +exibirExtrato() void
    }
```

### O que são getters e setters

Depois de tornar um atributo `private`, o resto do programa perde o acesso direto a ele — e é exatamente esse o objetivo. Mas o objeto ainda precisa de alguma forma controlada de **ler** e **alterar** esse valor. É para isso que existem os **getters** e **setters**: métodos públicos que fazem a ponte entre o mundo externo e o atributo privado.

**Getter** — método de **leitura**. Devolve o valor de um atributo privado, sem permitir alterá-lo. Por convenção, o nome começa com `get` seguido do nome do atributo com a primeira letra maiúscula: o atributo `titular` tem o getter `getTitular()`. Para atributos `boolean`, a convenção troca `get` por `is`: o atributo `ativa` tem o getter `isAtiva()`, não `getAtiva()`.

**Setter** — método de **escrita**. Recebe um novo valor e o atribui ao atributo privado, mas passando por uma validação antes. Por convenção, o nome começa com `set`: o atributo `titular` tem o setter `setTitular(String titular)`.

```
Sem getter/setter                    Com getter/setter
------------------                   ------------------
obj.saldo             (bloqueado,     obj.getSaldo()      leitura controlada
                       saldo e private)
obj.saldo = valor     (bloqueado)     obj.setSaldo(valor) escrita com validação
```

> **Por que não simplesmente tornar o atributo `public` de novo?** Porque o getter e o setter são um ponto único de controle. Um getter pode formatar o valor antes de devolvê-lo; um setter pode rejeitar valores inválidos (como um saldo negativo). Se o atributo fosse público, qualquer parte do código poderia alterá-lo livremente, sem passar por nenhuma dessas regras — voltando ao problema que o encapsulamento resolve.

Nem todo atributo precisa dos dois. No exemplo abaixo, `saldo` tem getter (`getSaldo()`) mas não tem setter direto — porque a única forma correta de alterá-lo é através dos métodos `depositar()` e `sacar()`, que aplicam as validações de negócio.

```java
// Arquivo: ContaEncapsulada.java

class ContaBancaria {

    // ATRIBUTOS PRIVADOS: acessíveis SOMENTE dentro desta classe
    private String  titular;
    private double  saldo;
    private String  numeroConta;
    private boolean ativa;

    ContaBancaria(String titular, double saldoInicial) {
        this.titular     = titular;
        this.saldo       = Math.max(saldoInicial, 0);
        this.numeroConta = "AG001-" + (1000 + (int)(Math.random() * 8999));
        this.ativa       = true;
    }

    // GETTERS: métodos de leitura
    public String  getTitular()     { return titular; }
    public double  getSaldo()       { return saldo; }
    public String  getNumeroConta() { return numeroConta; }
    public boolean isAtiva()        { return ativa; }

    // SETTER com validação
    public void setTitular(String titular) {
        if (titular != null && !titular.isBlank()) {
            this.titular = titular;
        } else {
            IO.println("Nome invalido.");
        }
    }

    // MÉTODOS DE NEGOCIO: a única forma de alterar o saldo
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            IO.println("Deposito de R$ " + valor + " realizado.");
        } else {
            IO.println("Valor de deposito invalido.");
        }
    }

    public void sacar(double valor) {
        if (!ativa)        { IO.println("Conta inativa."); return; }
        if (valor <= 0)    { IO.println("Valor invalido."); return; }
        if (valor > saldo) { IO.println("Saldo insuficiente."); return; }
        saldo -= valor;
        IO.println("Saque de R$ " + valor + " realizado.");
    }

    public void exibirExtrato() {
        IO.println("========================================");
        IO.println("  CONTA:   " + numeroConta);
        IO.println("  TITULAR: " + titular);
        IO.println("  SALDO:   R$ " + String.format("%.2f", saldo));
        IO.println("  STATUS:  " + (ativa ? "Ativa" : "Inativa"));
        IO.println("========================================");
    }
}

void main() {
    var c1 = new ContaBancaria("Maria Silva", 500.00);

    c1.exibirExtrato();
    c1.depositar(300.00);
    c1.sacar(100.00);
    c1.sacar(1000.00);  // bloqueado!

    // c1.saldo = -999; // ERRO de compilação: saldo e private

    IO.println("Saldo atual: R$ " + c1.getSaldo());
    c1.exibirExtrato();
}
```

## 6.2 Diferença entre private e protected

Esses dois modificadores aparecem o tempo todo em POO e geram confusão no início, porque parecem semelhantes — ambos restringem o acesso. A diferença está em **quem mais pode enxergar o atributo ou método**.

**`private`** — o mais restritivo. Apenas a **própria classe** onde o membro foi declarado pode acessá-lo. Nem uma subclasse (via herança) consegue enxergar um atributo `private` do pai.

**`protected`** — um meio-termo. Além da própria classe, **as subclasses também podem acessar**, mesmo estando em arquivos ou pacotes diferentes. É o modificador natural para atributos que uma classe pai quer compartilhar com quem herda dela, mas ainda proteger do resto do programa.

```java
// Arquivo: PrivateVsProtected.java

class Pessoa {
    private String cpf;      // SÓ Pessoa acessa
    protected String nome;   // Pessoa E suas subclasses acessam

    Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf  = cpf;
    }
}

class Aluno extends Pessoa {
    Aluno(String nome, String cpf) {
        super(nome, cpf);
    }

    void exibir() {
        IO.println(nome);   // OK: protected e visível na subclasse
        // IO.println(cpf); // ERRO: cpf e private em Pessoa, Aluno não acessa
    }
}
```

> **Regra prática:** comece sempre com `private`. Só troque para `protected` quando tiver certeza de que as subclasses vão precisar acessar aquele atributo ou método diretamente. Trocar de `private` para `protected` depois é fácil; o contrário pode quebrar código que já dependia do acesso.

## 6.3 Tabela de visibilidade

| Modificador | Mesma Classe | Mesmo Pacote | Subclasse | Qualquer Lugar |
|---|:---:|:---:|:---:|:---:|
| `private`   | Sim | Não | Não | Não |
| *(padrao)*  | Sim | Sim | Não | Não |
| `protected` | Sim | Sim | Sim | Não |
| `public`    | Sim | Sim | Sim | Sim |

## Erros comuns — Tema 6

```java
// ERRO 1: getter retornando o atributo errado
private String nome;
private int    idade;

public String getNome() { return idade; }  // ERRADO: retorna idade!
public String getNome() { return nome; }   // CORRETO

// ERRO 2: setter sem validação (perde o sentido do encapsulamento)
public void setSaldo(double saldo) {
    this.saldo = saldo; // permite qualquer valor negativo!
}
// Não faca setter de saldo — use depositar() e sacar()

// ERRO 3: "this" desnecessário quando não há ambiguidade
class Aluno {
    private String nome;
    public String obterNome() {
        return this.nome; // funciona, mas "this" e desnecessário aqui
        return nome;      // mais limpo quando não há parâmetro de mesmo nome
    }
}

// ERRO 4: retornar referência de objeto mutável (expõe o interno)
private int[] notas = {8, 7, 9};
public int[] getNotas() { return notas; }  // PERIGOSO: quem recebe pode alterar!
public int[] getNotas() { return notas.clone(); } // CORRETO: retorna cópia
```

## Exercícios — Tema 6

> **Exercício 6.1** — Refatore `Artesao` para encapsulamento completo. Setter de `precoPecaMedia` rejeita valores negativos ou zero.
>
> **Exercício 6.2** — Crie `Estoque` com `quantidade` privada. Métodos `adicionar(int)` e `retirar(int)` com validacoes.
>
> **Exercício 6.3 (Projeto progressivo)** — Encapsule todos os atributos de `Artesao`. Adicione um histórico privado de vendas (`int[]`) com método para registrar nova venda e calcular o total vendido.

---

[Índice](../README.md) | [← Anterior: 5. Construtores](../05-construtores/README.md) | [Próximo: 7. Herança →](../07-heranca/README.md)
