// Arquivo: HorasEDatas.java

import java.time.LocalDateTime;
import java.time.LocalTime;

void main() {
    // LocalTime: só a hora (sem data)
    LocalTime inicioAula = LocalTime.of(19, 30);
    LocalTime fimAula = inicioAula.plusMinutes(100);
    IO.println("Aula: " + inicioAula + " ate " + fimAula);
    IO.println("Comeca antes das 20h? " + inicioAula.isBefore(LocalTime.of(20, 0)));

    // LocalDateTime: data e hora juntas
    LocalDateTime entrega = LocalDateTime.of(2026, 10, 13, 23, 59);
    LocalDateTime prorrogada = entrega.plusDays(1).withHour(12).withMinute(0);
    IO.println("Entrega: " + entrega);
    IO.println("Prorrogada: " + prorrogada);

    // Convertendo entre os tipos
    IO.println("So a data: " + entrega.toLocalDate());
    IO.println("So a hora: " + entrega.toLocalTime());
}
