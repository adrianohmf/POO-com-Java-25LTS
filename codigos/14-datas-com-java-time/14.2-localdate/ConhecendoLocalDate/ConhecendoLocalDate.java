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
