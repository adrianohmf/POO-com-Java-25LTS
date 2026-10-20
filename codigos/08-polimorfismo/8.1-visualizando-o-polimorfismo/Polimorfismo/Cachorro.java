// Arquivo: Cachorro.java

class Cachorro extends Animal {
    Cachorro(String nome) { super(nome); }

    @Override
    void fazerSom() { IO.println(nome + ": Au au!"); }
}
