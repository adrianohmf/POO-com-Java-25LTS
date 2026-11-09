// Arquivo: Lacos.java

void main() {
    // for: quando você sabe quantas repetições serão feitas
    IO.println("Tabuada do 7:");
    for (int i = 1; i <= 5; i++) {
        IO.println("7 x " + i + " = " + (7 * i));
    }

    // while: repete ENQUANTO a condição for verdadeira (pode nem executar)
    int contagem = 3;
    while (contagem > 0) {
        IO.println("Faltam " + contagem);
        contagem--;
    }
    IO.println("Fogo!");

    // do-while: executa pelo menos UMA vez e testa a condição no final
    int tentativa = 0;
    do {
        tentativa++;
        IO.println("Tentativa " + tentativa);
    } while (tentativa < 2);

    // continue pula para a próxima volta; break encerra o laço
    for (int n = 1; n <= 10; n++) {
        if (n % 2 == 0) {
            continue;       // ignora os pares
        }
        if (n > 7) {
            break;          // para o laço
        }
        IO.println("Impar: " + n);
    }

    // Acumulador: padrão muito comum para somar valores
    int soma = 0;
    for (int n = 1; n <= 100; n++) {
        soma += n;
    }
    IO.println("Soma de 1 a 100: " + soma);
}
