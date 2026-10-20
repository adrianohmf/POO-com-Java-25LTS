// Arquivo: Vaca.java

class Vaca extends Animal {
    Vaca(String nome) { super(nome); }

    @Override
    void fazerSom() { IO.println(nome + ": Muuuu!"); }
}
