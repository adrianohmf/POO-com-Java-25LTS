// Arquivo: Heranca.java

void main() {
    var aluno = new Aluno("Maria Silva", 20, "111.222.333-44",
                           "2025-0001", "Informatica para Internet");
    aluno.definirMedia(8.5);

    var prof = new Professor("Adriano Franca", 35, "555.666.777-88",
                              "POO", "SIAPE-12345");

    IO.println("========================================");
    IO.println("ALUNO:");
    aluno.apresentar();

    IO.println("========================================");
    IO.println("PROFESSOR:");
    prof.apresentar();
    prof.ministrarAula();
}
