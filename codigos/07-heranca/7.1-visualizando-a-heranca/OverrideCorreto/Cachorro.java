// Arquivo: Cachorro.java

class Cachorro extends Animal {
    @Override
    void fazerSom() { IO.println("Au au!"); }   // OK: existe fazerSom() em Animal
}
