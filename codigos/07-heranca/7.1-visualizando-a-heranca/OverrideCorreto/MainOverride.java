// Arquivo: MainOverride.java
// Programa principal adicionado para executar o exemplo de @Override desta seção.
// Para executar: java MainOverride.java

void main() {
    var animal   = new Animal();
    var cachorro = new Cachorro();

    animal.fazerSom();     // versão da superclasse
    cachorro.fazerSom();   // versão sobrescrita na subclasse
}
