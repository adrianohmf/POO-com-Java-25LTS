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
        IO.println("Com rigor: data invalida");
    }
}
