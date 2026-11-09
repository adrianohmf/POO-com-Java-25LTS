// Arquivo: EscolhaSwitch.java

void main() {
    int dia = 3;

    // switch como expressão (forma moderna, com seta ->): devolve um valor
    String nome = switch (dia) {
        case 1 -> "Domingo";
        case 2 -> "Segunda";
        case 3 -> "Terca";
        case 4 -> "Quarta";
        case 5 -> "Quinta";
        case 6 -> "Sexta";
        case 7 -> "Sabado";
        default -> "Dia invalido";
    };
    IO.println("Dia " + dia + ": " + nome);

    // Vários valores no mesmo case, separados por vírgula
    String tipo = switch (dia) {
        case 1, 7 -> "Fim de semana";
        case 2, 3, 4, 5, 6 -> "Dia util";
        default -> "Dia invalido";
    };
    IO.println("Tipo: " + tipo);

    // switch com String; bloco com várias linhas usa yield para devolver o valor
    String comando = "sair";
    int codigo = switch (comando) {
        case "novo" -> 1;
        case "listar" -> 2;
        case "sair" -> {
            IO.println("Encerrando...");
            yield 0;
        }
        default -> -1;
    };
    IO.println("Codigo: " + codigo);
}
