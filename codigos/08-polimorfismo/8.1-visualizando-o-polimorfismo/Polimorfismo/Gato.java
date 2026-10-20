// Arquivo: Gato.java

class Gato extends Animal {
    Gato(String nome) { super(nome); }

    @Override
    void fazerSom() { IO.println(nome + ": Miau!"); }
}
