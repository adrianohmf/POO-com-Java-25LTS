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
        System.out.format("%-14s devolver ate %s", e.livro(), e.dataDevolucao().format(br));
        if (e.diasDeAtraso(hoje) > 0) {
            System.out.format(" | ATRASO: %d dias | multa R$ %.2f%n",
                    e.diasDeAtraso(hoje), e.multa(hoje));
        } else {
            System.out.println(" | em dia");
        }
    }
}
