[Índice](../../README.md) | [← Anterior: 13. Pacotes e Imports](../13-pacotes-e-imports/README.md) | [Próximo: 15. Leitura e Escrita de Arquivos →](../15-leitura-e-escrita-de-arquivos/README.md)

# 14. Datas com java.time

> **Códigos deste capítulo:** [codigos/14-datas-com-java-time](../../codigos/14-datas-com-java-time/)

> **O que você vai aprender neste capítulo:**
> Representar datas e horas com `LocalDate`, `LocalTime` e `LocalDateTime`, calcular diferenças com `Period`, `Duration` e `ChronoUnit`, formatar e ler datas no padrão brasileiro e usar datas dentro de objetos, como o prazo de devolução de um empréstimo.

Quase todo sistema lida com datas: o prazo de entrega de um trabalho, a data de nascimento de um aluno, o vencimento de uma conta, o dia da devolução de um livro. Guardar uma data como `String` ou como três `int` soltos parece simples, mas logo aparecem as dúvidas: quantos dias faltam para o fim do semestre? Que dia da semana cai o dia 25 de dezembro? O ano é bissexto?

O pacote `java.time` (disponível desde o Java 8) resolve tudo isso com classes prontas, claras e **imutáveis**. Ele substitui as antigas `java.util.Date` e `Calendar`, que são confusas e propensas a erros. Em código novo, use sempre `java.time`.

Todas as classes deste capítulo ficam no pacote `java.time` (ou em subpacotes), então precisam de `import`, como você viu no capítulo 13.

## 14.1 As principais classes

| Classe | O que guarda | Exemplo |
|---|---|---|
| `LocalDate` | Só a data | `2026-10-13` |
| `LocalTime` | Só a hora | `19:30` |
| `LocalDateTime` | Data e hora | `2026-10-13T19:30` |
| `Period` | Intervalo entre datas (anos, meses, dias) | 3 meses e 5 dias |
| `Duration` | Intervalo entre horas | 1 hora e 40 minutos |
| `DateTimeFormatter` | Regra para converter data em texto e texto em data | `dd/MM/yyyy` |
| `ChronoUnit` | Unidade de tempo para medir diferenças | `DAYS`, `WEEKS`, `MONTHS` |

O prefixo `Local` indica que a data não carrega fuso horário. Isso é suficiente para a maioria dos sistemas acadêmicos e administrativos.

## 14.2 LocalDate: trabalhando com datas

Não existe `new LocalDate(...)`. Os objetos são criados por métodos estáticos: `of`, `parse` e `now`.

```java
// Arquivo: ConhecendoLocalDate.java

import java.time.LocalDate;

void main() {
    // Três formas de obter uma data
    LocalDate prova = LocalDate.of(2026, 10, 13);        // ano, mês, dia
    LocalDate natal = LocalDate.parse("2026-12-25");     // texto no formato ISO: aaaa-mm-dd
    LocalDate hoje = LocalDate.now();                    // data do computador (muda todo dia)

    IO.println("Prova: " + prova);
    IO.println("Dia: " + prova.getDayOfMonth());
    IO.println("Mês: " + prova.getMonthValue() + " (" + prova.getMonth() + ")");
    IO.println("Ano: " + prova.getYear());
    IO.println("Dia da semana: " + prova.getDayOfWeek());
    IO.println("Dias no mês: " + prova.lengthOfMonth());
    IO.println("Ano bissexto? " + prova.isLeapYear());

    // Datas são IMUTÁVEIS: plusDays devolve uma NOVA data e não altera a original
    LocalDate daquiA7Dias = prova.plusDays(7);
    IO.println("Original: " + prova + " | Mais 7 dias: " + daquiA7Dias);
    IO.println("Menos 1 mês: " + prova.minusMonths(1));
    prova.plusDays(30);                                  // resultado descartado: prova continua igual
    IO.println("Depois de plusDays(30) sem guardar: " + prova);

    // Comparando datas
    IO.println(prova.isBefore(natal));                   // true
    IO.println(prova.isAfter(natal));                    // false
    IO.println(prova.equals(LocalDate.of(2026, 10, 13))); // true: equals compara o conteúdo

    // Valores inválidos lançam exceção: LocalDate.of(2026, 2, 30) -> DateTimeException
    IO.println("Hoje tem " + hoje.getYear() + " no calendário");   // o valor depende do dia
}
```

Saída esperada (a última linha usa o ano do computador):

```
Prova: 2026-10-13
Dia: 13
Mês: 10 (OCTOBER)
Ano: 2026
Dia da semana: TUESDAY
Dias no mês: 31
Ano bissexto? false
Original: 2026-10-13 | Mais 7 dias: 2026-10-20
Menos 1 mês: 2026-09-13
Depois de plusDays(30) sem guardar: 2026-10-13
true
false
true
Hoje tem 2026 no calendário
```

O ponto mais importante do exemplo: **as datas são imutáveis**. Métodos como `plusDays`, `minusMonths` e `withYear` **não alteram** o objeto original; eles devolvem uma data nova. Se você não guardar o resultado, ele se perde.

```java
data.plusDays(7);          // ERRADO: resultado descartado
data = data.plusDays(7);   // CORRETO: guarda a nova data
```

Isso é o mesmo comportamento de `String`, e é por isso que `LocalDate` é seguro de compartilhar entre classes e dentro de `record`.

## 14.3 LocalTime e LocalDateTime

`LocalTime` guarda apenas a hora, e `LocalDateTime` guarda data e hora. Os métodos seguem o mesmo padrão de `LocalDate`: `of`, `now`, `plus...`, `minus...`, `with...`, `isBefore` e `isAfter`.

```java
// Arquivo: HorasEDatas.java

import java.time.LocalDateTime;
import java.time.LocalTime;

void main() {
    // LocalTime: só a hora (sem data)
    LocalTime inicioAula = LocalTime.of(19, 30);
    LocalTime fimAula = inicioAula.plusMinutes(100);
    IO.println("Aula: " + inicioAula + " até " + fimAula);
    IO.println("Começa antes das 20h? " + inicioAula.isBefore(LocalTime.of(20, 0)));

    // LocalDateTime: data e hora juntas
    LocalDateTime entrega = LocalDateTime.of(2026, 10, 13, 23, 59);
    LocalDateTime prorrogada = entrega.plusDays(1).withHour(12).withMinute(0);
    IO.println("Entrega: " + entrega);
    IO.println("Prorrogada: " + prorrogada);

    // Convertendo entre os tipos
    IO.println("Só a data: " + entrega.toLocalDate());
    IO.println("Só a hora: " + entrega.toLocalTime());
}
```

Saída esperada:

```
Aula: 19:30 até 21:10
Começa antes das 20h? true
Entrega: 2026-10-13T23:59
Prorrogada: 2026-10-14T12:00
Só a data: 2026-10-13
Só a hora: 23:59
```

O `T` na saída (`2026-10-13T23:59`) é só o separador do formato padrão ISO. Na seção 14.5 você vai ver como mostrar de forma amigável.

## 14.4 Períodos e durações

Para medir a distância entre duas datas existem três ferramentas, e cada uma responde a uma pergunta diferente:

| Ferramenta | Pergunta que responde | Exemplo de resultado |
|---|---|---|
| `Period.between(a, b)` | Quantos anos, meses e dias separam as datas? | 26 anos, 4 meses e 23 dias |
| `ChronoUnit.DAYS.between(a, b)` | Quantos dias no total? | 66 |
| `Duration.between(h1, h2)` | Quanto tempo existe entre dois horários? | 1h40min |

```java
// Arquivo: CalculosComDatas.java

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

void main() {
    LocalDate nascimento = LocalDate.of(2000, 5, 20);
    LocalDate referencia = LocalDate.of(2026, 10, 13);

    // Period: diferença entre DATAS em anos, meses e dias
    Period idade = Period.between(nascimento, referencia);
    IO.println("Idade: " + idade.getYears() + " anos, "
            + idade.getMonths() + " meses e " + idade.getDays() + " dias");

    // ChronoUnit: diferença total em uma única unidade
    LocalDate fimDoSemestre = LocalDate.of(2026, 12, 18);
    long diasRestantes = ChronoUnit.DAYS.between(referencia, fimDoSemestre);
    long semanasRestantes = ChronoUnit.WEEKS.between(referencia, fimDoSemestre);
    IO.println("Dias até o fim do semestre: " + diasRestantes);
    IO.println("Semanas inteiras: " + semanasRestantes);

    // Se a primeira data for posterior à segunda, o resultado é negativo
    IO.println("Dias desde o fim do semestre até a prova: "
            + ChronoUnit.DAYS.between(fimDoSemestre, referencia));

    // Duration: diferença entre HORAS (tempo exato)
    Duration aula = Duration.between(LocalTime.of(19, 30), LocalTime.of(21, 10));
    IO.println("Duração da aula: " + aula.toMinutes() + " minutos");
    IO.println("Em horas e minutos: " + aula.toHours() + "h" + aula.toMinutesPart() + "min");
}
```

Saída esperada:

```
Idade: 26 anos, 4 meses e 23 dias
Dias até o fim do semestre: 66
Semanas inteiras: 9
Dias desde o fim do semestre até a prova: -66
Duração da aula: 100 minutos
Em horas e minutos: 1h40min
```

Atenção à ordem dos argumentos: `between(inicio, fim)`. Se a primeira data for **posterior** à segunda, o resultado vem **negativo**. Essa propriedade é útil, por exemplo, para saber se um prazo já passou.

## 14.5 Formatando e lendo datas

O formato padrão (`2026-10-13`) segue a norma ISO e não é o que o usuário brasileiro espera ver. Para isso existe o `DateTimeFormatter`, que usa um **padrão** escrito com letras:

| Letra | Significado | Exemplo |
|---|---|---|
| `dd` | Dia do mês | `14` |
| `MM` | Mês (número), **M maiúsculo** | `10` |
| `yyyy` | Ano com 4 dígitos | `2026` |
| `HH` | Hora de 0 a 23 | `19` |
| `mm` | Minutos, **m minúsculo** | `30` |

A diferença entre `MM` (mês) e `mm` (minutos) é a fonte de erro mais frequente com datas.

```java
// Arquivo: FormatandoDatas.java

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.format.TextStyle;
import java.util.Locale;

void main() {
    // Formatador no padrão brasileiro. dd = dia, MM = mês (maiúsculo!), yyyy = ano
    DateTimeFormatter br = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Data -> texto
    LocalDate data = LocalDate.of(2026, 10, 14);
    IO.println(data.format(br));                                    // 14/10/2026

    LocalDateTime momento = LocalDateTime.of(2026, 10, 14, 19, 30);
    IO.println(momento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

    // Nome do dia da semana em português
    Locale ptBr = Locale.of("pt", "BR");
    IO.println(data.getDayOfWeek().getDisplayName(TextStyle.FULL, ptBr));

    // Texto -> data (o texto precisa seguir exatamente o padrão do formatador)
    LocalDate lida = LocalDate.parse("25/12/2026", br);
    IO.println("Lida: " + lida);                                    // 2026-12-25

    // Texto fora do padrão lança DateTimeParseException
    try {
        LocalDate.parse("2026-12-25", br);
    } catch (DateTimeParseException e) {
        IO.println("Erro: formato esperado dd/MM/yyyy");
    }

    // Cuidado: no modo padrão, 31/02 é "ajustado" para o último dia do mês
    IO.println("Sem rigor: " + LocalDate.parse("31/02/2026", br));

    // Modo estrito: rejeita datas que não existem (use uuuu no lugar de yyyy)
    DateTimeFormatter estrito = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    try {
        LocalDate.parse("31/02/2026", estrito);
    } catch (DateTimeParseException e) {
        IO.println("Com rigor: data inválida");
    }
}
```

Saída esperada:

```
14/10/2026
14/10/2026 19:30
quarta-feira
Lida: 2026-12-25
Erro: formato esperado dd/MM/yyyy
Sem rigor: 2026-02-28
Com rigor: data inválida
```

Pontos de atenção do exemplo:

* **Data para texto:** `data.format(formatador)`.
* **Texto para data:** `LocalDate.parse(texto, formatador)`. O texto precisa seguir exatamente o padrão, senão ocorre `DateTimeParseException`, uma exceção que você já sabe tratar (capítulo 12).
* **Modo padrão é permissivo:** `31/02/2026` vira `28/02/2026` sem avisar. Para rejeitar datas que não existem, use `ResolverStyle.STRICT` e escreva `uuuu` no lugar de `yyyy` no padrão.

## 14.6 Datas dentro de objetos

Na prática, datas quase sempre aparecem como **atributos**. Vamos retomar o domínio da biblioteca: cada empréstimo tem o livro, a data de retirada e um prazo em dias. A classe calcula sozinha a data de devolução, os dias de atraso e a multa de R$ 0,50 por dia.

O `record` guarda os dados e os cálculos:

```java
// Arquivo: Emprestimo.java

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

record Emprestimo(String livro, LocalDate retirada, int prazoDias) {

    LocalDate dataDevolucao() {
        return retirada.plusDays(prazoDias);
    }

    long diasDeAtraso(LocalDate hoje) {
        long atraso = ChronoUnit.DAYS.between(dataDevolucao(), hoje);
        return Math.max(atraso, 0);
    }

    double multa(LocalDate hoje) {
        return diasDeAtraso(hoje) * 0.50;
    }
}
```

E o programa principal usa um array de empréstimos (seção 1.9) e um laço para mostrar a situação de cada um. A data de "hoje" é fixa no exemplo para que o resultado seja sempre o mesmo; em um sistema real você usaria `LocalDate.now()`.

```java
// Arquivo: ControleEmprestimos.java

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

void main() {
    DateTimeFormatter br = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate hoje = LocalDate.of(2026, 11, 9);     // fixa para o resultado ser sempre o mesmo

    Emprestimo[] emprestimos = {
        new Emprestimo("Dom Casmurro", LocalDate.of(2026, 10, 20), 14),
        new Emprestimo("Vidas Secas", LocalDate.of(2026, 10, 1), 14),
        new Emprestimo("Quincas Borba", LocalDate.of(2026, 11, 2), 14)
    };

    for (Emprestimo e : emprestimos) {
        System.out.format("%-14s devolver até %s", e.livro(), e.dataDevolucao().format(br));
        if (e.diasDeAtraso(hoje) > 0) {
            System.out.format(" | ATRASO: %d dias | multa R$ %.2f%n",
                    e.diasDeAtraso(hoje), e.multa(hoje));
        } else {
            System.out.println(" | em dia");
        }
    }
}
```

Saída esperada:

```
Dom Casmurro   devolver até 03/11/2026 | ATRASO: 6 dias | multa R$ 3.00
Vidas Secas    devolver até 15/10/2026 | ATRASO: 25 dias | multa R$ 12.50
Quincas Borba  devolver até 16/11/2026 | em dia
```

Observe `Math.max(atraso, 0)`: se a data de devolução ainda não chegou, a diferença é negativa e o atraso deve ser zero, não um número negativo.

> **Dica:** passar `hoje` como parâmetro, em vez de chamar `LocalDate.now()` dentro do método, deixa o código **testável**. Você escolhe a data do teste e o resultado é previsível.

## Erros comuns: Tema 14

```java
// ERRO 1: esperar que plusDays altere a data (as datas são imutáveis)
LocalDate data = LocalDate.of(2026, 10, 13);
data.plusDays(7);                  // o resultado foi descartado
IO.println(data);                  // 2026-10-13 (continua igual)
// CORRETO: data = data.plusDays(7);   ou   LocalDate nova = data.plusDays(7);

// ERRO 2: comparar datas com == (compara referências, não o conteúdo)
LocalDate a = LocalDate.of(2026, 10, 13);
LocalDate b = LocalDate.of(2026, 10, 13);
IO.println(a == b);                // pode dar false
IO.println(a.equals(b));           // true
// Para ordem use isBefore(), isAfter() ou compareTo()

// ERRO 3: mm (minutos) no lugar de MM (mês) no padrão
DateTimeFormatter errado = DateTimeFormatter.ofPattern("dd/mm/yyyy");   // mm = minuto!
// Ao formatar uma LocalDate dá erro (a data não tem minutos): UnsupportedTemporalTypeException
// CORRETO: "dd/MM/yyyy"

// ERRO 4: ler texto que não segue o padrão do formatador
LocalDate.parse("13/10/2026");     // sem formatador, espera aaaa-mm-dd
// Mensagem: DateTimeParseException: Text '13/10/2026' could not be parsed at index 0
// CORRETO: LocalDate.parse("13/10/2026", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

// ERRO 5: data que não existe
LocalDate.of(2026, 2, 30);
// Mensagem: DateTimeException: Invalid date 'FEBRUARY 30'

// ERRO 6: pedir horas de uma LocalDate (ela não tem hora)
LocalDate.of(2026, 10, 13).getHour();      // não compila: LocalDate não tem getHour()
// Use LocalDateTime se precisar de data e hora

// ERRO 7: usar a classe antiga java.util.Date em código novo
// Prefira sempre java.time: é imutável, clara e sem os problemas de Date e Calendar
```

## Exercícios: Tema 14

**Exercício 14.1:** Peça ao usuário a data de nascimento no formato `dd/MM/yyyy` (com `IO.readln`) e mostre a idade em anos, meses e dias, além do dia da semana em que ele nasceu.

**Exercício 14.2:** Escreva um programa que informe quantos dias faltam para o Natal, e que mostre `"Ja passou"` se a data já tiver passado neste ano.

**Exercício 14.3:** Crie um método que receba uma `LocalDate` e devolva `true` se ela cair em um fim de semana (`DayOfWeek.SATURDAY` ou `DayOfWeek.SUNDAY`).

**Exercício 14.4:** Dado um `LocalDate` de início e uma quantidade de aulas semanais, liste as datas das 8 primeiras aulas, uma por semana.

**Exercício 14.5:** Faça um programa que leia uma data digitada pelo usuário e trate `DateTimeParseException` com uma mensagem amigável, pedindo para digitar de novo.

**Exercício 14.6 (Projeto progressivo):** No projeto `Artesao`, registre a data de cada venda e crie um método que calcule o faturamento de um mês específico.

---

[Índice](../../README.md) | [← Anterior: 13. Pacotes e Imports](../13-pacotes-e-imports/README.md) | [Próximo: 15. Leitura e Escrita de Arquivos →](../15-leitura-e-escrita-de-arquivos/README.md)
