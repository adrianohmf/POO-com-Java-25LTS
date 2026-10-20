[Índice](../../README.md) | [← Anterior: 12. Projeto Final — Aplicação Completa](../12-projeto-final/README.md) | [Próximo: 14. Erros Comuns e Como Resolver →](../14-erros-comuns/README.md)

# 13. Boas Práticas e Convenções

> **Códigos deste capítulo:** [codigos/13-boas-praticas-e-convencoes](../../codigos/13-boas-praticas-e-convencoes/)

> **O que você vai aprender neste capítulo:**
> As convenções de nomenclatura do Java, boas práticas de escrita de código limpo e a estrutura de um projeto Java profissional.

Saber fazer o código funcionar e o primeiro passo. Saber fazer o código ser **lido** e o segundo — e igualmente importante. Um código que funciona mas e impossivel de entender e um código que vai gerar problemas no futuro: dificuldade de manutenção, bugs difíceis de encontrar e resistencia da equipe para trabalhar com ele.

A comunidade Java construiu ao longo de decadas um conjunto de convenções que todos os desenvolvedores seguem. Quando você abre um projeto Java é ve `ContaBancaria`, sabe que é uma classe. Quando ve `calcularSaldo()`, sabe que é um método. Quando ve `TAXA_IMPOSTO`, sabe que é uma constante. Esse reconhecimento imediato é possível porque todos seguem as mesmas convenções.

Este capítulo não tem exercícios de código — e um capítulo para ler, refletir e aplicar nos exercícios e no projeto. Você vai perceber que as boas práticas não são regras arbitrarias: cada uma existe para resolver um problema real que aparece quando o código cresce.


## 13.1 Nomenclatura Java

| Elemento | Convenção | Exemplo |
|---|---|---|
| **Classe** | PrimeiraLetraMaiuscula (PascalCase) | `ContaBancaria`, `Aluno` |
| **Interface** | PascalCase, geralmente adjetivo | `Imprimivel`, `Exportavel` |
| **Método** | primeiraLetraMinuscula (camelCase) | `calcularSaldo()`, `getNome()` |
| **Variável** | camelCase | `saldoAtual`, `nomeCompleto` |
| **Constante** | TUDO_MAIUSCULO com _ | `TAXA_IMPOSTO`, `MAX_TENTATIVAS` |
| **Pacote** | tudo.minusculo.separado.por.ponto | `br.edu.ifpe.poo` |
| **Arquivo** | Idêntico ao nome da classe pública | `ContaBancaria.java` |

## 13.2 Boas práticas gerais

```java
// 1. NOMES SIGNIFICATIVOS — o código deve se autoexplicar
// RUIM:
int x = 5;
double calc(double v, double t) { return v * t; }

// BOM:
int totalAlunosAprovados = 5;
double calcularImposto(double valor, double taxa) { return valor * taxa; }

// 2. MÉTODOS CURTOS — uma função, uma responsabilidade
// RUIM: método que faz tudo
void processarAluno(Aluno a) {
    // válida, salva, envia email, imprime relatório...
}

// BOM: cada método faz uma coisa
void validarAluno(Aluno a) { }
void salvarAluno(Aluno a) { }
void notificarAluno(Aluno a) { }

// 3. EVITE NÚMEROS MÁGICOS — use constantes
// RUIM:
if (nota >= 7.0) return "Aprovado"; // de onde vem 7.0?

// BOM:
static final double NOTA_APROVACAO = 7.0;
if (nota >= NOTA_APROVACAO) return "Aprovado";

// 4. FAIL FAST — valide cedo, falhe cedo
void sacar(double valor) {
    // Valide no início, antes de qualquer lógica
    if (valor <= 0) throw new IllegalArgumentException("Valor invalido");
    if (valor > saldo) throw new SaldoInsuficienteException(saldo, valor);
    // Só agora executa a lógica principal
    saldo -= valor;
}

// 5. COMENTÁRIOS EXPLICAM O "POR QUE", NÃO O "O QUE"
// RUIM:
saldo += valor; // adiciona valor ao saldo (o código já diz isso!)

// BOM:
// Juros compostos: reinveste o rendimento mensalmente
saldo += saldo * taxaJurosMensal;
```

## 13.3 Estrutura de um projeto Java profissional

```
meu-projeto/
|
+-- src/
|     +-- br/edu/ifpe/poo/
|           +-- model/          <- classes de dados (Aluno, Produto...)
|           |     Aluno.java
|           |     Produto.java
|           |
|           +-- service/        <- logica de negocio
|           |     AlunoService.java
|           |
|           +-- exception/      <- excecoes personalizadas
|           |     NotaInvalidaException.java
|           |
|           +-- Main.java       <- ponto de entrada
|
+-- README.md
```

---

[Índice](../../README.md) | [← Anterior: 12. Projeto Final — Aplicação Completa](../12-projeto-final/README.md) | [Próximo: 14. Erros Comuns e Como Resolver →](../14-erros-comuns/README.md)
