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
