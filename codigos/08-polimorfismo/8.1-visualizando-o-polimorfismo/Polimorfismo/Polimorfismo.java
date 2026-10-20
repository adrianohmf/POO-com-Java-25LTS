// Arquivo: Polimorfismo.java

void main() {
    // Array do tipo Animal aceita qualquer subclasse
    Animal[] animais = {
        new Cachorro("Rex"),
        new Gato("Mimi"),
        new Vaca("Mimosa"),
        new Cachorro("Bolt")
    };

    IO.println("=== Todos os animais fazendo som ===");
    for (Animal a : animais) {
        a.fazerSom(); // Java decide qual versão chamar em tempo de execução
    }

    IO.println("=== Hora de dormir ===");
    for (Animal a : animais) {
        a.dormir(); // não sobrescrito: mesmo comportamento para todos
    }
}
