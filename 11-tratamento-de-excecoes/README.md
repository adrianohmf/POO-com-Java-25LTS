[Índice](../README.md) | [← Anterior: 10. Coleções e Estruturas de Dados](../10-colecoes-e-estruturas-de-dados/README.md) | [Próximo: 12. Projeto Final — Aplicação Completa →](../12-projeto-final/README.md)

# 11. Tratamento de Exceções

> **O que você vai aprender neste capítulo:**
> Entender o que são exceções e por que elas existem, usar `try-catch-finally` para tratar erros, criar exceções personalizadas e construir programas que não travam diante de entradas inesperadas.

Todo programa, por mais bem escrito que seja, pode encontrar situacoes inesperadas: o usuario digita uma letra onde se esperava um número, o arquivo não existe, o saldo e insuficiente, a conexão cai. Como o programa deve se comportar nesses casos?

Sem tratamento de exceções, a resposta é simples e ruim: o programa trava, exibe uma mensagem de erro técnica incompreensivel e encerra. Com tratamento de exceções, o programa pode capturar o problema, reagir de forma inteligente, informar o usuario de forma clara e continuar funcionando.

Exceções são eventos anormais que interrompem o fluxo normal do programa. O Java tem um sistema elegante para lidar com elas: você tenta executar um bloco de código (`try`), captura possíveis erros (`catch`) e garante que recursos sejam liberados independente do resultado (`finally`). Você também pode criar suas próprias exceções para representar erros específicos do seu dominio, como `SaldoInsuficienteException` ou `NotaInvalidaException`.


## 11.1 Hierarquia de exceções

```
Throwable
  |
  +-- Error (problemas da JVM — nao trate)
  |     OutOfMemoryError, StackOverflowError
  |
  +-- Exception (problemas do programa — trate)
        |
        +-- RuntimeException (unchecked — opcional tratar)
        |     NullPointerException
        |     ArithmeticException
        |     ArrayIndexOutOfBoundsException
        |     IllegalArgumentException
        |
        +-- IOException (checked — obrigatorio tratar)
        |     FileNotFoundException
        |
        +-- SuaExcecaoPersonalizada extends Exception
```

```java
// Arquivo: TratamentoExcecoes.java

import java.util.Scanner;

void main() {
    var sc = new Scanner(System.in);

    try {
        IO.println("Digite um numero inteiro:");
        int num      = sc.nextInt();
        int resultado = 100 / num;
        IO.println("100 / " + num + " = " + resultado);

    } catch (ArithmeticException e) {
        // Captura divisão por zero
        IO.println("Erro: divisao por zero!");
        IO.println("Detalhe: " + e.getMessage());

    } catch (Exception e) {
        // Captura qualquer outro erro
        IO.println("Erro inesperado: " + e.getMessage());

    } finally {
        // Executa SEMPRE, com ou sem erro
        IO.println("Operacao finalizada.");
        sc.close();
    }
}
```

## 11.2 Exceções personalizadas

```java
// Arquivo: ExcecoesPersonalizadas.java

class SaldoInsuficienteException extends Exception {
    private double saldoAtual;
    private double valorSolicitado;

    SaldoInsuficienteException(double saldo, double solicitado) {
        super(String.format(
            "Saldo insuficiente. Atual: R$ %.2f | Solicitado: R$ %.2f",
            saldo, solicitado
        ));
        this.saldoAtual      = saldo;
        this.valorSolicitado = solicitado;
    }

    double getSaldo()      { return saldoAtual; }
    double getSolicitado() { return valorSolicitado; }
}

class Conta {
    private String titular;
    private double saldo;

    Conta(String titular, double saldo) {
        this.titular = titular;
        this.saldo   = saldo;
    }

    void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0)    throw new IllegalArgumentException("Valor invalido: " + valor);
        if (valor > saldo) throw new SaldoInsuficienteException(saldo, valor);
        saldo -= valor;
        IO.println("Saque de R$ " + valor + " realizado.");
    }

    double getSaldo() { return saldo; }
}

void main() {
    var conta = new Conta("Maria", 500.00);

    double[] tentativas = { 200.00, -50.00, 1000.00, 300.00 };

    for (double valor : tentativas) {
        try {
            IO.println("Tentando sacar R$ " + valor + "...");
            conta.sacar(valor);
            IO.println("Saldo: R$ " + conta.getSaldo());
        } catch (SaldoInsuficienteException e) {
            IO.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            IO.println("Aviso: " + e.getMessage());
        }
    }
}
```

## Erros comuns — Tema 11

```java
// ERRO 1: capturar Exception antes das específicas
try {
    // ...
} catch (Exception e) {          // captura tudo — as de baixo nunca executam!
    IO.println("Erro generico");
} catch (ArithmeticException e) { // NUNCA CHEGA AQUI
    IO.println("Divisao por zero");
}
// Coloque sempre as mais específicas PRIMEIRO

// ERRO 2: silenciar exceções (pior prática possível)
try {
    // ...
} catch (Exception e) {
    // não faz nada — o erro some sem trace!
}

// ERRO 3: lançar exceção sem mensagem útil
throw new Exception("erro"); // que erro? onde? por que?
throw new SaldoInsuficienteException(saldo, solicitado); // CORRETO: informativo

// ERRO 4: fechar recurso só no try (vaza se der exceção)
Scanner sc = new Scanner(System.in);
try {
    // ... usa o sc
    sc.close(); // se der exceção antes, sc nunca fecha!
} catch (Exception e) { }
// Use finally:
try { } catch (Exception e) { } finally { sc.close(); }
```

## Exercícios — Tema 11

> **Exercício 11.1** — `NotaInvalidaException` quando nota < 0 ou > 10. Lance no método `adicionarNota()` e trate no `main`.
>
> **Exercício 11.2** — `EstoqueInsuficienteException` com mensagem detalhada (estoque atual vs quantidade pedida).
>
> **Exercício 11.3 (Projeto progressivo)** — Adicione tratamento de exceções ao sistema de `Artesao`. Crie `VendaInvalidaException` para quantidade negativa e `EstoqueInsuficienteException` para estoque zerado. Implemente um menu interativo que não trava com erros.

---

[Índice](../README.md) | [← Anterior: 10. Coleções e Estruturas de Dados](../10-colecoes-e-estruturas-de-dados/README.md) | [Próximo: 12. Projeto Final — Aplicação Completa →](../12-projeto-final/README.md)
