// Arquivo: Sobrecarga.java

void main() {
    var imp = new Impressora();

    imp.imprimir("Ola!");         // chama versão 1
    imp.imprimir(42);             // chama versão 2
    imp.imprimir("Java 25!", 3);  // chama versão 3
}
