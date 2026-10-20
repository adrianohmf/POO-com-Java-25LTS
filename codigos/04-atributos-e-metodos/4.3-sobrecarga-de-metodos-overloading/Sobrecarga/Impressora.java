// Arquivo: Impressora.java

class Impressora {

    // Versão 1: recebe texto
    void imprimir(String texto) {
        IO.println("[TEXTO] " + texto);
    }

    // Versão 2: recebe número
    void imprimir(int numero) {
        IO.println("[NUMERO] " + numero);
    }

    // Versão 3: recebe texto e quantidade de vezes
    void imprimir(String texto, int vezes) {
        for (int i = 0; i < vezes; i++) {
            IO.println("[" + (i+1) + "] " + texto);
        }
    }
}
