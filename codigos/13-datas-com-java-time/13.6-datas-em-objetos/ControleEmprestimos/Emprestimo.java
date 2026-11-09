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
